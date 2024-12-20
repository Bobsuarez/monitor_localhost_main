<template>
  <vs-popup title="Información de la transacción" :active.sync="popupActivo">
    <vs-row>
      <vs-card>
        <h3>General</h3>
        <vs-row class="pt-4" vs-justify="center" vs-align="center">
          <vs-col vs-lg="2">Fecha:</vs-col>
          <vs-col vs-lg="4">{{ items.instant }}</vs-col>
          <vs-col vs-lg="2">Vehiculo-Id:</vs-col>
          <vs-col vs-lg="4">{{ items.id }}</vs-col>
          <vs-col vs-lg="2">Estado:</vs-col>
          <vs-col vs-lg="4">{{ items.status }}</vs-col>
          <vs-col vs-lg="2">Prefijo:</vs-col>
          <vs-col vs-lg="4">{{ items.prefix }}</vs-col>
          <vs-col vs-lg="2">N° Factura:</vs-col>
          <vs-col vs-lg="4">{{ items.bill_number }}</vs-col>
          <vs-col vs-lg="2">Total:</vs-col>
          <vs-col vs-lg="4">{{ items.total }}</vs-col>
        </vs-row>
      </vs-card>
      <vs-card class="pb-4">
        <h3>Descripción</h3>
        <vs-row class="pt-4">
          <vs-col v-for="moneyList of items.money" v-bind:key="moneyList.id">
            <vs-row class="pt-4">
              <vs-col vs-lg="2">Acción:</vs-col>
              <vs-col vs-lg="4">{{ moneyList.type_movement }}</vs-col>
              <vs-col vs-lg="3">Cantidad:</vs-col>
              <vs-col vs-lg="3">{{ formatPrice(moneyList.quantity) }}</vs-col>
              <vs-col vs-lg="2">Tipo:</vs-col>
              <vs-col vs-lg="4">{{ moneyList.type_money }}</vs-col>
              <vs-col vs-lg="3">Demoninación:</vs-col>
              <vs-col vs-lg="3">{{ formatPrice(moneyList.amount) }}</vs-col>
            </vs-row>
          </vs-col>
        </vs-row>
      </vs-card>
    </vs-row>
  </vs-popup>
</template>

<script>
export default {
  name: "PopupReport",
  data: () => ({
    itemArray: [],
    moneyList: []
  }),
  props: {
    popupActivo: {
      type: Boolean
    },
    items: {
      type: Object
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