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

    @Column(name = "team_tla")
    private String team_tla;

    @Column(name = "draw")
    private int draw;

    @Column(name = "goals_against")
    private int goalsAgainst;

    @Column(name = "goal_difference")
    private int goalDifference;

    @Column(name = "points")
    private int points;

    @Column(name = "team_crest")
    private String team_crest;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Standings(Team team, int position, String team_tla ,int id,int playedGames, int won, int draw, int goalDifference,int goalsAgainst,int goalsFor, int points, String form, String team_crest){
        this.position = position;
        this.id = id;
        this.team = team;
        this.team_tla = team_tla;
        this.playedGames = playedGames;
        this.won = won;
        this.draw = draw;
        this.form = form;
        this.goalDifference = goalDifference;
        this.goalsAgainst = goalsAgainst;
        this.goalsFor = goalsFor;
        this.points = points;
        this.team_crest = team_crest;
    }
}

