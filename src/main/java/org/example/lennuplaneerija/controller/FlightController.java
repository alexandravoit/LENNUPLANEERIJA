package org.example.lennuplaneerija.controller;

import org.example.lennuplaneerija.dto.FlightDTO;
import org.example.lennuplaneerija.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public List<FlightDTO> getFlights(
            @RequestParam("originSkyId") String origin,
            @RequestParam("destinationSkyId") String destination) {
        try {
            return flightService.fetchFlights(origin, destination);
        } catch (Exception e) {
            return null;
        }
    }
}
