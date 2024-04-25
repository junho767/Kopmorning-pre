package me.junholee.springbootdeveloper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profileImage/**")
                .addResourceLocations("file:///C:/Users/wnsgh/img/profile/");
//                .addResourceLocations("file:///img/profile/");

        registry.addResourceHandler("/articleImage/**")
                .addResourceLocations("file:///C:/Users/wnsgh/img/article/");
//                .addResourceLocations("file:///img/article/");
    }
}
