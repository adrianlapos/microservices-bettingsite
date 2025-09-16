package com.adrianlapos.clients.team;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="teams",
url = "localhost:8081"
)
public interface TeamClient {
    @GetMapping("api/teams/{teamId}")
    TeamDTO getTeamById(@PathVariable("teamId") Long id);

    @GetMapping("api/teams/all")
    List<TeamDTO> getAllTeams();
}
