package cn.lz.szp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * quincey
 * Date 2020/7/3 19:19
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"cn.lz.szp.service"})
public class GatewayApp8000 {
    Logger log = LoggerFactory.getLogger(GatewayApp8000.class);

    @PostConstruct
    void setTimezone() {
        log.warn("服务器Timezone默认时区是：" + TimeZone.getDefault().getID());
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApp8000.class, args);
    }
}
