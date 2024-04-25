package me.junholee.springbootdeveloper.service.Comment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.Comment;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.CommentList.CommentRequest;
import me.junholee.springbootdeveloper.repository.BlogRepository;
import me.junholee.springbootdeveloper.repository.CommentRepository;
import me.junholee.springbootdeveloper.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    @Transactional
    public Long commentSave(String email, Long id, CommentRequest request){
        User user = userRepository.findByEmail(email).orElse(null);
        Article article = blogRepository.findById(id).orElse(null);
        request.setUser(user);
        request.setArticle(article);
        Comment comment = request.toEntity();
        commentRepository.save(comment);
        return request.getId();
    }
    public void delete(long id){
        commentRepository.deleteById(id);
    }
    @Transactional
    public Comment CommentUpdate(long id,String content){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        comment.CommentUpdate(content);
        return comment;
    }
}
