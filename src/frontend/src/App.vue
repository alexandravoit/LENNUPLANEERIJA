<template>
  <p>{{message}}</p>
</template>

<script>
import axios from 'axios';

export default {
  name: 'App',

  data() {
    return {
      message: ''
    };
  },

  created() {
    this.lennukid();
  },
  methods: {
    async lennukid() {
      try {
        const response = await axios.get('/api/lennukid');
        this.message = response.data;
        console.log(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }
  }

}


</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
