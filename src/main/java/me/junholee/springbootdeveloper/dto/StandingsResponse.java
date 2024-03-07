package me.junholee.springbootdeveloper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Standings;


@NoArgsConstructor
@Getter
public class StandingsResponse {

    private int position;
    private String team_tla;
    private int played_games;
    private int goal_difference;
    private int won;
    private int draw;
    private int points;
    private String team_crest;

    public StandingsResponse(Standings standings) {
        this.position = standings.getPosition();
        this.team_tla = standings.getTeam_tla();
        this.played_games = standings.getPlayedGames();
        this.team_crest = standings.getTeam_crest();
        this.goal_difference = standings.getGoalDifference();
        this.won = standings.getWon();
        this.draw = standings.getDraw();
        this.points = standings.getPoints();
    }
}
