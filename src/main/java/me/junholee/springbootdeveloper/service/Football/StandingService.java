package me.junholee.springbootdeveloper.service.Football;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.Constant.FootBallConstant;
import me.junholee.springbootdeveloper.dto.Football.Standing.StandingsResponseDTO;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class StandingService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${football.api.key}")
    private String apiKey;

    public StandingsResponseDTO getStandings(long leagueId){
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(standingsUrl());
            request.setHeader("X-Auth-Token", apiKey);
            CloseableHttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
            String jsonResponse = EntityUtils.toString(entity);
            JsonNode node = objectMapper.readTree(jsonResponse);
            return objectMapper.treeToValue(node, StandingsResponseDTO.class);

        } catch (IOException e) {
            throw new RuntimeException("API 호출 실패", e);
        }
    }

    public String standingsUrl() {
//        return String.format(FootBallConstant.STANDINGS_REQUEST_URL, leagueId);
        return FootBallConstant.STANDINGS_REQUEST_URL;
    }
}
