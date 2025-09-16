package com.adrianlapos.bets.controller;

import com.adrianlapos.clients.team.TeamClient;
import com.adrianlapos.bets.model.MatchDTO;
import com.adrianlapos.bets.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/matches")
public class MatchController {
    private final MatchService matchService;
    private final TeamClient teamClient;

    public MatchController(MatchService matchService, TeamClient teamClient){
        this.matchService = matchService;
        this.teamClient = teamClient;
    }

    @GetMapping("all")
    public ResponseEntity<List<MatchDTO>> getAll(@RequestHeader("X-User-Id") String userId){
        log.info("List of matches requested");
        System.out.println("user header id:" + userId);
        return new ResponseEntity<>(matchService.findAll(),HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<MatchDTO> addMatch(@RequestBody MatchDTO matchDTO){
        log.info("Match created between {} and {}",teamClient.getTeamById(matchDTO.getTeam1Id()).getName(), teamClient.getTeamById(matchDTO.getTeam2Id()).getName());
        return new ResponseEntity<>(matchService.createMatch(matchDTO), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<MatchDTO> getMatch(@PathVariable Long id){
        log.info("Match requested with id {}",id);
        return new ResponseEntity<>(matchService.findMatch(id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MatchDTO> deleteMatch(@PathVariable Long id){
        log.info("Match deleted with id {}",id);
        return new ResponseEntity<>(matchService.deleteMatch(id),HttpStatus.OK);
    }

}
