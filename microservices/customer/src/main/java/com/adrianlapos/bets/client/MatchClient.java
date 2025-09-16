package com.adrianlapos.bets.client;

import com.adrianlapos.bets.model.TeamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "matches")
public interface MatchClient {
    @GetMapping("api/matches/{id}")
    TeamDTO getMatchById(@PathVariable("id") Long id);

    @GetMapping("api/matches/all}")
    List<TeamDTO> getAllMatches();
}
