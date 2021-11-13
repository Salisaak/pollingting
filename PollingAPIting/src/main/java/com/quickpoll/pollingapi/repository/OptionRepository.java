package com.quickpoll.pollingapi.repository;

import com.quickpoll.pollingapi.domain.AOption;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<AOption, Long> {

}
