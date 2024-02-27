package me.junholee.springbootdeveloper.service;

import me.junholee.springbootdeveloper.domain.Standings;
import me.junholee.springbootdeveloper.repository.StandingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StandingService {
    private StandingRepository standingRepository;
    @Autowired
    public StandingService(StandingRepository standingRepository) {
        this.standingRepository = standingRepository;
    }
    public Standings saveStandings(Standings standings) {
       return standingRepository.save(standings);
    }

    public List<Standings> findAll(){ return standingRepository.findAll(); }
}
