package me.junholee.springbootdeveloper.dto.Football.Team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
public class CoachDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String name;
    private String dateOfBirth;
    private String nationality;
    private ContractDTO contract;
}
