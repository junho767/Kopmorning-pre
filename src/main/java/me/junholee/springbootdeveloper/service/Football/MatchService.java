package me.junholee.springbootdeveloper.service.Football;

import me.junholee.springbootdeveloper.domain.Match;
import me.junholee.springbootdeveloper.dto.Match.MatchResponesDTO;
import me.junholee.springbootdeveloper.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

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
    public MatchResponesDTO nextMatch(List<MatchResponesDTO> matchList){
        LocalDate now = LocalDate.now();
        for(MatchResponesDTO match : matchList){
            String matchTime1 = match.getMatch_time();
            String matchTime2 = matchTime1.substring(0,10);
            LocalDate time = LocalDate.parse(matchTime2);
            if(time.isAfter(now)){
                return match;
            }
        }
        return null;
    }
    public MatchResponesDTO recentMatch(List<MatchResponesDTO> matchList){
        LocalDate now = LocalDate.now();
        int index=0;
        for(int i=0; i< matchList.size(); i++){
            index=i;
            String matchTime1 = matchList.get(i).getMatch_time();
            String matchTime2 = matchTime1.substring(0,10);
            LocalDate time = LocalDate.parse(matchTime2);
            if(time.isAfter(now) && (Objects.equals(matchList.get(i - 1).getStatus(), "FINISHED"))){
                return matchList.get(i-1);
            }
        }
        return matchList.get(index);
    }
    public Match findById(int match_day){
        return matchRepository.findById(match_day).orElse(null);
    }
}
