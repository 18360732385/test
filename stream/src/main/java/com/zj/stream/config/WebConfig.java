/**
 * Copyright (C), 2019
 * FileName: WebConfig
 * Author:   zhangjian
 * Date:     2019/9/2 18:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.stream.config;

import com.zj.stream.filter.MyFilter;
import com.zj.stream.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    // 重写WebMvcConfigurerAdapter类的addInterceptors方法，添加自己的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**");
    }


    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        MyFilter myFilter = new MyFilter();
        filterRegistrationBean.setFilter(myFilter);
//        Map<String,String> m = new HashMap<String,String>();
//        m.put("targetBeanName","MyKey");
//        m.put("targetFilterLifecycle","true");
//        filterRegistrationBean.setInitParameters(m);
        // 设置MyFile在指定url起作用
        List<String> urlList = new ArrayList<>();
        urlList.add("/*");

        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean;
    }
}
