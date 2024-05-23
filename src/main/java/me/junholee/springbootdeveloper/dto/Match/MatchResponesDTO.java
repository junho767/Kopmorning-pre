package me.junholee.springbootdeveloper.dto.Match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Match;
import me.junholee.springbootdeveloper.domain.Team;

@NoArgsConstructor
@Getter
public class MatchResponesDTO {
    private long id;
    private Team homeTeam;
    private Team awayTeam;
    private int home_score;
    private int away_score;
    private int match_day;
    private String status;
    private long winner;
    private String match_time;

    public MatchResponesDTO(Match match){
        this.id = match.getId();
        this.match_time = match.getMatch_time();
        this.homeTeam = match.getHomeTeam();
        this.awayTeam = match.getAwayTeam();
        this.home_score = match.getHome_score();
        this.away_score = match.getAway_score();
        this.match_day = match.getMatch_day();
        this.status = match.getStatus();
        this.winner = match.getWinner();
    }
}
