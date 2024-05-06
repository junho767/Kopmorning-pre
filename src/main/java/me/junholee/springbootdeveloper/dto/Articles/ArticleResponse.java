package me.junholee.springbootdeveloper.dto.Articles;

import lombok.Getter;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.ArticleImage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ArticleResponse {
    private String title;
    private String content;
    private Long id;
    private LocalDateTime createdAt;
    private String nickname;
    private List<String> imageUrls;
    public ArticleResponse(Article article){
        this.title=article.getTitle();
        this.content=article.getContent();
        this.id = article.getId();
        this.createdAt = article.getCreatedAt();
        this.nickname = article.getUser().getNickname();
        this.imageUrls = article.getArticleImage().stream()
                .map(ArticleImage::getUrl)
                .collect(Collectors.toList());
    }
}
