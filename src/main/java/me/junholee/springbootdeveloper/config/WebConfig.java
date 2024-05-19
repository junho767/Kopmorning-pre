package me.junholee.springbootdeveloper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// 이미지 업로드 시 저장 될 경로 설정 및 접근 방식 설정
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profileImage/**")
//                .addResourceLocations("file:///C:/Users/wnsgh/img/profile/");
                .addResourceLocations("file:///img/profile/");

        registry.addResourceHandler("/articleImage/**")
//                .addResourceLocations("file:///C:/Users/wnsgh/img/article/");
                .addResourceLocations("file:///img/article/");
    }
}
