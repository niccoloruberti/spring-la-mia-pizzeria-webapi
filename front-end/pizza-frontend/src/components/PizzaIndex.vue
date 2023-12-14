<template lang="">
    <h2 class="mb-5">Tutte le pizze:</h2>
    <ul>
        <li 
            class="list-unstyled"
            v-for="pizza in pizzas" 
            :key="pizza.id"
        >
            {{ pizza.nome }}
        </li>
    </ul>

</template>

<script setup>
    // import libraries
    import { defineProps } from 'vue';

    // props
    const props = defineProps({
        pizzas: {
            type: Array,
            required: true
        }
    });


</script>

<style lang="">
    
</style>