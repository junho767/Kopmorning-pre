package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "Standings")
@NoArgsConstructor
public class Standings {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "position")
    private int position;

    @Column(name = "played_games")
    private int playedGames;

    @Column(name = "form")
    private String form;

    @Column(name = "won")
    private int won;

    @Column(name = "lost")
    private int lost;

    @Column(name = "goals_for")
    private int goalsFor;

    @Column(name = "draw")
    private int draw;

    @Column(name = "goals_against")
    private int goalsAgainst;

    @Column(name = "goal_difference")
    private int goalDifference;

    @Column(name = "points")
    private int points;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Standings(Team team, int position ,int playedGames, int won, int draw, int goalDifference,int goalsAgainst,int goalsFor, int points, String form){
        this.position = position;
        this.team = team;
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

