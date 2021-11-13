package com.quickpoll.pollingapi.repository;

import com.quickpoll.pollingapi.domain.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll, Long> {

}




