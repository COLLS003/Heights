package com.height.heignt.controller;


import com.height.heignt.models.Bookings;
import com.height.heignt.models.Flights;
import com.height.heignt.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/bookings")
public class BookingController {
    //initialise the booking bean
    @Autowired
    BookingRepository bookingRepository;

    @PostMapping(path = "/create")
    @ResponseBody
    ResponseEntity<?> addBooking(@RequestParam Integer userId, @RequestParam Integer flightId){
        //initialise a booking instance
        Bookings bookings = new Bookings();
        bookings.setUserId(userId);
        bookings.setFlightID(flightId);
        bookingRepository.save(bookings);
        return ResponseEntity.ok(bookings);
    }

    //get all the bookings
    @GetMapping(path = "/list")
    public @ResponseBody Iterable<Bookings> getAllBookings(){
        return bookingRepository.findAll();
    }

    @GetMapping(path = "/read/{id}")
    public ResponseEntity<Bookings> getFlightById(@PathVariable("id") Integer id) {
        Optional<Bookings> bookingsOptional = bookingRepository.getByUserId(id);
        return bookingsOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
