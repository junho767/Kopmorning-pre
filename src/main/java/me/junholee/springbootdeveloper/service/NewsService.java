package me.junholee.springbootdeveloper.service;

import me.junholee.springbootdeveloper.dto.NewsRequest;
import me.junholee.springbootdeveloper.dto.NewsResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    private NewsRequest newsRequest;
    public List<NewsResponseDTO> NewsRequest(){
        return newsRequest.fetchArticles();
    }
}
