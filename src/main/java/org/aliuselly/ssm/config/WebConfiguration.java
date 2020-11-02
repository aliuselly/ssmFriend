package org.aliuselly.ssm.config;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;

public class WebConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 启动 spring
     * 加载 spring 配置类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ApplicationContext.class};
    }

    /**
     * 配置 spring mvc 配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcDispatcherServlet.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 配置 spring mvc 编码过滤器
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new CharacterEncodingFilter("UTF-8", true)};
    }

    /**
     * 启动 spring: 配置加载 spring 文件的监听器
     *
     * 这个类是实现 servletContextListener 接口的
     * 因此，作用也知道了吧
     * @param servletContext
     */
    /*@Override
    protected void registerContextLoaderListener(ServletContext servletContext) {
        super.registerContextLoaderListener(servletContext);
        servletContext.addListener(ContextLoaderListener.class);
//        配置 spring mvc 编码过滤器
        servletContext.addFilter("/*", new CharacterEncodingFilter("UTF-8", true));
    }*/
}
