<template>
  <vs-row style="padding-top: 10px" vs-justify="center" vs-align="center">


    <!--
         /////////////////
         Alert
         /////////////////
       -->
    <vs-col>
      <vs-alert v-if="errors.length > 0" class="mb-5 mt-5" color="danger" style="height: 55px">
        <b>Error</b>
        <ul class="common-list">
          <li v-for="error in errors" :key="error.id">{{ error }}</li>
        </ul>
      </vs-alert>
      <vs-alert v-else-if="isLoading" class="mb-5 mt-5" color="primary">
        <b>Cargando....</b>
      </vs-alert>
    </vs-col>

    <!--
       /////////////////
       ATM cards
       /////////////////
     -->

    <vs-row
        vs-justify="center" vs-align="center">
      <vs-col vs-lg="6" vs-sm="6" vs-xs="12"
              v-for="infoAtmItem in infoAtmList" v-bind:key="infoAtmItem.id">
        <vs-card style="border: 2px solid lightgray;  border-radius:25px;">
          <h2 class="card-title">{{ infoAtmItem.location }}</h2>
          <div class="container pb-4 pt-4">
            <img v-bind:src="require('../../../assets/images/product/cajero.png')"
                 class="cajeroStation">
          </div>
          <div class="pt-3">
            <h4>Equipo : {{ infoAtmItem.name }}</h4>
          </div>
          <div class="container">
            <h3 v-if="infoAtmItem.status === 'Enable'" style="border-radius:25px;background:mediumseagreen ">
              Conectado</h3>
            <h3 v-else>No está conectado</h3>
          </div>
        </vs-card>
      </vs-col>
    </vs-row>
    <router-link v-if="btnAddProperty" to="/view/setting/atm">
      <vs-button type="relief">Agregar Cajero</vs-button>
    </router-link>
  </vs-row>

</template>
<script>


import axios from "axios";
import propertyRouter from "@/propertyRouter";

export default {
  name: "Station",
  data: () => ({
    errors: [],
    isLoading: true,
    infoAtmList: [],
    btnAddProperty: false
  }),
  methods: {
    async getDataATM() {
      if (sessionStorage.length > 1) {
        this.isLoading = false;
        this.infoAtmList = JSON.parse(sessionStorage.getItem('localSaveList'));
        return;
      }
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing +
          propertyRouter.routerStationActive)
          .then((response) => {
            if (response.data.code === -1001) {
              this.errors.push(response.data.message);
              this.btnAddProperty = true
              return;
            }
            if (response.data.info !== null) {
              sessionStorage.setItem('localSaveList', JSON.stringify(response.data.info));
              this.isLoading = false;
              this.btnAddProperty = false;
              this.infoAtmList = JSON.parse(sessionStorage.getItem('localSaveList'));
              return;
            }
            this.errors.push(response.data.message);
          })
          .catch((errorApi) => {
            errorApi = 'La conexión con el servicio de cajeros no es posible';
            this.errors.push(errorApi);
          })
    }
  },
// validate() {
//   this.infoAtmList = JSON.parse(sessionStorage.getItem('localSaveList'));
//   if (this.infoAtmList === null) {
//     this.errors.push('No hay información de cajeros');
//     this.btnAddProperty = true
//     return;
//   }
//   this.isLoading = false;
//   this.btnAddProperty = false;
// },


  created() {
    this.getDataATM();
  }
  ,


}
</script>

<style scoped>
.cajeroStation {
  max-width: 100%;
  background-size: contain;
}

h3 {
  text-align: center;
}

card {

  padding: 10px;
  border-radius: 25px;
}

</style>
