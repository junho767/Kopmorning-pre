package me.junholee.springbootdeveloper.service.Comment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.Comment;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.Comment.CommentRequest;
import me.junholee.springbootdeveloper.repository.BlogRepository;
import me.junholee.springbootdeveloper.repository.CommentRepository;
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;
    private final UserService userService;
    @Transactional
    public Long commentSave(String username, Long id, CommentRequest request){
        User user = userService.findByUserName(username);
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

    public Comment findById(long id) { return commentRepository.findById(id).orElse(null); }

    @Transactional
    public Comment CommentUpdate(long id,String content){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        comment.CommentUpdate(content);
        return comment;
    }
}
