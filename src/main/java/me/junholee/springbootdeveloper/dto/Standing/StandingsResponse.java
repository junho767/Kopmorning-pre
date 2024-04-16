package me.junholee.springbootdeveloper.dto.Standing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Standings;
import me.junholee.springbootdeveloper.domain.Team;


@NoArgsConstructor
@Getter
public class StandingsResponse {

    private int position;
    private int played_games;
    private int goal_difference;
    private int won;
    private int draw;
    private int points;
    private Team team;

    public StandingsResponse(Standings standings) {
        this.position = standings.getPosition();
        this.played_games = standings.getPlayedGames();
        this.goal_difference = standings.getGoalDifference();
        this.won = standings.getWon();
        this.draw = standings.getDraw();
        this.points = standings.getPoints();
        this.team = standings.getTeam();
    }
}
