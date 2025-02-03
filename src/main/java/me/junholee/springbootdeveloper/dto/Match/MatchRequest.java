//package me.junholee.springbootdeveloper.dto.Match;
//
//import lombok.RequiredArgsConstructor;
//import me.junholee.springbootdeveloper.dto.Football.MatchDTO.Match;
//import me.junholee.springbootdeveloper.domain.FootBall.Team;
//import me.junholee.springbootdeveloper.repository.TeamRepository;
//import me.junholee.springbootdeveloper.service.Football.MatchService;
//import net.minidev.json.JSONArray;
//import net.minidev.json.JSONObject;
//import net.minidev.json.parser.JSONParser;
//import net.minidev.json.parser.ParseException;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Objects;
//
//@RequiredArgsConstructor
////주로 생성자를 자동으로 생성하는 데 사용됩니다.
//// 이 애노테이션을 사용하면 클래스의 필드를 기반으로한 생성자를 자동으로 생성해 줍니다.
//@Component
////Spring 프레임워크에서 컴포넌트 스캔을 통해 해당 클래스를 스프링 애플리케이션 컨텍스트에 빈으로 등록하는 데 사용
//public class MatchRequest {
//    private final TeamRepository teamRepository;
//    private final MatchService matchService;
//    public void matchRequest() throws ParseException {
//        RestTemplate restTemplate = new RestTemplate();
//        RequestEntity<Void> match_req = RequestEntity
//                .get("http://api.football-data.org/v4/competitions/2021/matches")
//                .header("X-Auth-Token", "43c685dacc6e4c6d986ed9ad8c1f20b5")
//                .build();
//        ResponseEntity<String> match_rep = restTemplate.exchange(match_req, String.class);
//        String match_body = match_rep.getBody();
//        JSONParser jsonParser = new JSONParser();
//        JSONObject jsonObject = (JSONObject) jsonParser.parse(match_body);
//        JSONArray jsonMatch = (JSONArray) jsonObject.get("matches");
//        for(int i=0;i<jsonMatch.size();i++){
//
//            JSONObject objectMatch = (JSONObject) jsonMatch.get(i);
//            JSONObject objectHomeTeam = (JSONObject) objectMatch.get("homeTeam");
//            JSONObject objectAwayTeam = (JSONObject) objectMatch.get("awayTeam");
//            JSONObject objectResult = (JSONObject) objectMatch.get("score");
//            JSONObject objectFullTime = (JSONObject) objectResult.get("fullTime");
//            JSONObject objectHalfTime = (JSONObject) objectResult.get("halfTime");
//            int homeTeam_id = (int) objectHomeTeam.get("id");
//            int awayTeam_id = (int) objectAwayTeam.get("id");
//            Team home_team = teamRepository.findById((long) homeTeam_id).orElse(null);
//            Team away_team = teamRepository.findById((long) awayTeam_id).orElse(null);
//            String match_time = (String) objectMatch.get("utcDate");
//            int match_id = (int) objectMatch.get("id");
//            int match_day = (int) objectMatch.get("matchday");
//            String status = (String) objectMatch.get("status");
//
//            ZonedDateTime zonedDateTime = ZonedDateTime.parse(match_time);
//            ZonedDateTime koreaDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"));
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//            String GameTime = koreaDateTime.format(formatter);
//
//            if(status.equals("FINISHED")){
//                String winner = (String) objectResult.get("winner");
//                long winner_id = 0;
//                int home_score = (int) objectFullTime.get("home");
//                int away_score = (int) objectFullTime.get("away");
//                if(Objects.equals(winner, "HOME_TEAM")){
//                    winner_id = homeTeam_id;
//                }
//                else if(Objects.equals(winner,"AWAY_TEAM")){
//                    winner_id = awayTeam_id;
//                }
//                else if(Objects.equals(winner,"DRAW")){
//                    winner_id = 0;
//                }
//                Match match = Match.builder()
//                        .homeTeam(home_team) // 홈 팀 객체
//                        .awayTeam(away_team) // 어웨이 팀 객체
//                        .id(match_id) // 매치 ID
//                        .match_day(match_day) // 매치 날짜
//                        .status(status) // 매치 상태
//                        .winner(winner_id) // 매치 승자
//                        .home_score(home_score) // 홈 팀 점수
//                        .away_score(away_score) // 어웨이 팀 점수
//                        .match_time(GameTime) // 경기 시간
//                        .build();
//                matchService.saveMatch(match); //DB에 저장
//            }
//            else{
//                long winner_id = 0;
//                int home_score = 0;
//                int away_score = 0;
//                Match match = Match.builder()
//                        .homeTeam(home_team) // 홈 팀 객체
//                        .awayTeam(away_team) // 어웨이 팀 객체
//                        .id(match_id) // 매치 ID
//                        .match_day(match_day) // 매치 날짜
//                        .status(status) // 매치 상태
//                        .winner(winner_id) // 매치 승자
//                        .home_score(home_score) // 홈 팀 점수
//                        .away_score(away_score) // 어웨이 팀 점수
//                        .match_time(GameTime) // 경기 시간
//                        .build();
//                matchService.saveMatch(match);
//            }
//        }
//    }
//}
