package me.junholee.springbootdeveloper.dto.Player;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.Player;
import me.junholee.springbootdeveloper.repository.PlayerRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@RequiredArgsConstructor
//주로 생성자를 자동으로 생성하는 데 사용됩니다.
// 이 애노테이션을 사용하면 클래스의 필드를 기반으로한 생성자를 자동으로 생성해 줍니다.
@Component
public class PlyerNationalityRequest {
    private final PlayerRepository playerRepository;
    public void PlayerNationalityRequest() throws ParseException, URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> nationality_req = RequestEntity
                .get("http://api.football-data.org/v4/competitions/PL/teams")
                .header("X-Auth-Token", "43c685dacc6e4c6d986ed9ad8c1f20b5")
                .build();
        ResponseEntity<String> nationality_response = restTemplate.exchange(nationality_req, String.class);
        String jsonString = nationality_response.getBody();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
        JSONArray jsonArray = (JSONArray) jsonObject.get("teams");
        for(int i=0 ; i<20 ;i++){
            JSONObject jsonTeams = (JSONObject) jsonArray.get(i);
            int teamId = (int) jsonTeams.get("id");
            JSONArray jsonSquad = (JSONArray) jsonTeams.get("squad");

            for(int j=0; j< jsonSquad.size(); j++){
                JSONObject jsonSquad1 = (JSONObject) jsonSquad.get(j);
                String Nationality = (String) jsonSquad1.get("nationality");
                Integer shirtNumber = (Integer) jsonSquad1.get("shirtNumber");
                if (shirtNumber == null){
                    continue;
                }
                Player player = playerRepository.findByTeamIdAndPlayerNumber((long)teamId,shirtNumber);
                if(player == null){
                    continue;
                }
                else{
                    player.updateNationality(Nationality);
                    playerRepository.save(player);
                }
            }
        }
    }
}
