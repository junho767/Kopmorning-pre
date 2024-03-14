package me.junholee.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Article;


@NoArgsConstructor //해당 클래스에 파라미터가 없는 기본 생성자를 자동으로 생성합니다. 이 생성자는 클래스를 인스턴스화할 때 사용
@AllArgsConstructor //모든 필드를 매개변수로 받는 생성자를 자동으로 생성합니다. 이 생성자는 모든 필드를 초기화할 때 사용
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
