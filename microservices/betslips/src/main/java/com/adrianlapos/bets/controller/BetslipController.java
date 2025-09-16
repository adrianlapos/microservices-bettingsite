package com.adrianlapos.bets.controller;

import com.adrianlapos.bets.entity.Betslip;
import com.adrianlapos.bets.model.Bet;
import com.adrianlapos.bets.model.BetslipDTO;
import com.adrianlapos.bets.model.BetslipDraft;
import com.adrianlapos.bets.service.BetslipDraftService;
import com.adrianlapos.bets.service.BetslipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/betslips")
public class BetslipController {

    private final BetslipService betslipService;
    private final BetslipDraftService betslipDraftService;

    public BetslipController(BetslipService betslipService, BetslipDraftService betslipDraftService){
        this.betslipService = betslipService;
        this.betslipDraftService = betslipDraftService;
    }
//

    @GetMapping("/all")
    public ResponseEntity<List<BetslipDTO>> getAllBetslips(@RequestHeader("X-User-Id") Long userId){
        List<BetslipDTO> betslips = betslipService.getAllBetslips(userId);
        return new ResponseEntity<>(betslips,HttpStatus.OK);
    }


    @GetMapping("/current")
    public ResponseEntity<BetslipDraft> getCurrentBetslip(@RequestHeader("X-User-Id") Long id){
        BetslipDraft betslipDraft = betslipDraftService.getDraft(id);
        if (betslipDraft == null) {
            betslipDraft = new BetslipDraft();
            betslipDraftService.saveDraft(id, betslipDraft);
        }
        return  new ResponseEntity<>(betslipDraft,HttpStatus.OK);
    }
    @PostMapping("/add-match")
    public ResponseEntity<BetslipDraft> addMatchToDraft(@RequestHeader("X-User-Id") Long id, @RequestBody Bet bet){
        BetslipDraft betslipDraft = betslipDraftService.getDraft(id);
        if (betslipDraft == null) {
            betslipDraft = new BetslipDraft();
            betslipDraftService.saveDraft(id, betslipDraft);
        }

        betslipDraft.getTips().add(bet);
        betslipDraftService.saveDraft(id,betslipDraft);
        return new ResponseEntity<>(betslipDraft,HttpStatus.OK);
    }
    @PutMapping("/change-stake")
    public ResponseEntity<BetslipDraft> changeStake(@RequestHeader("X-User-Id") Long id,@RequestParam("stake") double stake){
        BetslipDraft betslipDraft =betslipDraftService.getDraft(id);
        if (betslipDraft == null){
            betslipDraft = new BetslipDraft();
            betslipDraftService.saveDraft(id,betslipDraft);
        }
        betslipDraft.setStake(stake);
        betslipDraftService.saveDraft(id,betslipDraft);
        return  new ResponseEntity<>(betslipDraft,HttpStatus.OK);
    }
    @DeleteMapping("/current")
    public ResponseEntity<Void> resetDraft(@RequestHeader("X-User-Id") Long id) {
        betslipDraftService.deleteDraft(id);
        return ResponseEntity.noContent().build();
    }

}
