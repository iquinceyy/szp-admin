package cn.lz.szp.config.feign;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;

/**
 * quincey
 * Date 2020/7/3 16:34
 */
@Configuration
public class FeignClientConfig {
    @Resource
    FeignClientProperties feignClientProperties;// 第二种配置fen属性的方式

    @Bean
    public BasicAuthRequestInterceptor getBasicAuthRequestInterceptor() {
//        feignHttpClientProperties.setConnectionTimerRepeat(60000);
//        feignHttpClientProperties.setConnectionTimeout(60000);
        Map<String, FeignClientProperties.FeignClientConfiguration> config = feignClientProperties.getConfig();
        FeignClientProperties.FeignClientConfiguration aDefault = config.get("default");
        if (aDefault == null) {
            aDefault = new FeignClientProperties.FeignClientConfiguration();
        }
        aDefault.setReadTimeout(30000);// 读取超时，业务最长响应时间（这里设置就可以取代ribbon超时设置）
        aDefault.setConnectTimeout(30000); //链接超时30秒 这里设置就可以取代ribbon连接超时设置）

        config.put("default", aDefault);

        feignClientProperties.setConfig(config);

        // 注意！BasicAuthRequestInterceptor这个类是feign.auth包，千万不要导错了 默认的加密方式：
        return new BasicAuthRequestInterceptor("qianfeng", "java");
    }
}
