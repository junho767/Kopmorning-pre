package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Standings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StandingRepository extends JpaRepository<Standings,Object> {
    Optional<Standings> findByTeamId(int id);
}
