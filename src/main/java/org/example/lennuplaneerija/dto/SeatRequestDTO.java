package org.example.lennuplaneerija.dto;

import java.util.List;

public class SeatRequestDTO {
    private List<List<SeatDTO>> seats;
    private int passengers;
    private String preference;

    public SeatRequestDTO() {
    }

    public List<List<SeatDTO>> getSeats() {
        return seats;
    }

    public int getPassengers() {
        return passengers;
    }

    public String getPreference() {
        return preference;
    }

    public void setSeats(List<List<SeatDTO>> seats) {
        this.seats = seats;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }
}
