package me.junholee.springbootdeveloper.dto.Articles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.User;


@NoArgsConstructor //해당 클래스에 파라미터가 없는 기본 생성자를 자동으로 생성합니다. 이 생성자는 클래스를 인스턴스화할 때 사용
@AllArgsConstructor //모든 필드를 매개변수로 받는 생성자를 자동으로 생성합니다. 이 생성자는 모든 필드를 초기화할 때 사용
@Getter
@Setter
public class AddArticleRequest {
    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    public Article toEntity(User user) {
        return Article.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}
