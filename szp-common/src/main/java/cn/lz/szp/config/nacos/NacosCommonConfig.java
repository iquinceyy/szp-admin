package cn.lz.szp.config.nacos;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * quincey
 * Date 2020/7/3 16:34
 */
@SpringBootConfiguration
@Slf4j
public class NacosCommonConfig {
    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;// 这种方式是比较先进方式，看源码出来

    @Resource
    Environment environment;

    @PostConstruct
    public void initNacos() {
        String appName = environment.getProperty("spring.application.name");// 拿到微服务的名字
        String serverAddr = "118.31.17.120:8848";//开发环境用测试服务器的公网
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length > 0) {
            if ("pro".equals(activeProfiles[0])) {
                serverAddr = "172.16.25.162:8848";// 正式环境的私网
            } else if ("dev".equals(activeProfiles[0])) {
                serverAddr = "118.31.17.120:8848";// 开发环境用测试服务器的公网

                nacosDiscoveryProperties.setWatchDelay(2000L);// 从nacos获取服务列表的频率（2秒一次）
                nacosDiscoveryProperties.setHeartBeatInterval(1);// 给nacos发送心跳的时间间隔
                nacosDiscoveryProperties.setHeartBeatTimeout(3);// nacos多少秒没有收到这个心跳，就直接把这个微服务删除
            }
        }
        if (activeProfiles.length > 0) {
            log.info("#######" + appName + ":配置nacos的环境" + activeProfiles[0] + "地址：" + serverAddr);
        } else {
            log.info("#######" + appName + ":配置nacos的环境地址：" + serverAddr);
        }
        nacosDiscoveryProperties.setServerAddr(serverAddr);
    }
}
