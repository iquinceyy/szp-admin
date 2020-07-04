package cn.lz.szp.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * quincey
 * Date 2020/7/3 16:43
 */
@SpringBootConfiguration
public class DataSourceConfig {
    @Bean
    DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setUsername("root");
//        druidDataSource.setPassword("root");
//        druidDataSource.setUrl("jdbc:mysql://120.25.223.121:3306/szp_db_test?serverTimezone=Asia/Shanghai");

        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/szp_db_test?serverTimezone=Asia/Shanghai");
        return druidDataSource;
    }
    // 再这里配置mybatis：

    @Bean
    SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        try {
            sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath*:mybatis/mapper/**/*.xml");
            sqlSessionFactoryBean.setMapperLocations(resources);
            return sqlSessionFactoryBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
