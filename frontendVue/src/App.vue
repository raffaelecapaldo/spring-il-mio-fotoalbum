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
    <div class="container-fluid d-flex gap-3 flex-wrap justify-content-center">
      <div v-for="photo in photos" class="container-photo">
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
              <!-- TODO -->
              <!-- <p>Autore: </p> -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      API_URL: "http://localhost:8080/api/v1/",
      photos: null
    }
  },
  methods: {
    getPhotos() {
      axios.get(this.API_URL + 'photos')
        .then((res) => {
          this.photos = res.data
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
</style>