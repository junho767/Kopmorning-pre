package me.junholee.springbootdeveloper.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import me.junholee.springbootdeveloper.domain.*;
import me.junholee.springbootdeveloper.dto.*;

import me.junholee.springbootdeveloper.service.CommentService;
import me.junholee.springbootdeveloper.service.MatchService;
import me.junholee.springbootdeveloper.service.StandingService;
import net.minidev.json.parser.ParseException;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
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
    private final HttpSession httpSession;
    private final StandingService standingService;
    private final MatchService matchService;
    private final CommentService commentService;
    @GetMapping("/articles")
    public String getArticles(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles); // 블로그 글 리스트에 저장
        return "articles";
    }

    @GetMapping("/main")
    public String getMain(Model model) {
        List<StandingsResponse> standingsList = standingService.findAll().stream()
                .map(StandingsResponse::new)
                .toList();
        List<MatchRespones> matchList = matchService.findAll().stream()
                .map(MatchRespones::new)
                .toList();
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("standing",standingsList);
        model.addAttribute("user", user);
        model.addAttribute("matchList",matchList);
        return "main";
    }

    @GetMapping("/schedule")
    public String getSchedule(Model model){
      List<MatchRespones> matchList = matchService.findAll().stream()
              .map(MatchRespones::new)
              .toList();
      List<StandingsResponse> standingsList = standingService.findAll().stream()
              .map(StandingsResponse::new)
              .toList();
      SessionUser user = (SessionUser) httpSession.getAttribute("user");
      model.addAttribute("matchList", matchList);
      model.addAttribute("user", user);
      model.addAttribute("standing",standingsList);
      return "schedule";
    }

    @GetMapping("/match/{id}")
    public String getMatch(@PathVariable int id, Model model){
        Match match = matchService.findById(id);
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        List<StandingsResponse> standingsList = standingService.findAll().stream()
                .map(StandingsResponse::new)
                .toList();
        model.addAttribute("standing",standingsList);
        model.addAttribute("match",match);
        model.addAttribute("user", user);
        return "match";
    }


    // 주어진 ID에 해당하는 게시물을 찾고,
    // 그 게시물을 ArticleViewResponse로 변환하여 모델에 추가한 후 "article" 뷰를 반환합니다.
    // 따라서 이 코드는 특정 ID에 해당하는 게시물을 가져와서 해당 정보를 뷰로 전달하는 역할

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        ArticleViewResponse dto = new ArticleViewResponse(article);
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        List<CommentResponse> commentList = dto.getComment();
        if(commentList!=null && !commentList.isEmpty()){
            model.addAttribute("comment",commentList);
        }
        blogService.updateView(id);
        model.addAttribute("user", user);
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
