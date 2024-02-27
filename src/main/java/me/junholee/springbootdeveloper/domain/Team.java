package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Team")
public class Team {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @Column(name = "team_id")
    private Long team_id;
    @Column(name = "team_name")
    private String team_name;

    @Column(name = "team_tla")
    private String team_tla;

    @Column(name = "team_shortName")
    private String team_shortName;

    @Column(name = "team_crest")
    private String team_crest;

    @Builder
    public Team(Long team_id, String team_name, String team_tla, String team_shortName, String team_crest){
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_tla = team_tla;
        this.team_shortName = team_shortName;
        this.team_crest = team_crest;
    }
}