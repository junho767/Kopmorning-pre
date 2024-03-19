package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Game")
@NoArgsConstructor
public class Match {


    @Column(name = "id")
    private int id;

    @Column(name="league")
    private String league;

    @Column(name = "status")
    private String status;

    @Id
    @Column(name = "match_day")
    private int match_day;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team home_team;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team away_team;

    @Column(name = "winner")
    private String winner;

    @Column(columnDefinition = "integer default 0")
    private int home_score;

    @Column(columnDefinition = "integer default 0")
    private int away_score;

    @Column(name = "match_time")
    private String match_time;

    @Builder
    public Match(int away_score, int home_score, String winner, int id, String league, String status, int match_day, Team home_team, Team away_team, String match_time){
        this.match_time = match_time;
        this.id = id;
        this.league = league;
        this.status = status;
        this.match_day = match_day;
        this.home_team = home_team;
        this.away_team = away_team;
        this.home_score = home_score;
        this.away_score = away_score;
        this.winner = winner;
    }
}
