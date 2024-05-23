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


    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "status")
    private String status;

    @Column(name = "match_day")
    private int match_day;

    @ManyToOne
    @JoinColumn(name = "HomeTeamId")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "AwayTeamId")
    private Team awayTeam;

    @Column(name = "winner")
    private long winner;

    @Column(columnDefinition = "integer default 0")
    private int home_score;

    @Column(columnDefinition = "integer default 0")
    private int away_score;

    @Column(name = "match_time")
    private String match_time;

    @Builder
    public Match(int away_score, int home_score, long winner, long id, String status, int match_day, Team homeTeam, Team awayTeam, String match_time){
        this.id = id;
        this.match_time = match_time;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.home_score = home_score;
        this.away_score = away_score;
        this.match_day = match_day;
        this.status = status;
        this.winner = winner;
    }
}
