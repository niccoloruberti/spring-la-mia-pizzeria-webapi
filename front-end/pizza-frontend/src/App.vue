<template>

  <div>
    <input
      type="text"
      v-model="searchStr"
      placeholder="Cerca una pizza..."
    />
    <button @click="search">Cerca</button>
  </div>

  <!-- pizza index -->
  <PizzaIndex
  :pizzas="(isFiltered ? filteredPizzas : pizzas)"
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
  let filteredPizzas = ref(null);
  const creatingPizza = ref(false);
  let searchStr = ref(null);
  let isFiltered = ref(false);

  //functions

  const pizzaCreated = () => {
  creatingPizza.value = false;
  getPizzas();
  };

  const pizzaDeleted = () => {
  creatingPizza.value = false;
  isFiltered.value = false;
  getPizzas();
  };

  const getPizzas = async () => {
  const data = await axios.get("http://localhost:8080/api/v1.0/pizzas");
  pizzas.value = data.data;
  console.log(pizzas)
  };


  const search = () => {
  if (searchStr.value !== null) {
    // Filtra le pizze solo se c'è una stringa di ricerca
    filteredPizzas.value = pizzas.value.filter((pizza) =>
      pizza.nome.toLowerCase().includes(searchStr.value.toLowerCase())
    );
    isFiltered.value = true;
  } else {
    // Altrimenti, ripristina l'elenco completo di pizze
    getPizzas();
  }
  }

  //hooks
  onMounted(getPizzas);
</script>

<style scoped>
  
</style>
