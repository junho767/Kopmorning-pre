package me.junholee.springbootdeveloper.dto.CommentList;

import lombok.Getter;
import me.junholee.springbootdeveloper.domain.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponse {
    private Long id;
    private String nickname;
    private String comment;
    private LocalDateTime createdDate;
    private Long articleId;
    private String image;

    public CommentResponse(Comment comment){
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.comment = comment.getComment();
        this.articleId = comment.getArticle().getId();
        this.createdDate = comment.getCreatedDate();
        this.image = comment.getUser().getPicture();
    }
}
