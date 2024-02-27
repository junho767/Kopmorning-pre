package me.junholee.springbootdeveloper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Standings;


@NoArgsConstructor
@Getter
public class StandingsResponse {

    private int position;
    private String team_shortName;
    private int played_games;
    private int goal_difference;
    private int won;
    private int draw;
    private int points;

    public StandingsResponse(Standings standings) {
        this.position = standings.getPosition();
        this.team_shortName = standings.getTeam_shortName();
        this.played_games = standings.getPlayedGames();
        this.goal_difference = standings.getGoalDifference();
        this.won = standings.getWon();
        this.draw = standings.getDraw();
        this.points = standings.getPoints();
    }
}
