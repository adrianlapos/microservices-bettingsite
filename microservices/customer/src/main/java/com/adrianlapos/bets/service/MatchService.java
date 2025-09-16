package com.adrianlapos.bets.service;

import com.adrianlapos.clients.team.TeamClient;
import com.adrianlapos.clients.team.TeamDTO;
import com.adrianlapos.bets.entity.Match;
import com.adrianlapos.bets.model.MatchDTO;

import com.adrianlapos.bets.repo.MatchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamClient teamClient;
    public MatchService(MatchRepository matchRepository,TeamClient teamClient ){
        this.matchRepository = matchRepository;
        this.teamClient = teamClient;
    }

    public MatchDTO findMatch(Long id){
        Match matchDTO = matchRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return MatchDTO.MatchMapper.mapToDto(matchDTO);
    }
    public List<MatchDTO> findAll(){
        return matchRepository.findAll().stream().map(MatchDTO.MatchMapper::mapToDto).toList();
    }
    public MatchDTO createMatch(MatchDTO matchDTO){
        TeamDTO team1 = teamClient.getTeamById(matchDTO.getTeam1Id());
        TeamDTO team2 = teamClient.getTeamById(matchDTO.getTeam2Id());

        if (matchDTO.getTime() != null){
            Timestamp timestamp = Timestamp.valueOf(matchDTO.getTime().toLocalDateTime());
            matchDTO.setTime(timestamp);
        }
        Match match = new Match(matchDTO.getTeam1Id(),matchDTO.getTeam2Id(),matchDTO.getHome_odd(),matchDTO.getDraw_odd(),matchDTO.getAway_odd(),matchDTO.getSport(),matchDTO.getTime());
        matchRepository.save(match);
        return MatchDTO.MatchMapper.mapToDto(match);
    }

    public MatchDTO deleteMatch(Long id){
        Match match = matchRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        matchRepository.delete(match);
        return MatchDTO.MatchMapper.mapToDto(match);
    }
}
