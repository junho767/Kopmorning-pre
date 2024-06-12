package me.junholee.springbootdeveloper.service.Football;

import me.junholee.springbootdeveloper.dto.News.NewsRequest;
import me.junholee.springbootdeveloper.dto.News.NewsResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    private NewsRequest newsRequest;
    public List<NewsResponseDTO> NewsRequest(){
        return newsRequest.fetchArticles();
    }
}
