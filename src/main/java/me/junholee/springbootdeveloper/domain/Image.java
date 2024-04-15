package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_ID")
    private User user;

    public void updateUrl(String url) {
        this.url = url;
    }
}
