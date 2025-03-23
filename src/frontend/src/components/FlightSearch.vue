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
        <p>KUUPAEV</p>
        <p>HIND</p>
        <p>REISIJAID</p>
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
          <tr v-for="flight in flights" :key="flight.departure + flight.arrival">
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
      error: "",
      destinations: [
        { code: "LON" },
        { code: "NYC" },
        { code: "TAL" },
        { code: "HEL" },
        { code: "AMS" },
      ],
    };
  },
  methods: {
    async searchFlights() {
      this.error = "";

      try {

        const response = await axios.get("http://localhost:8081/api/flights", {
          params: {
            originSkyId: this.from,
            destinationSkyId: this.to,
          },
        });

        console.log(response.data);
        this.flights = response.data;

      } catch (err) {
        this.error = "Failed to fetch flights. Please try again later.";
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
    margin-bottom: 2vw;
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


</style>
