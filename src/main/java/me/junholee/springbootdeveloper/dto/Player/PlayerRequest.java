package me.junholee.springbootdeveloper.dto.Player;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.Player;
import me.junholee.springbootdeveloper.domain.Team;
import me.junholee.springbootdeveloper.service.Football.PlayerService;
import me.junholee.springbootdeveloper.service.Football.TeamService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
//주로 생성자를 자동으로 생성하는 데 사용됩니다.
// 이 애노테이션을 사용하면 클래스의 필드를 기반으로한 생성자를 자동으로 생성해 줍니다.
@Component
public class PlayerRequest {
    private final TeamService teamService;
    private final PlayerService playerService;
    public void Player_request() throws ParseException, URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String api_url = "https://apiv3.apifootball.com/?action=get_teams&league_id=152";
        String apiKey = "91f7214ad9ff0500ece16da3c413f6d25ac0e88dd92b14b2c288a7e353d1570f";
        String url = api_url + "&APIkey=" + apiKey;
        RequestEntity<Void> req = RequestEntity.get(new URI(url)).build();
        ResponseEntity<String> resultBody = restTemplate.exchange(req, String.class);
        String jsonString = resultBody.getBody();
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonString);

        for(int i=0 ; i<20 ; i++){
            Team team;
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            JSONObject jsonVenue = (JSONObject) jsonObject.get("venue");
            JSONArray jsonPlayers = (JSONArray) jsonObject.get("players");

            String team_name = (String) jsonObject.get("team_name");
            String venue_name = (String) jsonVenue.get("venue_name");

            if(team_name.equals("Luton Town")){
                team = teamService.findById(389);
            } else if (team_name.equals("Brentford")) {
                team = teamService.findById(402);
            } else{
                team = teamService.findByVenue_teamId(venue_name);
            }
            // 선수 정보 저장 하는 반복문
            for(int j=0;j<jsonPlayers.size();j++){

                JSONObject jsonPlayer = (JSONObject) jsonPlayers.get(j);

                for(String key : jsonPlayer.keySet()){
                    if(jsonPlayer.getAsString(key).isEmpty()){
                        jsonPlayer.put(key,"0");
                    }
                }
                String player_name = (String) jsonPlayer.get("player_name");
                String player_image = (String) jsonPlayer.get("player_image");
                String player_injured = (String) jsonPlayer.get("player_injured");
                String player_type = (String) jsonPlayer.get("player_type");
                String player_birthdate = (String) jsonPlayer.get("player_birthdate");
                String player_age = (String) jsonPlayer.get("player_age");
                String player_id = (String) jsonPlayer.get("player_id");
                String player_rating = (String) jsonPlayer.get("player_rating");
                String player_number = (String) jsonPlayer.get("player_number");
                String player_match_played = (String) jsonPlayer.get("player_match_played");
                String player_substitutes_on_bench = (String) jsonPlayer.get("player_substitutes_on_bench");
                String player_goals = (String) jsonPlayer.get("player_goals");
                String player_assists= (String) jsonPlayer.get("player_assists");
                String player_passes = (String) jsonPlayer.get("player_passes");
                String player_dribble_attempts = (String) jsonPlayer.get("player_dribble_attempts");
                String player_dribble_succ = (String) jsonPlayer.get("player_dribble_succ");
                String player_key_passes = (String) jsonPlayer.get("player_key_passes");
                String player_tackles = (String) jsonPlayer.get("player_tackles");

                long player_key = Long.parseLong(player_id);
                int age = Integer.parseInt(player_age);
                float rating = Float.parseFloat(player_rating);
                int number = Integer.parseInt(player_number);
                int match_played = Integer.parseInt(player_match_played);
                int substitutes_on_bench = Integer.parseInt(player_substitutes_on_bench);
                int goals = Integer.parseInt(player_goals);
                int assists= Integer.parseInt(player_assists);
                int passes = Integer.parseInt(player_passes);
                int dribble_attempts = Integer.parseInt(player_dribble_attempts);
                int dribble_succ = Integer.parseInt(player_dribble_succ);
                int key_passes= Integer.parseInt(player_key_passes);
                int tackles = Integer.parseInt(player_tackles);



                Player player = Player.builder()
                        .team(team)
                        .player_age(age)
                        .player_birthdate(player_birthdate)
                        .player_assists(assists)
                        .player_dribble_succ(dribble_succ)
                        .player_goals(goals)
                        .player_image(player_image)
                        .player_injured(player_injured)
                        .player_key(player_key)
                        .player_key_passes(key_passes)
                        .player_match_played(match_played)
                        .player_name(player_name)
                        .player_number(number)
                        .player_passes(passes)
                        .player_rating(rating)
                        .player_substitutes_on_bench(substitutes_on_bench)
                        .player_tackles(tackles)
                        .player_type(player_type)
                        .player_dribble_attempts(dribble_attempts)
                        .build();

                playerService.save(player);
            }
        }
    }
}
