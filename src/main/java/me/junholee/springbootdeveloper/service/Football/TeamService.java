package me.junholee.springbootdeveloper.service.Football;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.Constant.FootBallRequestUrl;
import me.junholee.springbootdeveloper.dto.Football.Team.TeamResponseDTO;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${football.api.key}")
    private String apiKey;

    public TeamResponseDTO getTeamInfo(long teamId) {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(teamRequestUrl(teamId));
            request.setHeader("X-Auth-Token", apiKey);
            CloseableHttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
            String jsonResponse = EntityUtils.toString(entity);
            JsonNode node = objectMapper.readTree(jsonResponse);
            return objectMapper.treeToValue(node, TeamResponseDTO.class);

        } catch (IOException e) {
            throw new RuntimeException("API 호출 실패", e);
        }
    }

    public String teamRequestUrl(long teamId) {
        return String.format(FootBallRequestUrl.TEAM_REQUEST_URL, teamId);
    }
}
