package me.junholee.springbootdeveloper.service;

import me.junholee.springbootdeveloper.domain.Team;
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
    public Team findById(long id){
        return teamRepository.findById(id).orElse(null);
    }

}
