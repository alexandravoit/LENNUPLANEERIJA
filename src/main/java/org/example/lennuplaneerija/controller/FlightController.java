package org.example.lennuplaneerija.controller;

import org.example.lennuplaneerija.dto.FlightDTO;
import org.example.lennuplaneerija.dto.RequestDTO;
import org.example.lennuplaneerija.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
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

    @PostMapping("/flights/filter")
    public List<FlightDTO> filterFlights(@RequestBody RequestDTO request) {
        List<FlightDTO> filtered = flightService.filterByDate(request.getFlights(), request.getDate());
        return flightService.filterByPriceRange(filtered, request.getPriceRange());
    }
}
