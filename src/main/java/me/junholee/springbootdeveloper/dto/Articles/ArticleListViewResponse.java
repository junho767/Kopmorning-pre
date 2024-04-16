package me.junholee.springbootdeveloper.dto.Articles;

import lombok.Getter;
import me.junholee.springbootdeveloper.domain.Article;

import java.time.LocalDateTime;

@Getter
public class ArticleListViewResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    public ArticleListViewResponse(Article article){
        this.id=article.getId();
        this.title=article.getTitle();
        this.content=article.getContent();
        this.author=article.getAuthor();
        this.createdAt=article.getCreatedAt();
    }
}