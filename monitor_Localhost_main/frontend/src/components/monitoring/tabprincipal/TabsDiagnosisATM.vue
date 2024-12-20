<template>
  <vs-card>
    <vs-tabs alignment="fixed">
      <vs-tab label="Cajeros" @click="cleanNameAtmPop()">
        <div>
          <station></station>
        </div>
      </vs-tab>
      <vs-tab label="Niveles" @click="activePopUp(); cleanNameAtmPop()">
        <popup :popupActivo="popupTab" @click="getPopupName"></popup>
        <div>
          <level v-if="nameAtmProup !== null" :nameAtm="nameAtmProup"></level>
        </div>
      </vs-tab>
      <vs-tab label="Reportes" @click="activePopUp(); cleanNameAtmPop()">
        <popup :popupActivo="popupTab" @click="getPopupName"></popup>
        <div>
          <reports v-if="nameAtmProup !== null" :atm-name="nameAtmProup"></reports>
        </div>
      </vs-tab>
    </vs-tabs>
  </vs-card>
</template>

<script>
import station from "../station/Station";
import level from "../level/Level";
import pharseDay from "../../../data/pharseday/pharseDay";
import popup from "../../generic-components/popup/Popup";
import reports from "../Reports/Reports";

export default {
  name: "TabsDiagnosisATM",
  data: () => ({
    pharseDayData: pharseDay,
    dato: '',
    popupTab: false,
    nameAtmProup: null,
    received_messages: [],
    send_message: "OK",
  }),
  components: {
    station,
    level,
    popup,
    reports
  },
  methods: {
    getPopupName(nameAtmSelection) {
      this.nameAtmProup = nameAtmSelection;
      this.popupTab = false
    },
    randomColorNotify() {
      function getRandomInt(min, max) {
        return Math.floor(Math.random() * (max - min)) + min;
      }

      let color = `rgb(${getRandomInt(0, 200)},${getRandomInt(0, 200)},${getRandomInt(0, 200)})`
      this.$vs.notify({
        title: '.::. Bienvenido .::. 7s  Frase del día',
        time: 7000,
        text: this.dato,
        color: color,
        icon: 'verified_user'
      })
    },
    getPharseDay() {
      let randomPharse = Math.floor(Math.random() * (this.pharseDayData.dataPharseDay.length));
      return this.pharseDayData.dataPharseDay[randomPharse].toString();
    },
    activePopUp() {
      this.popupTab = true;
    },
    cleanNameAtmPop() {
      this.nameAtmProup = null;
    },

  },
  created() {
   this.dato = this.getPharseDay();
    this.randomColorNotify();
  },
}
</script>
<style>
.vs-tabs--li{
  font-size: 21px;
}
</style>