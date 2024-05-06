package me.junholee.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.config.oauth.PrincipalDetails;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import me.junholee.springbootdeveloper.dto.Articles.AddArticleRequest;
import me.junholee.springbootdeveloper.dto.Articles.ArticleResponse;
import me.junholee.springbootdeveloper.dto.Articles.UpdateArticleRequest;
import me.junholee.springbootdeveloper.dto.ImageDTO.ArticleImageUploadDTO;
import me.junholee.springbootdeveloper.service.Blog.BlogService;
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogApiController {

    private final BlogService blogService;
    private final UserService userService;
    // "/api/articles" 경로로 POST 요청이 들어왔을 때 실행되는 메서드입니다. 여기서 주요한 기능은 새로운 글을 추가하고 그 결과를 응답하는 것입니다.
    @PostMapping("/api/articles") // 글 생성
    public String addArticle(@RequestPart("title") String title,
                             @RequestPart("content") String content,
                             @ModelAttribute ArticleImageUploadDTO imageUploadDTO,
                             @AuthenticationPrincipal PrincipalDetails principalDetails) {

        AddArticleRequest request  = new AddArticleRequest(title, content);
        User user= userService.findByEmail(principalDetails.getUser().getEmail());
        blogService.save(request,imageUploadDTO, user);

        return "redirect:/articles";

    }
    @GetMapping("/api/articles") // 글 조회
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles= blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(articles);
    }
    @GetMapping("/api/articles/{id}")  //
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){ // @PathVariable 에너테이션은  URL에서 값을 가져오는 것.
        Article article = blogService.findById(id);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id,Principal principal){
        blogService.delete(id, principal.getName());
        return ResponseEntity.ok()
                .build();
    }
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request,
                                                 Principal principal) {
        Article updatedArticle = blogService.update(id, request, principal.getName());
        return ResponseEntity.ok()
                .body(updatedArticle);
    }

}