package me.junholee.springbootdeveloper.controller;

import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.dto.ArticleViewResponse;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.dto.ArticleListViewResponse;
import me.junholee.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;
    @GetMapping("/articles")
    public String getArticles(Model model){ // Model 객체는 HTML 쪽으로 값을 넘겨주는 객체이다.
        List<ArticleListViewResponse> articles = blogService.findAll().stream().map(ArticleListViewResponse::new).toList();
        model.addAttribute("articles", articles); // 블로그 글 리스트에 저장
        return "articleList";
    }


    // 주어진 ID에 해당하는 게시물을 찾고,
    // 그 게시물을 ArticleViewResponse로 변환하여 모델에 추가한 후 "article" 뷰를 반환합니다.
    // 따라서 이 코드는 특정 ID에 해당하는 게시물을 가져와서 해당 정보를 뷰로 전달하는 역할

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article)); // article 객체에 저장

        return "article"; // article.html 뷰 조회
    }
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id,Model model){ // id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑.
        if(id==null){ // id 가 없으면 생성
            model.addAttribute("article", new ArticleViewResponse());
        }
        else{ //id가 있으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}
