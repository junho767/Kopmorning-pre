package me.junholee.springbootdeveloper.dto.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.Comment;
import me.junholee.springbootdeveloper.domain.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private Long id;
    private String comment;
    private LocalDateTime createdDate;
    private User user;
    private Article article;


    @Builder
    // Dto -> Entity
    public Comment toEntity(){
        Comment comments = Comment.builder()
                .id(id)
                .comment(comment)
                .user(user)
                .article(article)
                .createdDate(createdDate)
                .build();

        return comments;
    }
}
