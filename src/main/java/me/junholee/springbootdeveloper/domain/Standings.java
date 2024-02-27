package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Standings")
@NoArgsConstructor
public class Standings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goals_for")
    private int goalsFor;

    @Column(name = "form")
    private String form;

    @Column(name = "lost")
    private int lost;

    @Column(name = "won")
    private int won;

    @Column(name = "played_games")
    private int playedGames;

    @Column(name = "position")
    private int position;

    @Column(name = "team_shortName")
    private String team_shortName;

    @Column(name = "draw")
    private int draw;

    @Column(name = "goals_against")
    private int goalsAgainst;

    @Column(name = "goal_difference")
    private int goalDifference;

    @Column(name = "points")
    private int points;

    @Builder
    public Standings(int position, String team_shortName, int playedGames, int won, int draw, int goalDifference,int goalsAgainst,int goalsFor, int points, String form){
        this.position = position;
        this.team_shortName = team_shortName;
        this.playedGames = playedGames;
        this.won = won;
        this.draw = draw;
        this.form = form;
        this.goalDifference = goalDifference;
        this.goalsAgainst = goalsAgainst;
        this.goalsFor = goalsFor;
        this.points = points;
    }
}

