package me.junholee.springbootdeveloper.dto.Articles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.ArticleImage;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.CommentList.CommentResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private User user;
    private List<CommentResponse> comment;
    private List<String> imageUrl;
    private int likeCount;
    private int viewCount;

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.user = article.getUser();
        this.comment = article.getComment().stream().map(CommentResponse::new).collect(Collectors.toList());
        this.likeCount = article.getLikeCount();
        this.viewCount = article.getViewCount();
        this.imageUrl = article.getArticleImage().stream().map(ArticleImage::getUrl).collect(Collectors.toList());
    }
}