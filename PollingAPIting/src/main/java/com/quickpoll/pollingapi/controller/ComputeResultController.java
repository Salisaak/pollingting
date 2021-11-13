package com.quickpoll.pollingapi.controller;

import com.quickpoll.pollingapi.services.ComputeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeResultController {

    @Autowired
    private ComputeResultService computeResultService;

    @RequestMapping(value="/computeresult", method=RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        return computeResultService.computeResult(pollId);
    }

}
