package me.junholee.springbootdeveloper.service.Like;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.Likes;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.repository.LikeRepository;
import me.junholee.springbootdeveloper.service.Blog.BlogService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    private final BlogService blogService;
    private final LikeRepository likeRepository;
    public void addLike(User user, Long id){
        Article article = blogService.findById(id);
        // 해당 유저 정보와 글이 없다면 좋아요 증가  및 Like DB에 생성
        if(!likeRepository.existsByUserAndArticle(user,article)){
            article.setLikeCount(article.getLikeCount()+1);
            likeRepository.save(new Likes(user,article));
        }
        // 해당 유저 정보와 글이 있다면 좋아요 감소 및 Like DB에서 삭제
        else{
            article.setLikeCount(article.getLikeCount()-1);
            likeRepository.deleteByUserAndArticle(user,article);
        }
    }
}
