package me.junholee.springbootdeveloper.dto.Articles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.User;


@NoArgsConstructor //해당 클래스에 파라미터가 없는 기본 생성자를 자동으로 생성합니다. 이 생성자는 클래스를 인스턴스화할 때 사용
@Getter
@Setter
public class AddArticleRequest {
    private String title;
    private String content;
    private String articleType;
    public AddArticleRequest(String title, String content, String articleType){
        this.title = title;
        this.content = content;
        this.articleType = articleType;
    }
}
