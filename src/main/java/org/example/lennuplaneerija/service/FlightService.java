package org.example.lennuplaneerija.service;

import org.example.lennuplaneerija.dto.FlightDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    SELLE KLASSI ja ÜLDISE BACKEND STRUKTUURI PÜSTITAMISEL SAIN ABI
        - YOUTUBE'I KANALITEST: Java Technology Learning &  atquil
        - CHATGPT: Abi HttpEntity ja JsonNode'ide üles seadmisega
 */

@Service
public class FlightService {

    private final List<String> airlines =
            List.of("Aeroflot", "Turkish Air", "AirBaltic", "Lufthansa", "Eesti Lennukompanii", "Wizzair");
    private final Random random = new Random();

    // SAIN API-ga TÖÖLE, AGA KAHJUKS API VALIK OLI VEIDI KESINE NING LÕPPTULEMUSE KVALITEEDI HUVIDES
    // OTSUSTASIN LENNUD ISE GENEREERIDA

   /* private final RestTemplate restTemplate;
    private final String APIurl = "https://blue-scanner.p.rapidapi.com/skyscanner-app/flights/search-roundtrip";
    private final String APIkey = "0156f1295emsh6f5739d7cab54c1p1c39b8jsn62e333b19d7a";
    private final ObjectMapper objectMapper;

    public FlightService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<FlightDTO> fetchFlights(String origin, String destination) throws IOException {
        String url = APIurl + "?originSkyId=" + origin + "&destinationSkyId=" + destination;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", APIkey);
        headers.set("x-rapidapi-host", "blue-scanner.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JsonNode rootNode = objectMapper.readTree(response.getBody());

        JsonNode itinerariesNode = rootNode.path("data").path("itineraries");

        List<FlightDTO> flights = new ArrayList<>();

        for (JsonNode itineraryNode : itinerariesNode) {
            FlightDTO flightDTO = new FlightDTO();

            JsonNode legNode = itineraryNode.path("legs").get(0);

            flightDTO.setOrigin(legNode.path("origin").path("name").asText());
            flightDTO.setDestination(legNode.path("destination").path("name").asText());
            flightDTO.setAirline(legNode.path("carriers").path("marketing").get(0).path("name").asText());
            flightDTO.setDeparture(legNode.path("departure").asText());
            flightDTO.setArrival(legNode.path("arrival").asText());
            flightDTO.setPrice(itineraryNode.path("price").path("raw").asDouble());
            flightDTO.setDuration(legNode.path("durationInMinutes").asDouble());

            flights.add(flightDTO);
        }

        return flights;

    }*/

    public List<FlightDTO> fetchFlights(String origin, String destination) {

        List<FlightDTO> flights = new ArrayList<>();
        int nrOfFlights = 15;

        for (int i = 0; i < nrOfFlights; i++) {
            FlightDTO flight = new FlightDTO();

            flight.setOrigin(origin);
            flight.setDestination(destination);
            flight.setAirline(airlines.get(random.nextInt(airlines.size())));

            LocalDateTime departureDate = generateRandomDateTime();
            flight.setDeparture(departureDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            LocalDateTime arrivalDate = departureDate.plusHours(1 + random.nextInt(10));
            flight.setArrival(arrivalDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            flight.setPrice(generateRandomPrice());

            double duration = java.time.Duration.between(departureDate, arrivalDate).toMinutes();
            flight.setDuration(duration);

            flights.add(flight);
        }

        return flights;

    }


    private double generateRandomPrice() {
        return 100 + (2000 - 100) * random.nextDouble();
    }

    private LocalDateTime generateRandomDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.plusDays(random.nextInt(31))
                .plusHours(random.nextInt(24))
                .plusMinutes(random.nextInt(60));
    }
}
