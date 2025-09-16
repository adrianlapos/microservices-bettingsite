package com.adrianlapos.teams.controller;


import com.adrianlapos.teams.entity.Team;
import com.adrianlapos.teams.model.TeamDTO;
import com.adrianlapos.teams.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/teams")
public class TeamController {
    private final Logger logger = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;

    public TeamController(TeamService teamService){
        this.teamService = teamService;
    }

    @GetMapping(path = "{teamId}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable("teamId") Long id){
        logger.info("getTeamById called for id {}",id);
        Team team = teamService.getTeam(id);
        TeamDTO teamDTO = new TeamDTO(team.getName());
        return new ResponseEntity<>(teamDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/add",
    consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Team> addTeam(@RequestBody TeamDTO teamDTO){
        logger.info("Team created with name{}",teamDTO.getName());
        Team team = new Team(teamDTO.getName());
        return new ResponseEntity<>(teamService.createTeam(team),HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Team>> getTeams( ){

//        String xHeader = request.getHeader("X-Forwarded-For");
//        String addr;
//        if (xHeader == null)
//            addr=  request.getRemoteAddr();
//        else addr = xHeader.split(",")[0];
//
//        logger.info("List of teams retrieved from ip {}",addr);
//        System.out.println("type of servlet req " + request.getClass().getName());
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while  (headerNames.hasMoreElements()){
//            String name = headerNames.nextElement();
//            System.out.println("Header in servlet with name " + name);
//            System.out.println("its value " +request.getHeader(name) +"\n") ;
//        }
        List<Team> teams =  teamService.findAll();
        return new ResponseEntity<>(teams,HttpStatus.OK);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long teamId
                                            ) throws IOException {
        logger.info("Team deleted with id {}" , teamId);
        teamService.deleteTeam(teamId);
        return new ResponseEntity<>("Team deleted", HttpStatus.OK);
    }
 }
