<template lang="">
    <h2 class="mb-5">Tutte le pizze:</h2>
    <ul>
        <li 
            class="list-unstyled"
            v-for="pizza in pizzas" 
            :key="pizza.id"
        >
            {{ pizza.nome }}
            <button 
            class="btn btn-danger"
            @click="deletePizza(pizza.id)"
            >elimina</button>
        </li>
    </ul>

</template>

<script setup>
    // import libraries
    import { defineProps } from 'vue';
    import axios from 'axios';

    // props
    const props = defineProps({
        pizzas: {
            type: Array,
            required: true
        }
    });

    //emits
    const emits = defineEmits("deleted");

    const deletePizza = async (pizzaId) => {

    await axios.delete(`http://localhost:8080/api/v1.0/pizzas/${pizzaId}`);
    
    emits("deleted");

};


</script>

<style lang="">
    
</style>