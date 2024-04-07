package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "player_key")
    private long player_key;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "player_image")
    private String player_image;

    @Column(name = "player_name")
    private String player_name;

    @Column(name = "player_injured")
    private String player_injured;

    @Column(name = "player_number")
    private int player_number;

    @Column(name = "player_type")
    private String player_type;

    @Column(name = "player_match_played")
    private int player_match_played;

    @Column(name = "player_goals")
    private int player_goals;

    @Column(name = "player_rating")
    private float player_rating;

    @Column(name = "player_substitutes_on_bench")
    private int player_substitutes_on_bench;

    @Column(name = "player_assists")
    private int player_assists;

    @Column(name = "player_passes")
    private int player_passes;

    @Column(name = "player_dribble_attempts")
    private int player_dribble_attempts;

    @Column(name = "player_dribble_succ")
    private int player_dribble_succ;

    @Column(name = "player_key_passes")
    private int player_key_passes;

    @Column(name = "player_tackles")
    private int player_tackles;

    @Column(name = "player_birthdate")
    private String player_birthdate;

    @Column(name = "player_age")
    private int player_age;

    @Builder
    public Player(Team team,int player_age ,long player_key, String player_image, String player_name, String player_injured, int player_number, String player_type, int player_match_played, int player_goals, float player_rating, int player_substitutes_on_bench, int player_assists, int player_passes, int player_dribble_attempts, int player_dribble_succ, int player_key_passes, int player_tackles, String player_birthdate) {
        this.team = team;
        this.player_age = player_age;
        this.player_key = player_key;
        this.player_image = player_image;
        this.player_name = player_name;
        this.player_injured = player_injured;
        this.player_number = player_number;
        this.player_type = player_type;
        this.player_match_played = player_match_played;
        this.player_goals = player_goals;
        this.player_rating = player_rating;
        this.player_substitutes_on_bench = player_substitutes_on_bench;
        this.player_assists = player_assists;
        this.player_passes = player_passes;
        this.player_dribble_attempts = player_dribble_attempts;
        this.player_dribble_succ = player_dribble_succ;
        this.player_key_passes = player_key_passes;
        this.player_tackles = player_tackles;
        this.player_birthdate = player_birthdate;
    }
}

