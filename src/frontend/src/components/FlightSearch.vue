<template>
  <div class="container">

    <div class="destination">
      <div class="selection">
        <p class="details">KUST</p>
        <p class="main">TLL</p>
      </div>

      <div class="selection">
        <p class="details">KUHU</p>
        <p class="main">LND</p>
      </div>
    </div>

    <p class="otsi">OTSI</p>

    <div class="search">

      <div class="parameters">
        <p>KUUPAEV</p>
        <p>HIND</p>
        <p>REISIJAID</p>
      </div>

    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      from: "TLL",
      to: "LND",
      flights: [],
    };
  },
  methods: {
    searchFlights() {
      // Placeholder data – Replace with API call
      this.flights = [
        { id: 1, date: "2025-03-25", price: 120, passengers: 1 },
        { id: 2, date: "2025-03-26", price: 150, passengers: 2 },
      ];
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

  .main {
    color: black;
    font-size: 5vw;
  }

  .otsi {
    color: #00FF92;
    font-size: 2vw;
    margin-right: 3vw;
    align-self: flex-end;
  }

  .search {
    margin: 2vw;
    padding: 2vw;
    border: #00FF92 1px solid;
    border-radius: 15px;
    height: 50vh;
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

</style>
