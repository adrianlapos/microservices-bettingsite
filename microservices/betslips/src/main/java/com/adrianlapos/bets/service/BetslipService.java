package com.adrianlapos.bets.service;

import com.adrianlapos.bets.entity.Betslip;
import com.adrianlapos.bets.model.Bet;
import com.adrianlapos.bets.model.BetslipDTO;
import com.adrianlapos.bets.model.BetslipDraft;
import com.adrianlapos.bets.model.State;
import com.adrianlapos.bets.repo.BetslipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetslipService {
    private final BetslipRepository betslipRepository;
    private final BetslipDraftService betslipDraftService;
    public BetslipService(BetslipRepository betslipRepository, BetslipDraftService betslipDraftService){
        this.betslipRepository = betslipRepository;
        this.betslipDraftService = betslipDraftService;
    }

    public BetslipDTO submitBetslipDraft(Long id){
        BetslipDraft betslipDraft = betslipDraftService.getDraft(id);
        if (betslipDraft == null){
            throw new RuntimeException("Betslip cant be loaded to submit");
        }
        Betslip betslip = new Betslip(betslipDraft.getStake(),betslipDraft.getTips(),id);
        betslipRepository.save(betslip);
        BetslipDTO betslipDTO = BetslipDTO.BetslipDTOMapper.mapToDTO(betslip);
        return  betslipDTO;
    }

    public List<BetslipDTO> getAllBetslips(Long userId){
        List<Betslip> betslips = betslipRepository.findBetslipsById(userId);
        return betslips.stream().map(BetslipDTO.BetslipDTOMapper::mapToDTO).toList();
    }

    public List<BetslipDTO> getWonBetslips(Long userId){
        List<Betslip> betslips = betslipRepository.findBetslipsByIdAndState(userId, State.VYHERNY);
        return betslips.stream().map(BetslipDTO.BetslipDTOMapper::mapToDTO).toList();
    }


}
