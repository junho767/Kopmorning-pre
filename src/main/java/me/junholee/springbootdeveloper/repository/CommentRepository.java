package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Comment> findById(long id);
}
