package cn.lz.szp.config.fdfs;


import net.coobird.thumbnailator.Thumbnails;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * @author lh
 * @date 2019/7/16 16:31
 */
@Component
public class FastdfsClient {
    private String WINDOW_UPLOAD_PATH = "N:" + File.separator + "upload/";
    private String LINUX_UPLOAD_PATH = "/upload/";// 这个操作根本没有太大的必要，如果要压缩，需要先将文件保存到磁盘，然后再从磁盘中获取与二进制数据
    Logger logger = LoggerFactory.getLogger(FastdfsClient.class);

    private String trackers_pro = "120.25.223.121";// 正式环境
    private String fileDom = "120.25.223.121:8888";// storage访问的域名地址（给前端用的） 8888是storage默认端口
    // 对于前端来说，访问图片的时候，前后端分离的时候，一定要加上图片域名前缀。。。+图片保存路径
    private StorageServer storageServer;// 声明一个storageServer

    @Autowired
    public FastdfsClient(Environment environment) throws Exception {
//        ClientGlobal.init("fast_client.conf");
        String[] activeProfiles = environment.getActiveProfiles();// 获取当前环境情况
        if (activeProfiles.length > 0) {
            if ("dev".equals(activeProfiles[0])) {
                trackers_pro = "120.25.223.121";
                fileDom = "http://120.25.223.121:8888";// 图片地址前缀
            }
        }
        logger.warn("FastDfs的环境：地址" + trackers_pro);
        ClientGlobal.initByTrackers(trackers_pro + ":22122");// tracker服务器集群地址
        ClientGlobal.setG_connect_timeout(10 * 1000);// 设置连接超时时间为10秒
        this.storageServer = new StorageServer(trackers_pro, 8888, 0);// 根据tracker获取storage地址
    }

