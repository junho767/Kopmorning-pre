package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@Entity
@Table(name = "Standings")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Standings {

    @Id
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
    public Standings(Team team, int position ,int playedGames, int won, int draw, int goalDifference,int goalsAgainst,int goalsFor, int points, String form, int lost){
        this.position = position;
        this.lost = lost;
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

