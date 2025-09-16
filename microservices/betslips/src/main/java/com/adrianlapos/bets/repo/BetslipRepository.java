package com.adrianlapos.bets.repo;

import com.adrianlapos.bets.entity.Betslip;
import com.adrianlapos.bets.model.Bet;
import com.adrianlapos.bets.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetslipRepository extends JpaRepository<Betslip,Long> {
    List<Betslip> findBetslipsById(Long id);
    List<Betslip> findBetslipsByIdAndState(Long id, State state);
}
