package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Match;
import me.junholee.springbootdeveloper.dto.Match.MatchResponesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Integer> {
    List<Match> findByHomeTeamIdOrAwayTeamId(long id2,long id1);
}
