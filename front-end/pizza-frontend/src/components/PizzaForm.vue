<template>
    <form @submit.prevent="submit">
        <label for="name">Nome</label>
        <input type="text" name="name" id="nome" v-model="newPizza.nome">
        <br>
        <label for="description">Descrizione</label>
        <input type="text" name="description" id="descrizione" v-model="newPizza.descrizione">
        <br>
        <label for="price">Prezzo</label>
        <input type="number" name="price" id="prezzo" v-model="newPizza.prezzo">
        <br>
        <label for="photo">Foto</label>
        <input type="text" name="photo" id="foto" v-model="newPizza.foto">
        <br>
        <button type="submit">
            Crea
        </button>
        <button 
            type="button" 
            @click="$emit('back')">
            Back
        </button>
    </form>
</template>

<script setup>
// IMPORT LIBS
import { defineEmits, ref } from 'vue';
import axios from 'axios';

// props

// data
const newPizza = ref({
    nome: null,
    prezzo: null,
    descrizione: null,
    foto: null
});

// EMITS
const emits = defineEmits(["back", "created"]);

// FUNCTIONS
const submit = async () => {
     
    const data = await axios.post(
        "http://localhost:8080/api/v1.0/pizzas", 
        newPizza.value
    );

    console.log("data", data);

    // console.log("data", data);

    emits("created");
}


</script>