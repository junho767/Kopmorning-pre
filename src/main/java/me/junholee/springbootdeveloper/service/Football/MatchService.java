package me.junholee.springbootdeveloper.service.Football;

import me.junholee.springbootdeveloper.domain.Match;
import me.junholee.springbootdeveloper.dto.Match.MatchResponesDTO;
import me.junholee.springbootdeveloper.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public List<MatchResponesDTO> findHomeTeamOrAwayTeam(long id){
        List<Match> match = matchRepository.findByHomeTeamIdOrAwayTeamId(id, id);
        return match.stream().map(MatchResponesDTO::new).toList();
    }

    public Match findById(int match_day){
        return matchRepository.findById(match_day).orElse(null);
    }
}
