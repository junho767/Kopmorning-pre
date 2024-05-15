package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Team;
import me.junholee.springbootdeveloper.dto.Team.TeamResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByVenue(String venue);
}
