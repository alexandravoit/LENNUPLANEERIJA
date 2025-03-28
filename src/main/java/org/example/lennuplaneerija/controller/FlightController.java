package org.example.lennuplaneerija.controller;

import org.example.lennuplaneerija.dto.FlightDTO;
import org.example.lennuplaneerija.dto.FlightRequestDTO;
import org.example.lennuplaneerija.dto.FlightResponseDTO;
import org.example.lennuplaneerija.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public FlightResponseDTO getFlights(
            @RequestParam("originSkyId") String origin,
            @RequestParam("destinationSkyId") String destination) {
        try {
            List<FlightDTO> flights = flightService.fetchFlights(origin, destination);

            List<String> uniqueDates = flights.stream()
                    .map(flight -> flight.getDeparture().split(" kell ")[0])
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            return new FlightResponseDTO(flights, uniqueDates);

        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/flights/filter")
    public List<FlightDTO> filterFlights(@RequestBody FlightRequestDTO request) {
        List<FlightDTO> filtered = flightService.filterByDate(request.getFlights(), request.getDate());
        return flightService.filterByPriceRange(filtered, request.getPriceRange());
    }
}
