<template>
  <div>
    <!-- Header -->
    <div class="d-md-flex">
      <div>
        <h5 class="card-subtitle text-muted font-weight-normal">{{ driver }}</h5>
      </div>
    </div>
    <!-- Chart and details section -->
    <vs-row>
      <vs-col vs-lg="12" vs-xs="12" vs-sm="12">
        <vue-apex-charts
            type="radialBar"
            height="200"
            :options="chartOptions"
            :series="[value]"
        ></vue-apex-charts>
      </vs-col>
      <vs-col vs-lg="12" vs-xs="12" vs-sm="12">
        <h1 class="display-5  ma-5 font-medium">{{ namecard }}</h1>
        <h1 class="display-7 ma-5 font-medium ">{{ denomination }}</h1>
        <vs-button class="primary" @click="activePopUp()">Detalle</vs-button>
        <PopLevel :popupActivo="popupTab" :items="itemslevel" @click="getPopupName"></PopLevel>
      </vs-col>
    </vs-row>
  </div>

</template>

<script>
import VueApexCharts from "vue-apexcharts";
import PopLevel from "../../generic-components/popup/PopLevel";

//import ApexDataRadioBar from "@/data/charts/ApexDataRadioBar";

export default {
  name: "card",
  data: () => ({
    popupTab: false,
    chartOptions: {
      labels: [""],
      plotOptions: {
        radialBar: {
          hollow: {
            margin: 15,
            size: "70%"
          },
          dataLabels: {
            showOn: "always",
            name: {
              show: false
            },
            value: {
              color: "#111",
              fontSize: "28px",
              align: "center",
              show: true
            }
          }
        },
      },
      stroke: {
        lineCap: "round"
      },
      colors: [function ({value = this.value}) {
        if (value < 25) {
          return '#FF2D00'
        } else if (value > 25 && value < 50) {

          return '#FF6C00'

        } else {
          return '#00BC1D'
        }
      }],
    },

    //apexDataRadioBar: ApexDataRadioBar
  }),
  methods: {
    activePopUp() {
      this.popupTab = true;
    },
    getPopupName(click) {
      this.popupTab = click
    }
  },
  components: {
    VueApexCharts,
    PopLevel,
  },
  props: {
    namecard: String,
    value: Number,
    denomination: String,
    driver: String,
    itemslevel: {
      type: Array
    }
  },

}
</script>

<style scoped>

</style>