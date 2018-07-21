package com.prompt.marginplus.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prompt.marginplus.entities.State;

@Repository("stateRepo")
public interface StateRepository extends CrudRepository<State, String>{
}
