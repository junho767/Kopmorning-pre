package me.junholee.springbootdeveloper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Match;
import me.junholee.springbootdeveloper.domain.Team;

@NoArgsConstructor
@Getter
public class MatchRespones {
    private Team home;
    private Team away;
    private int home_score;
    private int away_score;
    private int match_day;
    private String status;
    private String winner;
    private String match_time;

    public MatchRespones(Match match){
        this.match_time = match.getMatch_time();
        this.home = match.getHome_team();
        this.away = match.getAway_team();
        this.home_score = match.getHome_score();
        this.away_score = match.getAway_score();
        this.match_day = match.getMatch_day();
        this.status = match.getStatus();
        this.winner = match.getWinner();
    }
}
