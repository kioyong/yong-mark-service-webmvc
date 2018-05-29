package com.yong.mark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yong.a.liang
 */
@SpringBootApplication
@EnableCaching
@EnableWebMvc
@EnableAutoConfiguration
public class MarkServiceApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(MarkServiceApplication.class, args);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
    }
}

