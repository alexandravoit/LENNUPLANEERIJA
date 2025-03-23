package org.example.lennuplaneerija.service;

import org.example.lennuplaneerija.dto.SeatDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class SeatService {

    private final Random random = new Random();

    public List<SeatDTO> fetchSeats() {

        // [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]]
        // [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]]
        // [jne] [jne]

        List<SeatDTO> seats = new ArrayList<>();
        int seatsPerRow = 6;
        String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H"};

        for (String row : rows) {

            for (int i = 0; i < seatsPerRow; i++) {
                SeatDTO seat = new SeatDTO();
                seat.setRow(row);
                seat.setSeat(i+1);

                String[] businessRows = {"A", "B", "C", "D"};

                if (Arrays.asList(businessRows).contains(row)) {
                    seat.setType("Business");
                } else {seat.setType("Economy");}

                seat.setAvailable(isSeatAvailable());

                seats.add(seat);

            }
        }

        return seats;
    }

    private boolean isSeatAvailable() {
        return random.nextDouble() > 0.1;
    }

}
