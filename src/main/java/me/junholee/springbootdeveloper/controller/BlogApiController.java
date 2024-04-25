package me.junholee.springbootdeveloper.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.SessionUser;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.Articles.AddArticleRequest;
import me.junholee.springbootdeveloper.dto.Articles.UpdateArticleRequest;
import me.junholee.springbootdeveloper.dto.ImageDTO.ArticleImageUploadDTO;
import me.junholee.springbootdeveloper.dto.User1.UpdateUserRequest;
import me.junholee.springbootdeveloper.service.Blog.BlogService;
import me.junholee.springbootdeveloper.service.Blog.ImageService;
import me.junholee.springbootdeveloper.service.Member.UserDetailService;
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@Controller
public class BlogApiController {

    private final BlogService blogService;
    private final UserService userService;
    private final HttpSession httpSession;
    // "/api/articles" 경로로 POST 요청이 들어왔을 때 실행되는 메서드입니다. 여기서 주요한 기능은 새로운 글을 추가하고 그 결과를 응답하는 것입니다.
    @PostMapping("/api/articles") // 글 생성
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request
                                            , @ModelAttribute ArticleImageUploadDTO imageUploadDTO) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        User user = userService.findByEmail(sessionUser.getEmail());
        Article savedArticle = blogService.save(request,imageUploadDTO, user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }
//    @GetMapping("/api/articles") // 글 조회
//    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
//        List<ArticleResponse> articles= blogService.findAll()
//                .stream()
//                .map(ArticleResponse::new)
//                .toList();
//        return ResponseEntity.ok()
//                .body(articles);
//    }
//    @GetMapping("/api/articles/{id}")  //
//    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){ // @PathVariable 에너테이션은  URL에서 값을 가져오는 것.
//        Article article = blogService.findById(id);
//        return ResponseEntity.ok()
//                .body(new ArticleResponse(article));
//    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);
        return ResponseEntity.ok()
                .build();
    }
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
    @PostMapping("/api/myProFil")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserRequest request){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        String email = user.getEmail();
        User updateUser = userService.UpdateUser(email, request);

        return ResponseEntity.ok()
                .body(updateUser);
    }
}