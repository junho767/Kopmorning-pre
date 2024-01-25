package me.junholee.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Article;


@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {
    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    public Article toEntity(String author) {
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
