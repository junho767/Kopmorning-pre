package me.junholee.springbootdeveloper.dto.CommentList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Comment;
import me.junholee.springbootdeveloper.domain.User;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String nickname;
    private String comment;
    private LocalDateTime createdDate;
    private Long articleId;
    private User user;
    private String image;

    public CommentResponse(Comment comment){
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.comment = comment.getComment();
        this.articleId = comment.getArticle().getId();
        this.createdDate = comment.getCreatedDate();
        this.user = comment.getUser();
        this.image = comment.getUser().getPicture();
    }
}
