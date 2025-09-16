package com.adrianlapos.bets.service;

import com.adrianlapos.bets.model.BetslipDraft;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class BetslipDraftService {

    RedisTemplate<String, Object> redisTemplate;

    public BetslipDraftService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveDraft(Long userId, BetslipDraft draft) {
        String key = "betslip:" + userId;
        redisTemplate.opsForValue().set(key, draft, Duration.ofMinutes(30)); // auto-expire
    }

    public BetslipDraft getDraft(Long userId) {
        String key = "betslip:" + userId;
        return (BetslipDraft) redisTemplate.opsForValue().get(key); // auto-expire
    }

    public void deleteDraft(Long userId) {
        redisTemplate.delete("betslip:" + userId);
    }

}



