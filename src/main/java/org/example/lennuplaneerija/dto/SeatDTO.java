package org.example.lennuplaneerija.dto;

public class SeatDTO {
    private String row; //A, B
    private int seat;
    private String type; // Economy, business
    private boolean available;
    private boolean preferred = false;

    public SeatDTO() {
    }

    public void setRow(String row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

    public boolean isPreferred() {
        return preferred;
    }
}
