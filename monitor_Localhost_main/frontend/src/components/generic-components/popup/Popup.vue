<template>
  <vs-popup title="Escoja una estación para continuar" :active.sync="popupActivo" button-close-hidden>
    <vs-row vs-justify="center" vs-align="center">
      <vs-col  vs-lg="6" vs-sm="6" vs-xs="12" v-for="atm in atmListActive" v-bind:key="atm">
        <vs-card autofocus actionable>
          <h3 style="text-align: center">{{atm}}</h3>
          <div class="container pb-4 pt-4"  style="max-width:200px">
            <img v-bind:src="require('../../../assets/images/product/cajero.png')"
                 alt="Cajero Accesspark"
                 class="cajero card-img-top">
          </div>

          <input class="btn-block btn btn-danger submit-button"
                 type="submit"
                 @click="$emit('click',atm)"
                 value="Seleccionar"/>
        </vs-card>
      </vs-col>
    </vs-row>
  </vs-popup>
</template>

<script>
import axios from "axios";
import propertyRouter from "@/propertyRouter";

export default {
  name: "popup",
  data: () => ({
    atmListActive: [],

  }),
  props: {
    popupActivo: {
      type: Boolean
    }
  }
  ,
  methods: {
    async getDataAtmActive() {
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing +
          propertyRouter.routerPopup)
          .then((response) => {
            if (response.data.info !== null) {
              this.atmListActive = response.data.info;
            }
          }).catch((errorApi) => {
            errorApi = "Error al consultar los cajeros activos"
            this.$vs.notify({title: 'ERROR', text: errorApi, color: 'danger'})
          });
    },
  },
  created() {
    this.getDataAtmActive();
  }

}
</script>
<style scoped>

.card {
  text-align: center;
  height: 200px;
}


</style>