    // 第三步：通过TrackerServer获取一个可以使用的storageClient
    public StorageClient1 getStorageClient1() {// 获取 storaged 一个客户端对象。
        StorageClient1 storageClient1;
        TrackerServer trackerServer = null;
        try {
            TrackerClient trackerClient = new TrackerClient();
//            trackerServer = trackerClient.getConnection();// 拿到trackerServer
            trackerServer = trackerClient.getTrackerServer();// 拿到trackerServer
            storageClient1 = new StorageClient1(trackerServer, null);// 根据tackerServer获取storageClient
        } catch (Exception e) {
            e.printStackTrace();
            TrackerClient trackerClient = new TrackerClient();
            try {
//                trackerServer = trackerClient.getConnection();
                trackerServer = trackerClient.getTrackerServer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            storageClient1 = new StorageClient1(trackerServer, storageServer);
        }
        return storageClient1;
    }

    /**
     * 适合上传单个文件
     *
     * @return 上传成功返回上传文件的路径，否则返回null
     */
    public String uploadFile(MultipartFile f, Integer... kb) {// 是否需要压缩图片
        if (!f.isEmpty()) {
            if (f.getSize() > 0) {
                String[] strings;
                if (kb.length == 0) {// 默认给你压缩到100KB
                    kb = new Integer[]{100};
                }
                byte[] bytes;
                try {
                    String originalFilename = f.getOriginalFilename();
                    String file_ext_name = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);


                    bytes = getFileBytes(f, file_ext_name, kb);// 将文件压缩或者获得二级制数据
//                    f.getBytes();// 如果不压缩，直接获取二进制数据
                    StorageClient1 storageClient1 = getStorageClient1();

                    strings = storageClient1.upload_file(bytes, file_ext_name, null);// 文件的元数据


                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                String path = "/" + strings[0] + "/" + strings[1];// 得到图片路径再加上图片前缀，就是前端需要的文件路径
                logger.info("缓存到：" + path);
                return fileDom + path;
            }
        }
        return null;
    }


    /**
     * 由外部传入storage客户端，这样就能否同一个客户端上传多个图片
     *
     * @return 上传成功返回上传文件的路径，否则返回null
     */
    public String uploadFile(MultipartFile f, StorageClient1 storageClient1, Integer... kb) {

        if (!f.isEmpty()) {
            if (f.getSize() > 0) {
                String[] strings;
                if (kb.length == 0) {
                    kb = new Integer[]{100};
                }
                byte[] bytes;
                String file_ext_name = Objects.requireNonNull(f.getContentType()).split("/")[1];
                try {
                    bytes = getFileBytes(f, file_ext_name, kb);
                    strings = storageClient1.upload_file(bytes, file_ext_name, null);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                String path = "/" + strings[0] + "/" + strings[1];
                logger.info("缓存到：" + path);
                return path;
            }
        }
        return null;
    }


    /**
     * 获取上传文件路径的方法
     *
     * @return
     */
    public String getUploadPath() {
        String realPath = LINUX_UPLOAD_PATH;// 写死。也就是规定好，这些文件就放到哪里去
        String systemType = System.getProperty("os.name");// 获取系统的类别, Window 8
        if (systemType.toLowerCase().contains("windows")) {// 此时是windows系统
            realPath = WINDOW_UPLOAD_PATH;
        }
        return realPath;
    }

    // 获取图片的压缩二进制数据：思路先将图片存到服务器，再从服务器获取文件，转换成二进制数据。Thumbnails没有提供直接压缩之后就得到二进制数据
    private byte[] getFileBytes(MultipartFile f, String file_ext_name, Integer[] kb) throws IOException {
        byte[] bytes;
        if (kb != null && kb.length > 0 && f.getSize() > 1024 * kb[0]) {// 要进行压缩
            String fileName = UUID.randomUUID().toString() + "." + file_ext_name;
            File f1 = new File(getUploadPath() + fileName);
            if (!f1.getParentFile().exists()) {
                f1.getParentFile().mkdirs();
            }
            // Thumbnails 是对图片进行压缩方法，反转转存等等操作的一个开源框架。。
            Thumbnails.of(f.getInputStream()).scale(0.5f).toFile(f1);// 只能得到文件，而且只能将文件保存到磁盘。
            // 这里需要改进：改进的方向能否压缩之后，利用内存流直接将图片二进制数据返回？

            while (f1.length() > kb[0] * 1024) {//压缩后仍然大于daxiaoKB,这样压缩出来是设置值的2/3差不多，两百多KB，继续压缩
                Thumbnails.of(f1).scale(0.7f).outputQuality(0.8f).toFile(f1);
            }
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 8];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(f1));
            while (-1 != (bufferedInputStream.read(buffer))) {
                output.write(buffer);
            }
            bytes = output.toByteArray();
            output.close();
            bufferedInputStream.close();
            f1.delete();// 删除临时文件
        } else {
            bytes = f.getBytes();
        }
        return bytes;
    }

    // 上传图片
    public List<String> uploadFile(MultipartFile[] fs, Integer... kb) {
        List<String> imgs = new ArrayList<>();
        StorageClient1 storageClient1 = getStorageClient1();
        for (MultipartFile f : fs) {
            String s = uploadFile(f, storageClient1, kb);
            imgs.add(s);
        }
        return imgs;
    }

    public String uploadFile(Collection<MultipartFile> fs, Integer... kb) {
        StringBuilder imgs = new StringBuilder();
        StorageClient1 storageClient1 = getStorageClient1();

        for (MultipartFile f : fs) {
            String s = uploadFile(f, storageClient1, kb);
            imgs.append(s).append(",");
        }
        if (imgs.length() > 0) {
            imgs.delete(imgs.length() - 1, imgs.length());
        }
        return imgs.toString();
    }

    // 批量上传
    public List<String> uploadFiles(Collection<MultipartFile> fs, Integer... kb) {
        List<String> list = new ArrayList<>();
        StorageClient1 storageClient1 = getStorageClient1();

        for (MultipartFile f : fs) {
            String s = uploadFile(f, storageClient1, kb);
            list.add(fileDom + s);
        }
        return list;
    }

    /**
     * @param filePath 上传文件的路径
     * @return 删除成功返回true, 删除失败返回false
     */
    public boolean deleteFile(String filePath) {
        int res = 0;
        try {
            if (filePath.startsWith("/group")) {// 是图片才去删除
                res = getStorageClient1().delete_file1(filePath.substring(1));
                if (res == 0) {
                    logger.info("fdfs删除文件成功！==>" + filePath);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return res == 0;
    }


}


