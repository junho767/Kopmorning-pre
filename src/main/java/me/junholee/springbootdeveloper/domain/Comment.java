package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user_id;

    @Column(name = "comment")
    private String comment;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "Article_id")
    private Article article_id;

    @Builder
    public Comment(int id, User user_id, String comment){
        this.id = id;
        this.user_id = user_id;
        this.comment = comment;
    }
}
