package me.junholee.springbootdeveloper.service.Blog;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.Article;
import me.junholee.springbootdeveloper.domain.ArticleImage;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.Articles.AddArticleRequest;
import me.junholee.springbootdeveloper.dto.Articles.ArticleListViewResponse;
import me.junholee.springbootdeveloper.dto.Articles.UpdateArticleRequest;
import me.junholee.springbootdeveloper.dto.ImageDTO.ArticleImageUploadDTO;
import me.junholee.springbootdeveloper.repository.ArticleImageRepository;
import me.junholee.springbootdeveloper.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final ArticleImageRepository articleImageRepository;

    @Value("${file.ArticleImagePath}")
    private String uploadFolder;
    @Transactional
    public Article save(AddArticleRequest request, ArticleImageUploadDTO imageUploadDTO, User user) {
        Article result = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .articleType(request.getArticleType())
                .user(user)
                .build();

        blogRepository.save(result);

        for(MultipartFile file : imageUploadDTO.getFiles()) {
            if(file.isEmpty()){
                break;
            }
            UUID uuid = UUID.randomUUID();
            String article_image_name = uuid + "_" + file.getOriginalFilename();
            File destinationFile = new File(uploadFolder + article_image_name);
            try {
                file.transferTo(destinationFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ArticleImage image = ArticleImage.builder()
                    .url(article_image_name)
                    .article(result)
                    .build();
            articleImageRepository.save(image);
        }
        return result;
    }
    public Page<ArticleListViewResponse> paging(Pageable pageable){
        int page = pageable.getPageNumber() -1;
        int pageSize = 10;
        Page<Article> articlePages = blogRepository.findAll(PageRequest.of(page,pageSize, Sort.by(Sort.Direction.DESC,"id")));
        return articlePages.map(ArticleListViewResponse::new);
    }

    @Transactional
    public Page<ArticleListViewResponse> searchByKeyword(String keyword,Pageable pageable){
        int page = pageable.getPageNumber() -1;
        int pageSize = 10;
        Page<Article> searchPages = blogRepository.findByTitleContaining(
                keyword,
                PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id"))
        );
        return searchPages.map(ArticleListViewResponse::new);
    }

    public Page<ArticleListViewResponse> findByArticleType(String articleType,Pageable pageable){
        int page = pageable.getPageNumber() -1;
        int pageSize = 10;
        Page<Article> articles_type = blogRepository.findByArticleType(
                articleType,
                PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id"))
        );
        return articles_type.map(ArticleListViewResponse::new);
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void updateView(long id){
        Optional<Article> article = this.blogRepository.findById(id);
        Article article1 = article.get();
        article1.setViewCount(article1.getViewCount()+1);
        this.blogRepository.save(article1);
    }
    public void delete(long id,String username) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        authorizeArticleAuthor(article,username);
        blogRepository.delete(article);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request,String userName) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        authorizeArticleAuthor(article,userName);
        article.update(request.getTitle(), request.getContent());
        return article;
    }

    // 게시글을 작성한 유저인지 확인
    private void authorizeArticleAuthor(Article article,String userName) {
        if (!article.getUser().getUsername().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }

}
