package com.adrianlapos.teams.service;

import com.adrianlapos.teams.entity.Team;
import com.adrianlapos.teams.repo.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public Team getTeam(Long id){
        return teamRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity not found"));
    }

    public Team createTeam(Team team){
        return teamRepository.save(team);
    }

    public List<Team> findAll(){
        return teamRepository.findAll();
    }

    public void deleteTeam(Long id){
        teamRepository.deleteById(id);
    }
}
