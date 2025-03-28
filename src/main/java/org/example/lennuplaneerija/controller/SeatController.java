package org.example.lennuplaneerija.controller;
import org.example.lennuplaneerija.dto.FlightRequestDTO;
import org.example.lennuplaneerija.dto.SeatDTO;
import org.example.lennuplaneerija.dto.SeatRequestDTO;
import org.example.lennuplaneerija.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/seats")
    public List<List<SeatDTO>> getSeats() {
        try {
            return seatService.fetchSeats();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/seats/filter")
    public List<List<SeatDTO>> getSuggestedSeats(@RequestBody SeatRequestDTO request) {
        try {
            return seatService.fetchSeatSelection(request.getSeats(), request.getPreference(), request.getPassengers());
        } catch (Exception e) {
            return null;
        }
    }

}
