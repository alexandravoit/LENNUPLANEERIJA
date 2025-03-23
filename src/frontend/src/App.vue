<template>
  <div class="app">
    <div class="header">
      <h1 class="green">SWAG</h1>
      <h1 class="white">TRAVEL</h1>
    </div>
    <h2 class="subheader">KOIGE SWAGIM LENNUPLANEERIJA</h2>

    <img src="@/assets/plane.svg" class="plane" alt="plane icon" />

    <FlightSearch />

    <SeatSearch />

  </div>


</template>

<script>
import FlightSearch from './components/FlightSearch.vue';
import SeatSearch from './components/SeatSearch.vue';


export default {
  name: 'App',

  components: {
    FlightSearch,
    SeatSearch,
  },

  data() {

  },

  created() {
  },
  methods: {

  }

}


</script>

<style>

@font-face {
  font-family: 'Akira';
  src: url('@/assets/fonts/Akira.otf') format('opentype');
}

* {
  font-family: 'Akira', sans-serif;
  color: white;
  margin: 0;
  box-sizing: border-box;
}

.app {
  background: black;
  border: solid 1px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.header {
  margin-top: 1vh;
  display: flex;
  flex-direction: row;
  font-size: 4vw;
}

.header .green {
  color: #00FF92;
}

.header .white {
  color: white;
}

.subheader {
  font-size: 3vw;
  color: black;
  -webkit-text-stroke: 1px #00FF92;
  margin-bottom: 1vh;
}

.plane {
  width: 60vw;
  animation: float 3s ease-in-out infinite;
}



@keyframes float {
  0% { transform: translateY(0) }
  50% { transform: translateY(-1vh) }
  100% { transform: translateY(0) }
}





</style>
