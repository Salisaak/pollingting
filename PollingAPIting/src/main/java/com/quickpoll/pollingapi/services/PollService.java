package com.quickpoll.pollingapi.services;

import com.quickpoll.pollingapi.domain.Poll;
import com.quickpoll.pollingapi.exception.ResourceNotFoundException;
import com.quickpoll.pollingapi.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {

        Poll poll = pollRepository.findById(pollId).orElse(null);

        if(poll == null) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }

    }

    public ResponseEntity<Iterable<Poll>> getAllPolls() {

        Iterable<Poll> allPolls = pollRepository.findAll();

        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);

    }

    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {

        poll = pollRepository.save(poll);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

    }

    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {

        verifyPoll(pollId);

        Poll p = pollRepository.findById(pollId).orElse(null);

        if(p == null) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }

        return new ResponseEntity<>(p, HttpStatus.OK);

    }

    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {

        verifyPoll(pollId);

        Poll p = pollRepository.save(poll); // 'Saves' the entity

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {

        verifyPoll(pollId);

        pollRepository.deleteById(pollId);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
