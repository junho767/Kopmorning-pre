package me.junholee.springbootdeveloper.domain;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "integer default 0",nullable = false)
    private int viewCount;

    @Column(columnDefinition = "integer default 0",nullable = false)
    private int likeCount;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // mappedBy="comment"는 comment 엔티티와 일대다 관계에서 종속 엔티티가 주인 엔티티의 어떤 필드에 매핑되어 있는지를 나타냅니다
    // 부모 엔티티가 삭제될 때 자식 엔티티에 대해 수행할 작업을 설정합니다. cascade
    // 이 속성은 해당 관계에 대한 데이터를 가져오는 전략을 설정합니다. EAGER는 즉시 로딩을 의미하며, 관계가 있는 엔티티를 함께 로딩합니다.
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comment;

//    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    @OrderBy("id asc")
//    private List<ArticleImage> articleImage;

    @Builder
    public Article(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}