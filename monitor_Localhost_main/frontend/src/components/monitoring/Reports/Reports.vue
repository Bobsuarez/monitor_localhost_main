<template>
  <vs-row vs-justify="center" vs-align="center">
    <!--
         /////////////////
         header atm
         /////////////////
       -->
    <vs-col vs-lg="6">
      <vs-card>
        <h1>{{ atmName }}</h1>
        <div class="container pb-4 pt-4" style="max-width:250px">
          <img v-bind:src="require('../../../assets/images/product/cajero.png')"
               alt="Cajero Accesspark"
               class="cajero card-img-top">
        </div>
      </vs-card>
    </vs-col>
    <!--
         /////////////////
         Date y logo
         /////////////////
       -->
    <vs-col vs-lg="6">
      <vs-card>
        <div class="container">
          <vs-row class="mb-3">
            <vs-col vs-lg="6"><h3>Fecha inicial</h3></vs-col>
            <vs-col vs-lg="6">
              <VueDatePicker v-model="initialdate"
                             min-date="2010-01-01"
                             :max-date="new Date()"/>
            </vs-col>
          </vs-row>
          <vs-row>
            <vs-col vs-lg="6"><h3>Fecha final</h3></vs-col>
            <vs-col vs-lg="6">
              <VueDatePicker v-model="finaldate"
                             min-date="2010-01-01"
                             :max-date="new Date()"/>
            </vs-col>
          </vs-row>
        </div>
      </vs-card>
      <vs-card>
        <img v-bind:src="require('../../../assets/images/logo/logo-text.png')">
      </vs-card>
    </vs-col>

    <!--
            /////////////////
            Options Reports
            /////////////////
          -->
    <vs-col vs-lg="12">
      <vs-card>
        <tabsReport v-bind:key="initialdate"
                    v-if="initialdate !== null"
                    :date-initial="functionDate(initialdate)"
                    :atm-name="atmName"
                    :date-final="functionDate(finaldate)"></tabsReport>
      </vs-card>
    </vs-col>
    <vs-col>
      <div style="background-color: #dc3545">_</div>
    </vs-col>
  </vs-row>
</template>

<script>

import moment from 'moment'
import tabsReport from "./tabsreports/TabsReport";
import {VueDatePicker} from '@mathieustan/vue-datepicker';
import '@mathieustan/vue-datepicker/dist/vue-datepicker.min.css'


export default {
  name: "reports",
  data: () => ({
    finaldate: new Date(),
    initialdate: null,
    forceReload: 0
  }),
  props: {
    atmName: {
      type: String,
      required: true
    }
  },
  components: {
    tabsReport,
    VueDatePicker
  },
  methods: {
    reload() {
      this.forceReload += 1
    },
    functionDate(value) {
      if (value) {
        return moment(String(value)).format('YYYY-MM-DD%20HH:mm')
      }
    },
  },
  mounted() {
    this.initialdate = null;
  }
}
</script>

<style scoped>
.cajero {
  max-width: 100%;
  background-size: contain;
}

</style>