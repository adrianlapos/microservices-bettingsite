package com.adrianlapos.bets.repo;

import com.adrianlapos.bets.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends JpaRepository<Bet,Long> {
}