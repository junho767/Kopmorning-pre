package me.junholee.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.config.oauth.PrincipalDetails;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.Articles.ArticleListViewResponse;
import me.junholee.springbootdeveloper.dto.Articles.ArticleViewResponse;
import me.junholee.springbootdeveloper.dto.Comment.CommentResponseDTO;
import me.junholee.springbootdeveloper.dto.News.NewsRequest;
import me.junholee.springbootdeveloper.dto.News.NewsResponseDTO;
import me.junholee.springbootdeveloper.service.Blog.BlogService;
import me.junholee.springbootdeveloper.service.Football.StandingService;
import me.junholee.springbootdeveloper.service.Like.LikeService;
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;
    private final UserService userService;
    private final LikeService likeService;
    private final NewsRequest newsRequest;
    @GetMapping("/articles")
    public String getArticles(@PageableDefault(page = 1)Pageable pageable,
                              String keyword,
                              Model model,
                              String articleType,
                              @AuthenticationPrincipal PrincipalDetails principalDetails){
        Page<ArticleListViewResponse> articlePages;
        if(keyword == null){
            articlePages = blogService.paging(pageable);
        }
        else {
            articlePages = blogService.searchByKeyword(keyword,pageable);
        }
        int blockLimit = 5;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), articlePages.getTotalPages());
        model.addAttribute("category", articleType);
        model.addAttribute("articlePages", articlePages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }
        return "articles";
    }
    @GetMapping("/articles/free")
    public String getFreeArticles(@PageableDefault(page = 1)Pageable pageable,
                                  String keyword,
                                  Model model,
                                  @AuthenticationPrincipal PrincipalDetails principalDetails){
        String articleType = "자유 게시판";
        Page<ArticleListViewResponse> articlePages;
        if(keyword == null){
            articlePages = blogService.findByArticleType(articleType,pageable);
        }
        else {
            articlePages = blogService.findByArticleType(articleType,pageable);
        }
        int blockLimit = 5;

        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), articlePages.getTotalPages());
        model.addAttribute("category", articleType);
        model.addAttribute("articlePages", articlePages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }
        return "free-articles";
    }
    @GetMapping("/articles/news")
    public String getNewArticles(@PageableDefault(page = 1)Pageable pageable,
                                  String keyword,
                                  Model model,
                                  @AuthenticationPrincipal PrincipalDetails principalDetails){
        String articleType = "News";
        Page<ArticleListViewResponse> articlePages;
        if(keyword == null){
            articlePages = blogService.findByArticleType(articleType,pageable);
        }
        else {
            articlePages = blogService.findByArticleType(articleType,pageable);
        }
        int blockLimit = 5;

        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), articlePages.getTotalPages());
        model.addAttribute("category", articleType);
        model.addAttribute("articlePages", articlePages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }
        return "news-articles";
    }
    @GetMapping("/articles/liverpool")
    public String getLiverpoolArticles(@PageableDefault(page = 1)Pageable pageable,
                                  String keyword,
                                  Model model,
                                  @AuthenticationPrincipal PrincipalDetails principalDetails){
        String articleType = "liverpool";
        Page<ArticleListViewResponse> articlePages;
        if(keyword == null){
            articlePages = blogService.findByArticleType(articleType,pageable);
        }
        else {
            articlePages = blogService.findByArticleType(articleType,pageable);
        }
        int blockLimit = 5;

        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), articlePages.getTotalPages());
        model.addAttribute("category", articleType);
        model.addAttribute("articlePages", articlePages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }
        return "liverpool-articles";
    }
    @GetMapping("/history")
    public String getHistory(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }

        return "teamhistory";
    }
    @GetMapping("/")
    public String getMain(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<NewsResponseDTO> newsList = newsRequest.fetchArticles();
        List<NewsResponseDTO> limitList = newsList.subList(0,Math.min(newsList.size(),5));
        model.addAttribute("news",limitList);

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }


        return "index";
    }

//    @GetMapping("/match/{id}")
//    public String getMatch(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
//
//        Match match = matchService.findById(id);
//        MatchResponesDTO matchInfo = new MatchResponesDTO(match);
//
//        if(principalDetails != null) {
//            User user= userService.findByEmail(principalDetails.getUser().getEmail());
//            model.addAttribute("user", user);
//        }
//
//        List<StandingsResponseDTO> standingsList = standingService.findAll().stream()
//                .map(StandingsResponseDTO::new)
//                .toList();
//
//
//        model.addAttribute("standing",standingsList);
//        model.addAttribute("match",matchInfo);
//
//        return "match";
//    }
    @GetMapping("/notice")
    public String getNotice(Model model){
        return "notice";
    }

    @GetMapping("/myprofil")
    public String getMyProFil(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }

        return "myprofil";
    }

    // 주어진 ID에 해당하는 게시물을 찾고,
    // 그 게시물을 ArticleViewResponse로 변환하여 모델에 추가한 후 "article" 뷰를 반환합니다.
    // 따라서 이 코드는 특정 ID에 해당하는 게시물을 가져와서 해당 정보를 뷰로 전달하는 역할

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model,@AuthenticationPrincipal PrincipalDetails principalDetails) {

        Article article = blogService.findById(id);
        ArticleViewResponse dto = new ArticleViewResponse(article);
        List<CommentResponseDTO> commentList = dto.getComment();
        List<String> articleImages = dto.getImageUrl();

        if(articleImages != null && !articleImages.isEmpty()){
            model.addAttribute("article_image",articleImages);
        }

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
            model.addAttribute("likesArticle",likeService.existArticleAndUSer(article,user));
        }

        if (commentList != null && !commentList.isEmpty()) {
            model.addAttribute("comment", commentList);
        } else {
            model.addAttribute("comment", Collections.emptyList()); // 빈 리스트를 추가
        }

        blogService.updateView(id);
        model.addAttribute("article", new ArticleViewResponse(article)); // article 객체에 저장

        return "article"; // article.html 뷰 조회
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id,
                             Model model,
                             @AuthenticationPrincipal PrincipalDetails principalDetails){ // id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑.
        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }
        if(id==null){ // id 가 없으면 생성
            model.addAttribute("article", new ArticleViewResponse());
        }
        else{ //id가 있으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
    @GetMapping("/story")
    public String getStory(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }

        return "story";
    }
}
