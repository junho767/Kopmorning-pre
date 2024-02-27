package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Object> {

}
