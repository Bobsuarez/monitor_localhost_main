<template>
  <vs-popup title="Información de los niveles" :active.sync="popupActivo" button-close-hidden>
    <vs-row>
      <vs-card class="pb-4">
        <h3>Descripción</h3>
        <vs-row class="pt-4">
          <vs-col v-for="moneyList of items" v-bind:key="moneyList.id">
            <vs-row class="pt-4">
              <vs-col vs-lg="3">Denominación:</vs-col>
              <vs-col vs-lg="3">{{ moneyList.value}}</vs-col>
              <vs-col vs-lg="3">Cantidad:</vs-col>
              <vs-col vs-lg="3">{{ moneyList.quantity }}</vs-col>
              <vs-col vs-lg="3">Capacidad :</vs-col>
              <vs-col vs-lg="3">{{ moneyList.maximum }}</vs-col>
            </vs-row>
          </vs-col>
          <input class="mt-5 btn btn-primary submit-button"
                 type="submit"
                 @click="$emit('click',false)"
                 value="Aceptar"/>
        </vs-row>
      </vs-card>
    </vs-row>
  </vs-popup>
</template>

<script>
export default {
  name: "PopLevel",
  data: () => ({
    itemArray: [],
    moneyList: []
  }),
  props: {
    popupActivo: {
      type: Boolean
    },
    items: {
      type: Array
    }

  },
  methods:{
    formatPrice(value) {
      let val = (value / 1).toFixed(2).replace('.', ',')
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")
    }
  }
}

</script>

<style scoped>

</style>