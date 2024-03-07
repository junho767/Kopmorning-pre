package me.junholee.springbootdeveloper.service;

import me.junholee.springbootdeveloper.domain.Standings;
import me.junholee.springbootdeveloper.repository.StandingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StandingService {
    private final StandingRepository standingRepository;
    @Autowired
    public StandingService(StandingRepository standingRepository) {
        this.standingRepository = standingRepository;
    }
    public Standings saveStandings(Standings standings) {
       return standingRepository.save(standings);
    }

    public List<Standings> findAll(){
        return standingRepository.findAll(Sort.by(Sort.Direction.ASC, "position"));
        // position 필드를 기준으로 오름차순하여 정렬.
    }
    public Standings findByid(int id){
        return standingRepository.findById(id).orElse(null);
    }
}
