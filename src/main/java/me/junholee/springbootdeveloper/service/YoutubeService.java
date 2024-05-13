//package me.junholee.springbootdeveloper.service;
//
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.youtube.YouTube;
//import com.google.api.services.youtube.model.SearchListResponse;
//import com.google.api.services.youtube.model.SearchResult;
//import com.google.api.services.youtube.model.SearchResultSnippet;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//public class YoutubeService {
//
//    //@Value 어노테이션을 사용하여 application.yml에서 정의한 YouTube API 키를 주입 받음
//    @Value("${spring.youtube.api.key}")
//    private String apiKey;
//    private String Channel_id = "UC9LQwHZoucFT94I2h6JOcjw";
//    public List<String> Video_List() throws IOException, GeneralSecurityException {
//        YouTube youtubeService = new YouTube.Builder(
//                GoogleNetHttpTransport.newTrustedTransport(),
//                JacksonFactory.getDefaultInstance(),
//                null)
//                .setApplicationName("youtube-search")
//                .build();
//
//        YouTube.Search.List request = youtubeService.search()
//                .list(Collections.singletonList("snippet"))
//                .setKey(apiKey)
//                .setChannelId(Channel_id)
//                .setMaxResults(10L)
//                .setOrder("date");
//        List<String> videoIds= new ArrayList<>();
//        SearchListResponse response = request.execute();
//
//        List<SearchResult> items = response.getItems();
//        int count = 0; // 최대 3개의 동영상 ID를 저장하기 위한 카운터
//
//        if (items != null) {
//            for (SearchResult item : items) {
//                SearchResultSnippet snippet = item.getSnippet();
//                if ((snippet != null && snippet.getTitle() != null) && (snippet.getTitle().contains("Women") || !snippet.getTitle().contains("&#39;"))) {
//                    // 제목에 "Women"이 포함되어 있다면 해당 아이템을 건너뜁니다.
//                    continue;
//                }
//                String videoId = item.getId().getVideoId();
//                videoIds.add(videoId);
//                count++;
//                if(count == 3){
//                    break;
//                }
//            }
//        }
//        return videoIds;
//    }
//}
