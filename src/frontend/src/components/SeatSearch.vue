<template>
  <div class="container">

    <div class="grid-container">
      <div v-for="(block, index) in displayedSeats" :key="index" class="block">
        <div
            v-for="seat in block"
            :key="seat.row + seat.seat"
            class="seat"
            :class="{
            available: seat.available,
            preferred: seat.preferred,
            selected: isSeatSelected(seat)
          }"
            @click="toggleSeatSelection(seat)"
        >
          {{ seat.row }}{{ seat.seat }}
        </div>
      </div>
    </div>

    <div class="selection">

      <div class="seat-header">
        <h1> ISTEKOHTADE </h1>
        <h1> VALIK </h1>
      </div>

      <h1 class="pref-header">EELISTUS:</h1>

      <select v-model="pref" @change="fetchSeatSelection" class="filter-select">
        <option v-for="pref in prefChoices" :key="pref" :value="pref">
          {{ pref }}
        </option>
      </select>



    </div>

  </div>



</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      seats: [],
      preferredSeats: [],
      selectedSeats: [],
      error: "",
      pref: "",
      prefChoices: ["ARIKLASS", "AKEN", "KORIDOR"],

    };
  },
  computed: {
    displayedSeats() {
      return this.preferredSeats.length > 0 ? this.preferredSeats : this.seats;
    },
  },
    props: {
    flight: Object,
    passengerCount: Number,
  },
  methods: {
    async searchSeats() {
      this.error = "";

      try {

        const response = await axios.get("http://localhost:9090/api/seats");
        this.seats = response.data;

      } catch (err) {
        this.error = "Failed to fetch seats. Please try again later.";
        console.error("API Error:", err);
      }
    },

    async fetchSeatSelection() {
      try {
        const response = await axios.post("http://localhost:9090/api/seats/filter", {
          seats: this.seats,
          passengers: this.passengerCount,
          preference: this.pref,
        });

        console.log(response.data);

        this.preferredSeats = response.data;
        this.selectedSeats = [];

      } catch (err) {
        this.error = "Failed to fetch filtered seats. Please try again later.";
        console.error("API Error:", err);
      }
    },


    // MEETODI AITAS LUUA CHATGPT
    toggleSeatSelection(seat) {
      if (!seat.available) return;

      const index = this.selectedSeats.findIndex(s =>
          s.row === seat.row && s.seat === seat.seat
      );

      if (index === -1) {
        if (this.selectedSeats.length < this.passengerCount) {
          this.selectedSeats.push(seat);
        }
      } else {
        this.selectedSeats.splice(index, 1);
      }
    },

    // MEETODI AITAS LUUA CHATGPT
    isSeatSelected(seat) {
      return this.selectedSeats.some(s =>
          s.row === seat.row && s.seat === seat.seat
      );
    }

  },
  mounted() {
    this.searchSeats();
  },
};

</script>

<style scoped>

.container {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  margin: 2vw;
  padding: 1vw;
  width: 74vw;
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
  cursor: pointer;
}

.seat:not(.available) {
  background-color: #000000;
}

.selection {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.seat-header {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  margin-bottom: 1vw;
}

.seat-header h1 {
  color: #00ff92;
  font-size: 3vw;
}

.filter-select {
  background-color: #000000;
  border-radius: 5px;
  padding: 0.5vw;
  color: #00ff92;
  font-size: 2.7vw;
  margin-top: 1vw;
}

.pref-header {
  font-size: 3vw;
  -webkit-text-stroke: 1px #00ff92;
  color: black;
}

.seat.preferred {
  border: #ffbd36 1px dashed !important;
  color: #ffbd36 !important;
}

.seat.selected {
  background-color: #00ff92 !important;
  color: black !important;
}


</style>