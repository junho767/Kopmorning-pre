package me.junholee.springbootdeveloper.dto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsRequest {
    private static final String URL = "https://www.liverpoolfc.com/news?categoryId=186";

    @Scheduled(fixedRate = 6000000)
    public List<NewsResponseDTO> fetchArticles() {
        List<NewsResponseDTO> newslist = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements articles = doc.select("div.css-6n72ux div a");

            for (Element article : articles) {
                String rawTitle = article.text(); // 원본 제목 추출

                String link = article.attr("abs:href");
                Elements categoryElementsH2 = article.select("div div h2 span.css-12j2mzt");
                String categoryH2 = categoryElementsH2.text();
                Elements timeElements = article.select("div div time span.css-ia5owy");
                String time = timeElements.text();
                // 이미지 링크 추출
                String imgSrc = extractImageSource(article, doc);
                if(categoryElementsH2.isEmpty()){

                    Elements categoryElementsH3 = article.select("div div h3 span.css-12j2mzt");
                    String categoryH3 = categoryElementsH3.text();
                    String title = removeTimeAndCategory(rawTitle,time,categoryH3);
                    NewsResponseDTO news = NewsResponseDTO.builder()
                            .image(imgSrc)
                            .category(categoryH3)
                            .url(link)
                            .time(time)
                            .title(title)
                            .build();
                    newslist.add(news);

                }
                else{
                    String title = removeTimeAndCategory(rawTitle,time,categoryH2);
                    NewsResponseDTO news = NewsResponseDTO.builder()
                            .image(imgSrc)
                            .category(categoryH2)
                            .url(link)
                            .time(time)
                            .title(title)
                            .build();
                    newslist.add(news);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newslist;
    }

    // 시간과 카테고리 정보를 제거하는 메소드
    public static String removeTimeAndCategory(String rawTitle,String time,String category) {
        String title1 = rawTitle.substring(time.length()+2);
        return title1.substring(category.length()+1);
    }

    // 이미지 소스 추출을 위한 메소드
    public static String extractImageSource(Element article, Document doc) {
        Elements imgElements = article.select("img");
        for (Element img : imgElements) {
            String src = img.attr("abs:src");
            if (!src.isEmpty() && !src.startsWith("data:image")) {
                return src;
            }

            String srcset = img.attr("srcset");
            if (!srcset.isEmpty()) {
                String[] sources = srcset.split(",")[0].split(" ");
                if (sources.length > 0) {
                    return sources[0];
                }
            }
        }

        Elements globalImages = doc.select("img");
        for (Element img : globalImages) {
            String src = img.attr("abs:src");
            if (!src.isEmpty() && !src.startsWith("data:image")) {
                return src;
            }
        }
        return "";
    }
}
