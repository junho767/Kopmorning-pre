package me.junholee.springbootdeveloper.dto.Standing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Standings;
import me.junholee.springbootdeveloper.domain.Team;


@NoArgsConstructor
@Getter
public class StandingsResponseDTO {

    private long id;
    private int position;
    private int played_games;
    private int goal_difference;
    private int won;
    private int draw;
    private int lost;
    private int points;
    private int goals_for;
    private int goals_against;
    private Team team;

    public StandingsResponseDTO(Standings standings) {
        this.id = standings.getTeam().getId();
        this.lost =standings.getLost();
        this.position = standings.getPosition();
        this.played_games = standings.getPlayedGames();
        this.goal_difference = standings.getGoalDifference();
        this.won = standings.getWon();
        this.goals_against = standings.getGoalsAgainst();
        this.goals_for = standings.getGoalsFor();
        this.draw = standings.getDraw();
        this.points = standings.getPoints();
        this.team = standings.getTeam();
    }
}
