<template>

  <!-- pizza index -->
  <PizzaIndex
  :pizzas="pizzas"
  v-if="!creatingPizza"
  @deleted="pizzaDeleted"
  />

  

  <!-- bottone creazione pizze -->
  <button 
    class="btn btn-success"
    v-if="!creatingPizza"
    @click="creatingPizza = true"
  >
    Aggiungi Pizza
  </button>

  <!-- pizza form -->
  <PizzaForm 
    v-if="creatingPizza" 
    @back="creatingPizza = false" 
    @created="pizzaCreated"
  />

</template>

<script setup>
  //import libraries
  import axios from 'axios';
  import { onMounted, ref } from 'vue';

  //import components
  import PizzaIndex from './components/PizzaIndex.vue';
  import PizzaForm from './components/PizzaForm.vue';

  //data
  const pizzas = ref(null);
  const creatingPizza = ref(false);

  //functions

  const pizzaCreated = () => {
  creatingPizza.value = false;
  getPizzas();
  };

  const pizzaDeleted = () => {
  creatingPizza.value = false;
  getPizzas();
  };

  const getPizzas = async () => {
  const data = await axios.get("http://localhost:8080/api/v1.0/pizzas");
  pizzas.value = data.data;
  };

  //hooks
  onMounted(getPizzas);
</script>

<style scoped>
  
</style>
