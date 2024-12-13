package me.junholee.springbootdeveloper.dto.Football.Standing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.junholee.springbootdeveloper.dto.Football.Team.TeamDTO;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
public class TableDTO {
    public long position;
    public TeamDTO team;
    public long playedGames;
    public String form;
    public long won;
    public long draw;
    public long lost;
    public long points;
    public long goalsFor; // 득점 수
    public long goalsAgainst;  // 실점 수
    public long goalDifference; // 득실차
}
