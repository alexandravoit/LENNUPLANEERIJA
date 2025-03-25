package org.example.lennuplaneerija.dto;

import org.example.lennuplaneerija.dto.FlightDTO;
import java.util.List;

public class FlightResponseDTO {
    private List<FlightDTO> flights;
    private List<String> availableDates;

    public FlightResponseDTO(List<FlightDTO> flights, List<String> availableDates) {
        this.flights = flights;
        this.availableDates = availableDates;
    }

    public List<FlightDTO> getFlights() {
        return flights;
    }

    public List<String> getAvailableDates() {
        return availableDates;
    }
}
