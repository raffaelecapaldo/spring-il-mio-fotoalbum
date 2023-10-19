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
        <div v-for="x in 5" class="container-photo">
          <div class="photo">
            <div class="photo-image ">
              <div class="d-flex justify-content-center">
              <img class="img-fluid photo" src="https://i1.adis.ws/i/canon/pro-issues-affecting-student-photographers-1_4961dcc710a54b35b57d7c1dc2c9a14f?$media-collection-full-dt-jpg" alt="">
            </div>
              <div class="info-image pt-1 d-flex flex-column align-items-center">
                <h3>Title</h3>
                <p>Descrizione</p>
              </div>
              <div class="extra-info">
                <p class="m-0">Categorie: </p>
                <p>Autore: </p>
              </div>
            </div>
          </div>
        </div>
      </div>
  </main>
</template>

<script>
  export default {
    
  }
</script>

<style lang="scss" scoped>

nav {
  padding:15px;
  background-color: #1b292b;
  color:white;
  li a {
    color:white;
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
  background-color:#46576b;
  color:#F2F2F2
}

.photo-image {
  padding:10px;
  color:#F2F2F2;
  background-color: #1b292b;
  .photo {
    border:1px solid white
  }
}

.container-photo {
  min-width: 320px;
  max-width: 320px;
}




</style>