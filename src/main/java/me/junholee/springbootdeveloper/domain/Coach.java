package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Coach")
@EntityListeners(AuditingEntityListener.class)
public class Coach {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "Last_name")
    private String Last_name;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "nationality")
    private String nationality;

    @Builder
    public Coach(long id, String name,String Last_name, String dateOfBirth, String nationality){
        this.id = id;
        this.name = name;
        this.Last_name = Last_name;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }
}
