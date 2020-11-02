package org.aliuselly.ssm.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * spring 配置类
 */
@Configuration
@ComponentScan(value = "org.aliuselly.ssm", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
})  // 开启注解扫描
@PropertySource("classpath:c3p0.properties")  // 读取数据库配置信息
@EnableTransactionManagement  // 开启注解事务的支持，要和下面的事务管理器结合使用，纯注解的话
@Import({MybatisSpring.class})  // 导入 mybatis 配置类
public class ApplicationContext {

    @Value("${c3p0.driverClass}")
    private String driverClass;
    @Value("${c3p0.jdbcUrl}")
    private String jdbcUrl;
    @Value("${c3p0.user}")
    private String user;
    @Value("${c3p0.password}")
    private String password;
    @Value("${c3p0.initialPoolSize}")
    private String initialPoolSize;
    @Value("${c3p0.maxPoolSize}")
    private String maxPoolSize;
    @Value("${c3p0.minPoolSize}")
    private String minPoolSize;
    @Value("${c3p0.maxIdleTime}")
    private String maxIdleTime;

    /**
     * 配置数据源
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        数据库驱动
        dataSource.setDriverClass(driverClass);
//        连接 Url
        dataSource.setJdbcUrl(jdbcUrl);
//        数据库用户
        dataSource.setUser(user);
//        数据库密码
        dataSource.setPassword(password);
//        初始化连接数
        dataSource.setInitialPoolSize(Integer.parseInt(initialPoolSize));
//        最大连接数
        dataSource.setMaxPoolSize(Integer.parseInt(maxPoolSize));
//        最小连接数
        dataSource.setMinPoolSize(Integer.parseInt(minPoolSize));
//        连接的生存时间
        dataSource.setMaxIdleTime(Integer.parseInt(maxIdleTime));

        return dataSource;
    }

    /**
     * 配置 spring 事务管理器
     * @param dataSource
     * @return
     */
    @Bean
    public PlatformTransactionManager dataSourceTransactionManager(DataSource dataSource)
    {
//        原理：控制数据源
        return new DataSourceTransactionManager(dataSource);
    }
}
