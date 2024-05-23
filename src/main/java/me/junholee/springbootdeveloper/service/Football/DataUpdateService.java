//package me.junholee.springbootdeveloper.service.Football;
//
//import me.junholee.springbootdeveloper.dto.Match.MatchRequest;
//import me.junholee.springbootdeveloper.dto.Player.PlayerNationalityRequest;
//import me.junholee.springbootdeveloper.dto.Player.PlayerRequest;
//import me.junholee.springbootdeveloper.dto.Standing.StandingsRequest;
//import me.junholee.springbootdeveloper.dto.Team.TeamRequest;
//import net.minidev.json.parser.ParseException;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.net.URISyntaxException;
//
//@Service
//public class DataUpdateService {
//
//    private final TeamRequest teamRequest;
//    private final StandingsRequest standingsRequest;
//    private final MatchRequest matchRequest;
//    private final PlayerRequest playerRequest;
//    private final PlayerNationalityRequest playerNationalityRequest;
//
//    public DataUpdateService(TeamRequest teamRequest, StandingsRequest standingsRequest, MatchRequest matchRequest, PlayerRequest playerRequest, PlayerNationalityRequest playerNationalityRequest) {
//        this.teamRequest = teamRequest;
//        this.standingsRequest = standingsRequest;
//        this.matchRequest = matchRequest;
//        this.playerRequest = playerRequest;
//        this.playerNationalityRequest = playerNationalityRequest;
//    }
//    @Scheduled(fixedRate = 86400000) // 86400000 ms = 24 hours
//    public void updateData() throws ParseException, URISyntaxException {
//        teamRequest.team_request();
//        standingsRequest.addStandings();
//        matchRequest.matchRequest();
//        playerRequest.Player_request();
//        playerNationalityRequest.PlayerNationalityRequest();
//        System.out.println("24시간 경과 Data updated at: " + new java.util.Date());
//    }
//}
