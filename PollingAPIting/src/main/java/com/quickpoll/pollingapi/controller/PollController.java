package com.quickpoll.pollingapi.controller;

import javax.validation.Valid;

import com.quickpoll.pollingapi.domain.Poll;
import com.quickpoll.pollingapi.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;

    @RequestMapping(value="/polls", method= RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        return pollService.getAllPolls();
    }

    @RequestMapping(value="/polls", method=RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        return pollService.getPoll(pollId);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        return pollService.updatePoll(poll, pollId);
    }


    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        return pollService.deletePoll(pollId);
    }


}
