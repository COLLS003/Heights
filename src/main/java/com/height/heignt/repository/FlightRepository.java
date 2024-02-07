package com.height.heignt.repository;

import com.height.heignt.models.Flights;
import com.height.heignt.models.User;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository  extends CrudRepository<Flights, Integer> {

}
