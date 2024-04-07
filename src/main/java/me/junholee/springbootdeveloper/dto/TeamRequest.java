package me.junholee.springbootdeveloper.dto;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.Team;
import me.junholee.springbootdeveloper.service.TeamService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
//주로 생성자를 자동으로 생성하는 데 사용됩니다.
// 이 애노테이션을 사용하면 클래스의 필드를 기반으로한 생성자를 자동으로 생성해 줍니다.
@Component
//Spring 프레임워크에서 컴포넌트 스캔을 통해 해당 클래스를 스프링 애플리케이션 컨텍스트에 빈으로 등록하는 데 사용
public class TeamRequest {
    private final TeamService teamService;
    public void team_request() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> req = RequestEntity
                .get("http://api.football-data.org/v4/competitions/2021/teams")
                .header("X-Auth-Token", "43c685dacc6e4c6d986ed9ad8c1f20b5")
                .build();
        ResponseEntity<String> resultBody = restTemplate.exchange(req, String.class);
        String jsonString = resultBody.getBody();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
        JSONArray teams = (JSONArray) jsonObject.get("teams");

        for(int i=0; i< teams.size() ; i++){

            JSONObject jsonTeam = (JSONObject) teams.get(i);
            String team_name = (String) jsonTeam.get("name");
            int team_id = (int) jsonTeam.get("id");
            String team_tla = (String) jsonTeam.get("tla");
            String team_crest = (String) jsonTeam.get("crest");
            String team_shortName = (String) jsonTeam.get("shortName");
            String team_venue = (String) jsonTeam.get("venue");

            Team team = Team.builder()
                    .team_crest(team_crest)
                    .team_shortName(team_shortName)
                    .id((long) team_id)
                    .team_name(team_name)
                    .team_tla(team_tla)
                    .venue(team_venue)
                    .build();

            teamService.saveTeams(team);
        }
    }
}
