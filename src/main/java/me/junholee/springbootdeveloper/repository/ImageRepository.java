package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ArticleImage,Long> {
}
