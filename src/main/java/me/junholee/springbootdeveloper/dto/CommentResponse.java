package me.junholee.springbootdeveloper.dto;

import lombok.Getter;
import me.junholee.springbootdeveloper.domain.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponse {
    private Long id;
    private String nickname;
    private String comment;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private Long articleId;

    public CommentResponse(Comment comment){
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.comment = comment.getComment();
        this.articleId = comment.getArticle().getId();
        this.createdDate = comment.getCreatedDate();
    }
}
