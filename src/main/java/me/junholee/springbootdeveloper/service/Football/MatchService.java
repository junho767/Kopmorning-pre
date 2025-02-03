package me.junholee.springbootdeveloper.service.Football;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.Constant.FootBallRequestUrl;
import me.junholee.springbootdeveloper.dto.Football.Match.Matches;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final ObjectMapper objectMapper;

    @Value("${football.api.key}")
    private String apiKey;

    public List<Matches> getMatchList(long leagueId) {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(matchListRequestUrl(leagueId));
            request.setHeader("X-Auth-Token", apiKey);
            CloseableHttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
            String jsonResponse = EntityUtils.toString(entity);
            JsonNode node = objectMapper.readTree(jsonResponse);
            JsonNode matches = node.get("matches");

            return Collections.singletonList(objectMapper.treeToValue(matches, Matches.class));

        } catch (IOException e) {
            throw new RuntimeException("API 호출 실패", e);
        }
    }

    private String matchListRequestUrl(long leagueId) {
        return String.format(FootBallRequestUrl.MATCH_LIST_REQUEST_URL, leagueId);
    }
}
