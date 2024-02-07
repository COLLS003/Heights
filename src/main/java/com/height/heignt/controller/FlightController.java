package com.height.heignt.controller;

import com.height.heignt.models.Flights;
import com.height.heignt.models.User;
import com.height.heignt.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(path =  "/flights")
public class FlightController {

    //get the flight controller beam
    @Autowired
    FlightRepository flightRepository;

    //create a new flight

    /*

    |
     */

    @PostMapping(path = "/create")
    @ResponseBody
    public ResponseEntity<?> createFlight(
            @RequestParam String origin,
            @RequestParam Float price,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime arrivalTime,
            @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime departureTime) {
        // Initialize a flight instance
        Flights flight = new Flights();
        flight.setOrigin(origin);
        flight.setPrice(price);
        flight.setDestination(destination);
        flight.setDate(date);
        flight.setArrivalTime(arrivalTime);
        flight.setDepartureTime(departureTime);
        flightRepository.save(flight);
        return ResponseEntity.ok(flight); // Return flight details
    }

    //get all the flights
    @GetMapping(path = "/list")
    public @ResponseBody Iterable<Flights> getAllflighs(){
        return flightRepository.findAll();
    }

    //get flight by id
    @GetMapping(path = "/read/{id}")
    public ResponseEntity<Flights> getFlightById(@PathVariable("id") Integer id) {
        Optional<Flights> flightOptional = flightRepository.findById(id);
        return flightOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }




}
