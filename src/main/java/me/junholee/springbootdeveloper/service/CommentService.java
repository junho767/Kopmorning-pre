package me.junholee.springbootdeveloper.service;

import me.junholee.springbootdeveloper.domain.Comment;
import me.junholee.springbootdeveloper.repository.ComentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private ComentRepository comentRepository;

    @Autowired
    public CommentService(ComentRepository comentRepository) { this.comentRepository = comentRepository; }
    public Comment SaveComment(Comment comment){
        return comentRepository.save(comment);
    }
}
