package me.junholee.springbootdeveloper.service.Like;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.Comment;
import me.junholee.springbootdeveloper.domain.Likes;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.repository.LikeRepository;
import me.junholee.springbootdeveloper.service.Blog.BlogService;
import me.junholee.springbootdeveloper.service.Comment.CommentService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    private final BlogService blogService;
    private final CommentService commentService;
    private final LikeRepository likeRepository;
    public void articleLike(User user, Long id){
        Article article = blogService.findById(id);
        // 해당 유저 정보와 글이 없다면 좋아요 증가  및 Like DB에 생성
        if(!likeRepository.existsByUserAndArticle(user,article)){
            likeRepository.save(new Likes(user,article));
        }
        // 해당 유저 정보와 글이 있다면 좋아요 감소 및 Like DB에서 삭제
        else{
            likeRepository.deleteByUserAndArticle(user,article);
        }
    }
    public void commentLike(User user, Long id){
        Comment comment = commentService.findById(id);
        // 해당 유저 정보와 글이 없다면 좋아요 증가  및 Like DB에 생성
        if(!likeRepository.existsByUserAndComment(user,comment)){
            likeRepository.save(new Likes(user,comment));
        }
        // 해당 유저 정보와 글이 있다면 좋아요 감소 및 Like DB에서 삭제
        else{
            likeRepository.deleteByUserAndComment(user,comment);
        }
    }
}
