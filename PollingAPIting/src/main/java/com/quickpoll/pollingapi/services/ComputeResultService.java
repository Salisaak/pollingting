package com.quickpoll.pollingapi.services;

import com.quickpoll.pollingapi.domain.Vote;
import com.quickpoll.pollingapi.dto.OptionCount;
import com.quickpoll.pollingapi.dto.VoteResult;
import com.quickpoll.pollingapi.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Service
public class ComputeResultService {

    @Autowired
    private VoteRepository voteRepository;

    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);

        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();

        for(Vote v : allVotes) {

            totalVotes ++;

            OptionCount optionCount = tempMap.get(v.getOption().getId());

            if(optionCount == null) {

                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);

            }
            optionCount.setCount(optionCount.getCount()+1);
        }

        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());

        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);

    }

}
