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

    public List<List<SeatDTO>> fetchSeats() {

        // [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]] ESIMENE BLOKK
        // [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]] TEINE BLOKK
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

        return groupSeatsIntoBlocks(seats);
    }

    private List<List<SeatDTO>> groupSeatsIntoBlocks(List<SeatDTO> allSeats) {
        List<List<SeatDTO>> blocks = new ArrayList<>();

        int seatsPerBlockRow = 3;
        int rowsPerBlock = 4;

        String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H"};


        for (int rowStart = 0; rowStart < rows.length; rowStart += rowsPerBlock) {


            for (int seatStart = 0; seatStart < 6; seatStart += seatsPerBlockRow) {
                List<SeatDTO> block = new ArrayList<>();


                for (int rowOffset = 0; rowOffset < rowsPerBlock; rowOffset++) {
                    String row = rows[rowStart + rowOffset];


                    for (int seatOffset = 0; seatOffset < seatsPerBlockRow; seatOffset++) {
                        int seatNumber = seatStart + seatOffset + 1;
                        SeatDTO seat = findSeat(allSeats, row, seatNumber);
                        if (seat != null) {
                            block.add(seat);
                        }
                    }
                }

                blocks.add(block);
            }
        }

        return blocks;
    }

    private SeatDTO findSeat(List<SeatDTO> allSeats, String row, int seatNumber) {
        for (SeatDTO seat : allSeats) {
            if (seat.getRow().equals(row) && seat.getSeat() == seatNumber) {
                return seat;
            }
        }
        return null;
    }

    private boolean isSeatAvailable() {
        return random.nextDouble() > 0.1;
    }





}
