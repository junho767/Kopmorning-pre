package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Team")
@EntityListeners(AuditingEntityListener.class)
public class Team {

    @Id //엔티티 클래스에서 기본 키(primary key)를 나타냅니다
    @Column(name = "id")
    private long id;

    @Column(name = "team_name")
    private String team_name;

    @Column(name = "team_tla")
    private String team_tla;

    @Column(name = "team_shortName")
    private String team_shortName;

    @Column(name = "team_crest")
    private String team_crest;

    @Column(name = "venue")
    private String venue;

    @Builder
    public Team(long id, String team_name, String team_tla, String team_shortName, String team_crest, String venue){
        this.id = id;
        this.team_name = team_name;
        this.team_tla = team_tla;
        this.team_shortName = team_shortName;
        this.team_crest = team_crest;
        this.venue = venue;
    }
}