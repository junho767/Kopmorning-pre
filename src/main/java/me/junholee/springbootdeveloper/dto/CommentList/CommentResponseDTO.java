package me.junholee.springbootdeveloper.dto.CommentList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Comment;
import me.junholee.springbootdeveloper.domain.User;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDTO {
    private Long id;
    private String username;
    private String comment;
    private LocalDateTime createdDate;
    private Long articleId;
    private User user;
    private String image;
    private int likesCount;

    public CommentResponseDTO(Comment comment){
        this.id = comment.getId();
        this.username = comment.getUser().getUsername();
        this.comment = comment.getComment();
        this.articleId = comment.getArticle().getId();
        this.createdDate = comment.getCreatedDate();
        this.user = comment.getUser();
        this.image = comment.getUser().getPicture();
        this.likesCount = comment.getLikes().size();
    }
}
