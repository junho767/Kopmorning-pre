package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.Comment;
import me.junholee.springbootdeveloper.domain.Likes;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.CommentList.CommentResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes,Long> {
    boolean existsByUserAndArticle(User user, Article article);
    void deleteByUserAndArticle(User user, Article article);
    boolean existsByUserAndComment(User user, CommentResponseDTO comment);
    void deleteByUserAndComment(User user, Comment comment);
}
