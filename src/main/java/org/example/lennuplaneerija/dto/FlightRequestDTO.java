package org.example.lennuplaneerija.dto;

import java.util.List;

public class FlightRequestDTO {
    private List<FlightDTO> flights;
    private String date;
    private String priceRange;

    public FlightRequestDTO() {
    }
    
    public List<FlightDTO> getFlights() { return flights; }
    public void setFlights(List<FlightDTO> flights) { this.flights = flights; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getPriceRange() { return priceRange; }
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }
}
