<template>
  <div class="container">

    <h1> ISTEKOHTADE VALIK </h1>
    <button @click="searchSeats"></button>

    <div class="grid-container">
      <div v-for="(block, index) in seats" :key="index" class="block">
        <div v-for="seat in block" :key="seat.row + seat.seat" class="seat" :class="{ available: seat.available }">
          {{ seat.row }}{{ seat.seat }}
        </div>
      </div>
    </div>

  </div>



</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      seats: [],
      error: "",
    };
  },
  methods: {
    async searchSeats() {
      this.error = "";

      try {

        const response = await axios.get("http://localhost:8081/api/seats");

        console.log(response.data);
        this.seats = response.data;

      } catch (err) {
        this.error = "Failed to fetch seats. Please try again later.";
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
  border: 1px white solid;
  width: 54vw;
}

.grid-container {
  align-self: flex-start;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(2, 1fr);
  gap: 2vw;
}

.block {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1vw;
  padding: 1vw;
}

.seat {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 5px;
  width: 4vw;
  height: 4vw;
  font-size: 1vw;
  color: #00ff92;
}

.seat.available {
  background: black;
  border: #00ff92 2px solid;
}

.seat:not(.available) {
  background-color: #000000;
}




</style>