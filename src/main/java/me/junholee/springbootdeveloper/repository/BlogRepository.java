package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Article, Long> {
    Page<Article> findByTitleContaining(String keyword,Pageable pageable);
    Page<Article> findByArticleType(String articleType,Pageable pageable);
}
