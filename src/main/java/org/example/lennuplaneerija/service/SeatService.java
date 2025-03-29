package org.example.lennuplaneerija.service;

import org.example.lennuplaneerija.dto.SeatDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        return random.nextDouble() > 0.3;
    }

    public List<List<SeatDTO>> fetchSeatSelection(List<List<SeatDTO>> seatBlocks, String pref, int passengers) {
        // pref saab olla: "ARIKLASS", "AKEN", "KORIDOR"
        // ARIKLASS -> seat.type.equals("Business")
        // AKEN -> seat.seat == 1 || seat.seat == 6
        // KORIDOR -> seat.seat == 3 || seat.seat == 4
        // Lennnufirma huvides on vaikimisi eelistus istutada kõik võimalikult ette, täites ühe bloki korraga
        // Eelistus tähendab, et vähemalt 1 kohtadest on selle eelistuga arvestanud (juhul, kui on piisavalt kohti)

        if (pref.equals("ARIKLASS")) {
            List<List<SeatDTO>> modifiedBusinessSeats = getBusinessSeats(seatBlocks.subList(0,2), passengers);
            List<List<SeatDTO>> unchangedEconomy = seatBlocks.subList(2, seatBlocks.size());
            List<List<SeatDTO>> finalSeatArrangement = new ArrayList<>();
            finalSeatArrangement.addAll(modifiedBusinessSeats);
            finalSeatArrangement.addAll(unchangedEconomy);

            return finalSeatArrangement;
        }
        else {
            List<List<SeatDTO>> modifiedEconomySeats = getEconomySeats(seatBlocks.subList(2, seatBlocks.size()), passengers, pref);
            List<List<SeatDTO>> unchangedBusiness = seatBlocks.subList(0,2);
            List<List<SeatDTO>> finalSeatArrangement = new ArrayList<>();
            finalSeatArrangement.addAll(unchangedBusiness);
            finalSeatArrangement.addAll(modifiedEconomySeats);

            return finalSeatArrangement;
        }
    }


    private List<List<SeatDTO>> getBusinessSeats(List<List<SeatDTO>> blocks, int passengers) {
        int assignedCount = 0;

        for (List<SeatDTO> block : blocks) {
            List<SeatDTO> availableSeats = block.stream()
                    .filter(SeatDTO::isAvailable)
                    .sorted(Comparator.comparing(SeatDTO::getRow))
                    .toList();

            for (SeatDTO seat : availableSeats) {
                if (assignedCount < passengers) {
                    seat.setPreferred(true);
                    assignedCount++;
                } else {
                    break;
                }
            }

            if (assignedCount >= passengers) break;
        }

        return blocks;
    }


    private List<List<SeatDTO>> getEconomySeats(List<List<SeatDTO>> blocks, int passengers, String pref) {
        int assignedCount = 0;
        SeatDTO firstPreferredSeat = null;
        List<SeatDTO> allAvailableSeats = new ArrayList<>();

        for (List<SeatDTO> block : blocks) {
            for (SeatDTO seat : block) {
                if (seat.isAvailable()) {
                    if (firstPreferredSeat == null) {
                        if (pref.equals("AKEN") && (seat.getSeat() == 1 || seat.getSeat() == 6)) {
                            firstPreferredSeat = seat;
                        } else if (pref.equals("KORIDOR") && (seat.getSeat() == 3 || seat.getSeat() == 4)) {
                            firstPreferredSeat = seat;
                        }
                    }
                    allAvailableSeats.add(seat);
                }
            }
        }

        if (firstPreferredSeat != null) {
            firstPreferredSeat.setPreferred(true);
            assignedCount++;
            allAvailableSeats.remove(firstPreferredSeat);
        }

        for (SeatDTO seat : allAvailableSeats) {
            if (assignedCount < passengers) {
                seat.setPreferred(true);
                assignedCount++;
            } else {
                break;
            }
        }

        return blocks;
    }




}
