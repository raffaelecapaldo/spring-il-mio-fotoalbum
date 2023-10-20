<template>
  <header>
    <nav class="d-flex justify-content-between align-items-center">
      <div class="left d-flex flex-column justify-content-center align-items-center">
        <img class="logo" src="images/fotografando-logo.svg" alt="Fotografando">
        <h4>Fotografando</h4>
      </div>
      <div class="right">
        <ul class="list-unstyled m-0">
          <li><a href="">Home</a></li>
        </ul>
      </div>
    </nav>
  </header>
  <main>
    <div class="py-4 mb-2 hero container-fluid d-flex justify-content-center">
      <h1 class="text-uppercase">Le foto più apprezzate</h1>
    </div>

    <div class="d-flex justify-content-center">
      <form class="w-75" @submit.prevent="getPhotos">
        <div class="input-group flex-nowrap mb-2">

          <span class="input-group-text" id="addon-wrapping">Filtro</span>
          <input type="text" name="title" class="form-control" placeholder="Foto da ricercare" for="title"
            aria-describedby="addon-wrapping" v-model="titleSearch">
          <button type="submit" class="mybutton">Cerca</button>
        </div>
      </form>
    </div>

    <div v-if="photos == null" class="p-2 notfound mt-3">
        <p>Nessuna foto trovata</p>
    </div>

    <div class="container-fluid d-flex gap-3 flex-wrap justify-content-center">
      <div v-if="photos != null" v-for="photo in photos" class="container-photo">
        <div class="photo">
          <div class="photo-image ">
            <div class="d-flex justify-content-center">
              <img class="photo img-fluid" :src="photo.url" :alt="photo.name">
            </div>
            <div class="info-image pt-1 d-flex flex-column align-items-center">
              <h3>{{ photo.title }}</h3>
              <p>{{ photo.description }}</p>
            </div>
            <div class="extra-info">
              <p v-if="photo.categories" class="m-0">Categorie:
                <span v-for="(category, i) in photo.categories">{{ category.name }}<span
                    v-if="i < photo.categories.length - 1">, </span></span>
              </p>
              <p>Autore: {{ photo.username }}</p>
            </div>
          </div>
        </div>
      </div>

    </div>
    <div class="py-2 hero container-fluid d-flex justify-content-center align-items-center mt-4">
      <h4 class="text-uppercase m-0">Contattaci</h4>
    </div>
    <div class="container-fluid d-flex justify-content-center ">
      <form @submit.prevent="sendMessage" class="myform d-flex flex-column justify-content-center align-items-center "
        action="">
        <div class="mb-3">
          <input required v-model="formEmail" type="email" class="form-control" placeholder="La tua email">
        </div>
        <div class="mb-3">
          <textarea maxlength="650" minlength="3" v-model="formText" type="email" class="form-control" rows="3"
            placeholder="Il tuo messaggio..."></textarea>
          <p class="m-0 text-white">Caratteri rimanenti: {{ MAX_CHARFORM - formText.length }}</p>
        </div>
        <div class="mb-1">
          <input type="submit" class="mybutton text-decoration-none fs-5">
        </div>


      </form>
    </div>
  </main>
  <footer>
    <div class="p-3 d-flex justify-content-center align-items-center">
      <p class="m-0 fs-4">Made by Raf-san</p>
    </div>
  </footer>
</template>

<script>
import axios from 'axios';
import { useToast } from "vue-toastification";

export default {
  data() {
    return {
      API_URL: "http://localhost:8080/api/v1/",
      photos: null,
      formEmail: null,
      formText: "",
      toast: useToast(),
      titleSearch: null,
      MAX_CHARFORM: 650

    }
  },
  methods: {
    getPhotos() {
      axios.get(this.API_URL + 'photos', {
        params: {
          q: this.titleSearch
        }
      })
        .then((res) => {
          this.photos = res.data
        })
        .catch((e) => {
          this.photos = null
        })
    },
    sendMessage() {
      const message = {
        email: this.formEmail,
        text: this.formText
      }
      axios.post(this.API_URL + 'message', message)
        .then((res) => {
          this.formEmail =
            this.formText = null
          this.toast.success("Messaggio inviato!", {
            timeout: 2000
          })

        })
        .catch((e) => {
          console.log(e)
        })
    }
  },
  mounted() {
    this.getPhotos()
    console.log(this.photos)
  }

}
</script>

<style lang="scss" scoped>
nav {
  padding: 15px;
  background-color: #1b292b;
  color: white;

  li a {
    color: white;
    text-decoration-line: none;
    font-size: 30px;

    &:hover {
      text-decoration-line: underline;
    }
  }
}

.logo {
  width: 100px;
  border-radius: 10px;
}

.hero {
  background-color: #46576b;
  color: #F2F2F2
}

.photo-image {
  padding: 10px;
  color: #F2F2F2;
  background-color: #1b292b;
  min-height: 400px;
  max-height: 400px;

  .photo {
    border: 1px solid white;
    height: 200px;
    object-fit: cover;

  }
}

.container-photo {
  min-width: 350px;
  max-width: 350px;


}

.myform {

  background-color: #46576b;
  width: 400px;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;

}

textarea {
  width: 300px
}

.mybutton {
  all: unset;
  padding-left: 20px;
  padding-right: 20px;
  padding-bottom: 5px;
  padding-top: 5px;
  border-radius: 3px;
  color: white;
  cursor: pointer;
  background-color: #1b292b !important;
  ;

  &:hover {
    background-color: black !important;
    ;
  }
}

.notfound {
  background-color: #1b292b;
  color:#F2F2F2;
  text-align: center;
  font-size: 3rem;
}

footer {
  margin-top: 10px;
  background-color: #1b292b;
  color: white;
}</style>