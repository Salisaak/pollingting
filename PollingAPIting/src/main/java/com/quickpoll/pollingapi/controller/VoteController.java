package com.quickpoll.pollingapi.controller;

import com.quickpoll.pollingapi.domain.Vote;
import com.quickpoll.pollingapi.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.POST)
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
        return voteService.createVote(pollId, vote);
    }

    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
    public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
        return voteService.getAllVotes(pollId);
    }

}
