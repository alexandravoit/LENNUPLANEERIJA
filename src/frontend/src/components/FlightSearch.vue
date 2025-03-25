<template>
  <div class="container">

    <div class="destination">

      <div class="selection">
        <p class="details">KUST</p>
        <div class="dropdown">
          <select v-model="from">
            <option v-for="destination in destinations" :key="destination.code" :value="destination.code">
              {{ destination.code }}
            </option>
          </select>
        </div>
      </div>

      <div class="selection">
        <p class="details">KUHU</p>
        <div class="dropdown">
          <select v-model="to">
            <option v-for="destination in destinations" :key="destination.code" :value="destination.code">
              {{ destination.code }}
            </option>
          </select>
        </div>
      </div>

    </div>

    <button @click="searchFlights" class="otsi">OTSI</button>

    <div class="search">

      <div class="parameters">
        <div class="filter-group">
          <p>KUUPAEV</p>
          <select v-model="selectedDate" @change="fetchFlightsWithFilters" class="filter-select">
            <option value="">KUUPAEVAD</option>
            <option v-for="date in availableDates" :key="date" :value="date">
              {{ date }}
            </option>
          </select>
        </div>

        <div class="filter-group">
          <p>HIND</p>
          <select v-model="selectedPriceRange" @change="fetchFlightsWithFilters" class="filter-select">
            <option value="">HINNAD</option>
            <option value="100-250">100 - 250€</option>
            <option value="250-500">250 - 500€</option>
          </select>
        </div>

        <div class="filter-group">
          <p>REISIJAID</p>
          <select v-model="passengerCount" class="filter-select">
            <option v-for="nr in passengerOptions" :value="nr" :key="nr">
              {{ nr }}
            </option>
          </select>
        </div>


      </div>

      <div class="flight-results" v-if="flights.length > 0">
        <table>
          <thead>
          <tr>
            <th>LENNUFIRMA</th>
            <th>VALJUMINE</th>
            <th>SAABUMINE</th>
            <th>HIND (€)</th>
            <th>PIKKUS (MIN)</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="flight in displayedFlights" :key="flight.departure + flight.arrival">
            <td>{{ flight.airline }}</td>
            <td>{{ flight.departure }}</td>
            <td>{{ flight.arrival }}</td>
            <td>{{ flight.price }}</td>
            <td>{{ flight.duration }}</td>
          </tr>
          </tbody>
        </table>
      </div>

    </div>
  </div>

</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      from: "NYC",
      to: "LON",
      flights: [],
      filteredFlights: [],
      error: "",
      destinations: [
        { code: "LON" },
        { code: "NYC" },
        { code: "TAL" },
        { code: "HEL" },
        { code: "AMS" },
      ],
      selectedDate: "",
      selectedPriceRange: "",
      passengerCount: 1,
      passengerOptions: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      availableDates: [],
    };
  },
  computed: {
    displayedFlights() {
      return this.filteredFlights.length > 0 ? this.filteredFlights : this.flights;
    },
  },
  methods: {
    async searchFlights() {
      this.error = "";
      this.selectedDate = "";
      this.selectedPriceRange = "";
      this.filteredFlights = []

      try {

        const response = await axios.get("http://localhost:8081/api/flights", {
          params: {
            originSkyId: this.from,
            destinationSkyId: this.to,
          },
        });

        const data = response.data;
        const flights = data.flights;
        const availableDates = data.availableDates;
        this.flights = flights;
        this.availableDates = availableDates;

      } catch (err) {
        this.error = "Failed to fetch flights. Please try again later.";
        console.error("API Error:", err);
      }
    },

    async fetchFlightsWithFilters() {
      this.error = "";

      try {
        const response = await axios.post("http://localhost:8081/api/flights/filter", {
          flights: this.flights,
          date: this.selectedDate,
          priceRange: this.selectedPriceRange
        });


        console.log(response.data);
        this.filteredFlights = response.data;

      } catch (err) {
        this.error = "Failed to fetch filtered flights. Please try again later.";
        console.error("API Error:", err);
      }
    },
  },
};
</script>

<style scoped>
  .container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .destination {
    display: flex;
    flex-direction: row;
  }

  .destination .selection {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 2vw 5vw;
    margin: 2vw;
    background-color: #00FF92;
    border-radius: 15px;
  }

  .details {
    color: #00FF92;
    -webkit-text-stroke: 1px black;
    font-size: 2vw;
  }


  .otsi {
    border: none;
    background: none;
    color: #00FF92;
    font-size: 2vw;
    margin-right: 3vw;
    align-self: flex-end;
  }

  .otsi:hover {
    color: white;
  }

  .search {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 2vw;
    padding: 2vw;
    border: #00FF92 1px solid;
    border-radius: 15px;
    width: 54vw;
  }

  .parameters {
    font-size: 2vw;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    padding: 2vw;
    background-color: #00FF92;
    border-radius: 15px;
    width: 47vw;
  }

  .parameters p {
    color: black;
  }

  /* Kasutasin ChatGPT'd dropdown menüüde stylinguks */

  .dropdown {
    position: relative;
  }

  .dropdown select {
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    background-color: transparent;
    border: none;
    color: black;
    font-weight: bold;
    cursor: pointer;
    padding-right: 2vw;
    font-size: 4vw;
  }

  .dropdown::after {
    content: "X";
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    pointer-events: none;
    color: black;
  }

  .flight-results {
    display: flex;
    flex-direction: column;
    font-size: 1vw;
    margin-top: 2vw;
  }

  .flight-results table {
    border-collapse: collapse;
    margin-top: 1vw;
  }

  .flight-results th,
  .flight-results td {
    padding: 1vw;
    text-align: center;
    color: #00ff92;
    font-family: Arial, Helvetica, sans-serif;
  }

  .flight-results th {
    background-color: #00ff92;
    color: black;
  }

  .flight-results tr:hover {
    background-color: #00ff92;
  }

  .flight-results tr:hover td {
    color: black;
  }

  .filter-group {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 0 1vw;
  }

  .filter-select {
    background-color: #000000;
    border-radius: 5px;
    padding: 0.5vw;
    color: #00ff92;
    font-size: 1vw;
  }


</style>
