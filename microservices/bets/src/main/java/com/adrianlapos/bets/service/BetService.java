package com.adrianlapos.bets.service;


import javax.persistence.EntityNotFoundException;

import com.adrianlapos.bets.repo.BetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BetService {

    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final BetslipRepository betslipRepository;

    public BetService(BetRepository betRepository, UserRepository userRepository, BetslipRepository betslipRepository) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.betslipRepository = betslipRepository;
    }

    public Bet getBet(Long id) {
        return betRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Betslip makeBet(Long userId, double stake, List<Bet> tips) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty())
            throw new EntityNotFoundException();

        User user = userOpt.get();
        if (user.getBalance() < stake)
            throw new IllegalArgumentException("not enough balance");
        Betslip betSlip = new Betslip();
        betSlip.setBets(tips);
        betSlip.setUser(user);
        betSlip.setStake(stake);
        double balance = user.getBalance();
        user.setBalance(balance - stake);
        for (Bet bet : tips) {
            bet.setBetslip(betSlip);
        }
        userRepository.save(user);
        if(true) {
            throw new RuntimeException("Forced failure to test rollback");
        }
        return betslipRepository.save(betSlip);
    }

}
