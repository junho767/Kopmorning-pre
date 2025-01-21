package me.junholee.springbootdeveloper.dto.Football.Match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import me.junholee.springbootdeveloper.dto.Football.Team.TeamDTO;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
public class Matches {
    public long id;
    public TeamDTO homeTeam;
    public TeamDTO awayTeam;
    public ScoreDTO score;
}
