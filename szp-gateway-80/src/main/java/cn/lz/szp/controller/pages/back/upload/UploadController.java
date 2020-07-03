package cn.lz.szp.controller.pages.back.upload;

import cn.lz.szp.config.fdfs.FastdfsClient;
import cn.lz.szp.pojo.dto.ResponseDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.util.*;

/**
 * creator：杜夫人
 * date: 2020/6/30
 */
@RestController
@RequestMapping("/back/upload")
public class UploadController {
    @Resource
    FastdfsClient fastdfsClient;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("uploadFiles")
// 上传单张图片和多张图片,多个图片以逗号分隔
    ResponseDTO uploadFiles(MultipartHttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
        Collection<List<MultipartFile>> values = multiFileMap.values();
        for (List<MultipartFile> value : values) {
            for (MultipartFile m : value) {
                String s = fastdfsClient.uploadFile(m, 50);// 上传图片，并且压缩到50KB以内
                // 要做，因为磁盘空间。
                //  redisTemplate.opsForValue().set("img_cache_" + s, System.currentTimeMillis());// 把图片和当前时间存进去
                builder.append(s).append(",");
            }
        }
        if (builder.length() > 0) {
            builder.delete(builder.length() - 1, builder.length());// 去掉最后一个逗号
        }
        // 不够好/没有缓存功能（应该给它2个小时的操作时间（确认保存的操作时间，如果两个小时没有真正的将图片保存，我们就应该删除图片））
        // redis来做


        return ResponseDTO.ok("上传成功", builder.toString());
    }

    @RequestMapping("uploadFilesGetArray")
// 上传单张图片和多张图片,多个图片以逗号分隔
    ResponseDTO uploadFilesGetArray(MultipartHttpServletRequest request) {
        MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
        Collection<List<MultipartFile>> values = multiFileMap.values();
        List<String> list = new ArrayList<>();

        for (List<MultipartFile> value : values) {
            for (MultipartFile m : value) {
                String s = fastdfsClient.uploadFile(m, 50);// 上传图片，并且压缩到50KB以内
                list.add(s);
            }
        }

        return ResponseDTO.ok("上传成功", list);
    }

    // wangeditor上传 // 它需要返回的格式：code:0 ,data:["src1","src2"]
    @RequestMapping("wangEditorUploadFiles")
    Object wangEditorUploadFiles(MultipartHttpServletRequest request) {// 富文本上传
        ResponseDTO res = this.uploadFiles(request);
        String o = res.getObject(String.class);
        if (o.contains(",")) {
            String[] data = o.split(",");
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("code", 0);
            resMap.put("data", data);
            return resMap;
        }
        return null;
    }
}
