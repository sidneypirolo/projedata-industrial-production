<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

const API_URL = "http://localhost:8080";

// ================= RAW MATERIAL =================

const rawMaterials = ref([]);
const rawName = ref("");
const rawQuantity = ref(0);

const loadRawMaterials = async () => {
   const response = await axios.get(`${API_URL}/raw-materials`);
   rawMaterials.value = response.data;
};

const saveRawMaterial = async () => {
   await axios.post(`${API_URL}/raw-materials`, {
      name: rawName.value,
      quantityInStock: rawQuantity.value
   });

   rawName.value = "";
   rawQuantity.value = 0;
   loadRawMaterials();
};

// ================= PRODUCT =================

const products = ref([]);
const productName = ref("");
const productValue = ref(0);

const loadProducts = async () => {
   const response = await axios.get(`${API_URL}/products`);
   products.value = response.data;
};

const saveProduct = async () => {
   await axios.post(`${API_URL}/products`, {
      name: productName.value,
      value: productValue.value,
      composition: []
   });

   productName.value = "";
   productValue.value = 0;
   loadProducts();
};

// ================= OPTIMIZATION =================

const optimizationResult = ref({
   items: [],
   totalRevenue: 0
});

const calculateOptimization = async () => {
   const response = await axios.get(`${API_URL}/production/optimization`);
   optimizationResult.value = response.data;
};

// ================= INIT =================

onMounted(() => {
   loadRawMaterials();
   loadProducts();
});
</script>

<template>
   <div class="container">
      <h1>Production Optimization System</h1>

      <hr />

      <h2>Raw Materials</h2>

      <input v-model="rawName" placeholder="Name" />
      <input v-model="rawQuantity" type="number" placeholder="Quantity" />
      <button @click="saveRawMaterial">Save</button>

      <ul>
         <li v-for="rm in rawMaterials" :key="rm.id">
            {{ rm.name }} - {{ rm.quantityInStock }}
         </li>
      </ul>

      <hr />

      <h2>Products</h2>

      <input v-model="productName" placeholder="Name" />
      <input v-model="productValue" type="number" placeholder="Value" />
      <button @click="saveProduct">Save</button>

      <ul>
         <li v-for="p in products" :key="p.id">
            {{ p.name }} - $ {{ p.value }}
         </li>
      </ul>

      <hr />

      <h2>Production Optimization</h2>

      <button @click="calculateOptimization">
         Calculate Best Production
      </button>

      <ul>
         <li v-for="item in optimizationResult.items" :key="item.productId">
            {{ item.productName }} - Quantity: {{ item.quantityToProduce }}
         </li>
      </ul>

      <p>
         <strong>Total Revenue:</strong>
         $ {{ optimizationResult.totalRevenue }}
      </p>
   </div>
</template>

<style>
.container {
   padding: 40px;
   font-family: Arial, sans-serif;
}

input {
   margin: 5px;
   padding: 5px;
}

button {
   margin: 5px;
   padding: 5px 10px;
   cursor: pointer;
}

hr {
   margin: 30px 0;
}
</style>