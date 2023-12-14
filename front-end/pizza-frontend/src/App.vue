<template>
  <PizzaIndex
  :pizzas="pizzas"/>
</template>

<script setup>
  //import libraries
  import axios from 'axios';
  import { onMounted, ref } from 'vue';

  //import components
  import PizzaIndex from './components/PizzaIndex.vue';

  //data
  const pizzas = ref(null);

  const getPizzas = async () => {
  const data = await axios.get("http://localhost:8080/api/v1.0/pizzas");
  pizzas.value = data.data;
  };

  //hooks
  onMounted(getPizzas);
</script>

<style scoped>
  
</style>
