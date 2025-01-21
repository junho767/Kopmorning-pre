package me.junholee.springbootdeveloper.dto.Football.Team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SquadDTO {
    private long id;
    private String name;
    private String dateOfBirth;
    private String position;
    private String nationality;
}
