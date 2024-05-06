package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ArticleImage")
@Builder
public class ArticleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "Article_ID")
    private Article article;
}
