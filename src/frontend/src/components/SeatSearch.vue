<template>
  <div class="container">

    <h1> ISTEKOHTADE VALIK </h1>


  </div>


</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      seats: [],
    };
  },
  methods: {
    async searchSeats() {
      this.error = "";

      try {

        const response = await axios.get("http://localhost:8081/api/seats");

        console.log(response.data);
        this.flights = response.data;

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

</style>