package me.junholee.springbootdeveloper.dto.Standing;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.Standings;
import me.junholee.springbootdeveloper.domain.Team;
import me.junholee.springbootdeveloper.repository.TeamRepository;
import me.junholee.springbootdeveloper.service.Football.StandingService;
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
public class StandingsRequest {
    private final StandingService standingService;
    private final TeamRepository teamRepository;
    public void addStandings() throws ParseException {

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> req = RequestEntity
                .get("http://api.football-data.org/v4/competitions/2021/standings")
                .header("X-Auth-Token", "43c685dacc6e4c6d986ed9ad8c1f20b5")
                .build();
        ResponseEntity<String> resultBody = restTemplate.exchange(req, String.class);
        String jsonString = resultBody.getBody();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
        JSONArray standing = (JSONArray) jsonObject.get("standings");
        JSONObject innerJson1 = (JSONObject) standing.get(0);
        JSONArray table = (JSONArray) innerJson1.get("table");

        for (int i = 0; i < table.size(); i++) {
            JSONObject jsonStanding = (JSONObject) table.get(i);
            JSONObject jsonTeam = (JSONObject) jsonStanding.get("team");

            int id = (int) jsonTeam.get("id");
            Team team = teamRepository.findById((long) id).orElse(null);
            int position = (int) jsonStanding.get("position");
            String form = (String) jsonStanding.get("form");
            int playedGames = (int) jsonStanding.get("playedGames");
            int won = (int) jsonStanding.get("won");
            int draw = (int) jsonStanding.get("draw");
            int goalDifference = (int) jsonStanding.get("goalDifference");
            int goalAgainst = (int) jsonStanding.get("goalsAgainst");
            int goalsFor = (int) jsonStanding.get("goalsFor");
            int points = (int) jsonStanding.get("points");


            Standings standings = Standings.builder()
                    .position(position)
                    .playedGames(playedGames)
                    .form(form)
                    .team(team)
                    .goalsFor(goalsFor)
                    .goalsAgainst(goalAgainst)
                    .won(won)
                    .draw(draw)
                    .goalDifference(goalDifference)
                    .points(points)
                    .build();

            standingService.saveStandings(standings);
        }
    }
}
