import "@fontsource/roboto"; 
import './assets/main.css'
import 'bootstrap/dist/css/bootstrap.css'
import "vue-toastification/dist/index.css"


import { createApp } from 'vue'
import Toast from "vue-toastification";
import App from './App.vue'

createApp(App).use(Toast).mount('#app')


