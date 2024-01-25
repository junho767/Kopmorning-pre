package me.junholee.springbootdeveloper.dto;

import lombok.Getter;
import me.junholee.springbootdeveloper.domain.Article;

@Getter
public class ArticleListViewResponse {
    private Long id;
    private String title;
    private String content;
    public ArticleListViewResponse(Article article){
        this.id=article.getId();
        this.title=article.getTitle();
        this.content=article.getContent();
    }
}