package cn.lz.szp;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * quincey
 * Date 2020/7/3 17:12
 */

@SpringBootApplication
@MapperScan(basePackages = {"cn.lz.szp.dao"})
public class UserAccountRestApp {
    Logger log = LoggerFactory.getLogger(UserAccountRestApp.class);

    @PostConstruct
    void setTimezone() {
        log.warn("服务器Timezone默认时区是：" + TimeZone.getDefault().getID());
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    public static void main(String[] args) {
        SpringApplication.run(UserAccountRestApp.class, args);
    }
}
