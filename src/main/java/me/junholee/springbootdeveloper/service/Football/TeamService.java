package me.junholee.springbootdeveloper.service.Football;

import me.junholee.springbootdeveloper.domain.Team;
import me.junholee.springbootdeveloper.dto.Team.TeamResponseDTO;
import me.junholee.springbootdeveloper.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    public Team saveTeams(Team team) {
        return teamRepository.save(team);
    }

    public Team findByVenue_teamId(String venue) {
        return teamRepository.findByVenue(venue);
    }
    public TeamResponseDTO findById(long id){
        Team team= teamRepository.findById(id).orElse(null);
        return new TeamResponseDTO(team);
    }
}
