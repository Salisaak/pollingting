package com.quickpoll.pollingapi.services;

import com.quickpoll.pollingapi.domain.Vote;
import com.quickpoll.pollingapi.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {

        vote = voteRepository.save(vote);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

    }

    public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
        return voteRepository.findByPoll(pollId);
    }

}
