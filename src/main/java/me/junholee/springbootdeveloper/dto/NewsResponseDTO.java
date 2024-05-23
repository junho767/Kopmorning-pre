package me.junholee.springbootdeveloper.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NewsResponseDTO {
    private String title;
    private String url;
    private String image;
    private String time;
    private String category;

    @Builder
    public NewsResponseDTO(String title, String url, String image, String category, String time){
        this.title = title;
        this.url = url;
        this.image = image;
        this.category = category;
        this.time = time;
    }
}
