package com.example.billoa.common.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class MyWebConfigurer {

    @Bean
    public CorsFilter corsFilter(){
        //添加Cros配置信息
        CorsConfiguration configuration = new CorsConfiguration();
        //允许跨的域
        configuration.addAllowedOrigin("http://localhost:9527");
        configuration.addAllowedOrigin("http://60.205.225.90");
        //configuration.addAllowedOrigin("http://60.205.225.90:9527");
        //是否发送cookie信息
        configuration.setAllowCredentials(true);
        //允许的请求方式
        configuration.addAllowedMethod("*");
        //允许的请求头信息
        configuration.addAllowedHeader("*");

        //添加映射路径
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",configuration);

        //返回的CrosFilter
        return  new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
