package org.aliuselly.ssm.config;

import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * mybatis 与 spring 整合
 */
@Configuration
@MapperScan("org.aliuselly.ssm.dao")  // 这个可以替换下面的那个方法的
public class MybatisSpring {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        注入数据源
        factoryBean.setDataSource(dataSource);

//        mybatis 分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();

        Properties properties = new Properties();
//        注意，要使用 getClassLoader 这个，而不是直接 getClass().getResourceAsStream() 这个是拿到对象后才创建的，如果没有对象情况下，会是 null 的，因此呢，类加载器不会 null 的
        properties.load(this.getClass().getClassLoader().getResourceAsStream("mybatisPage.properties"));

//        指定数据库类型  不过呢，并没有这个属性啊，人家的 setProperties 方法那边，不知道他了
        pageInterceptor.setProperties(properties);

//        引入插件
        factoryBean.setPlugins(pageInterceptor);
        return factoryBean;
    }

    /**
     * 开启 mapper 接口扫描器：扫描 dao 层
     * @return
     */
    /*@Bean
    public MapperScannerConfigurer mapperScannerConfigurer()
    {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("org.aliuselly.ssm.dao");
        return configurer;
    }*/
}
