package com.height.heignt.repository;

import com.height.heignt.models.Bookings;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookingRepository extends CrudRepository<Bookings, Integer> {
    Optional<Bookings> getByUserId(Integer userId); // Corrected attribute name to match entity
}
