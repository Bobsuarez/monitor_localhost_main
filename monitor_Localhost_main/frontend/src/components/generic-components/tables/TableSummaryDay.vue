<template>
  <!--
         /////////////////
         Alert
         /////////////////
       -->

  <div>
    <div>
      <vs-col>
        <vs-alert v-if="errors.length > 0" class="mb-5 mt-5" color="danger" style="height: 55px">
          <b>Error</b>
          <ul class="common-list">
            <li v-for="error in errors" :key="error.id">{{ error }}</li>
          </ul>
        </vs-alert>
      </vs-col>
    </div>

    <div>
      <vs-card>
        <div class="" v-for="ind of fillEmptyList" v-bind:key="ind.id">
          <vs-row>
            <vs-col vs-lg="3">
              <h2>Total Retornos:</h2>
            </vs-col>
            <vs-col vs-lg="3">
              <h2>{{ formatPrice(ind.total_refunds) }}</h2>
            </vs-col>
            <vs-col vs-lg="3">
              <h2>Total Recaudos:</h2>
            </vs-col>
            <vs-col vs-lg="3">
              <h2>{{ formatPrice(ind.total_colletion) }}</h2>
            </vs-col>
            <vs-col vs-lg="12">
              <vs-table multiple
                        search
                        max-items="5"
                        pagination
                        :data="ind.list_detail"
                        v-model="multiSelected"
                        @selected="handleSelected"
                        id="#table1">
                <!--              <template slot="header">-->
                <!--                <h3 class="ml-3">Detalle díario</h3>-->
                <!--              </template>-->
                <template slot="thead">
                  <vs-th>Fecha</vs-th>
                  <vs-th>Retorno</vs-th>
                  <vs-th>Recaudos</vs-th>
                </template>

                <template slot-scope="{data}">
                  <vs-tr :data="tr" :key="indextr" v-for="(tr, indextr) in data">
                    <vs-td :data="data[indextr].instant">{{ data[indextr].instant }}</vs-td>
                    <vs-td :data="data[indextr].refunds">{{ formatPrice(data[indextr].refunds) }}</vs-td>
                    <vs-td :data="data[indextr].collection">{{ formatPrice(data[indextr].collection) }}</vs-td>
                  </vs-tr>
                </template>
              </vs-table>
            </vs-col>
          </vs-row>
        </div>
      </vs-card>

      <vs-card>
      <div v-for="index of summaryList " v-bind:key="index.id">
        <vs-row>

        <vs-col class="pb-4" vs-lg="12">
          <vs-table multiple
                    search
                    max-items="5"
                    pagination
                    :data="index.list_detail"
                    v-model="multiSelected"
                    @selected="handleSelected"
                    id="#table">

            <template slot="header">
              <vs-col vs-lg="4">
                <h2 v-if="index.type === 'F'">Recarga</h2>
                <h2 v-else>Retiro</h2>
              </vs-col>
              <vs-col vs-lg="6">
                <h2>Total : {{ formatPrice(index.totalfinal) }}</h2>
              </vs-col>
            </template>

            <template slot="thead">
              <vs-th>Fecha</vs-th>
              <vs-th>Cantidad</vs-th>
              <vs-th>Denominación</vs-th>
              <vs-th>Valor total día</vs-th>
            </template>

            <template slot-scope="{data}">
              <vs-tr :data="tr" :key="indextr" v-for="(tr, indextr) in data">
                <vs-td :data="data[indextr].instant">{{ data[indextr].instant }}</vs-td>
                <vs-td :data="data[indextr].quantity">{{ data[indextr].quantity }}</vs-td>
                <vs-td :data="data[indextr].amount">{{ formatPrice(data[indextr].amount) }}</vs-td>
                <vs-td :data="data[indextr].total_amount">{{ formatPrice(data[indextr].total_amount) }}</vs-td>
              </vs-tr>
            </template>
          </vs-table>
        </vs-col>
        </vs-row>
      </div>
      </vs-card>
    </div>
    <vs-row>
      <vs-col vs-lg="6" class="ml-auto pt-4">
        <vs-button color="danger" type="border" @click="initialPdf()" icon="cloud_download" style="margin-left: 450px">
          Descargar PDF
        </vs-button>
      </vs-col>
    </vs-row>
  </div>


</template>

<script>
import axios from "axios";
import propertyRouter from "@/propertyRouter";
import jsPDF from 'jspdf'
import 'jspdf-autotable'

export default {
  name: "TableSummaryDay",
  data: () => ({
    summaryList: [],
    fillEmptyList: [],
    errors: [],
    multiSelected: [],
    popupTab: false,
    disableSelect: true,
    itemview: []
  }),
  components: {},
  props: {
    dateInitial: {
      type: String,
      required: true
    },
    dateFinal: {
      type: String,
      required: true
    },
    atmName: {
      type: String,
      required: true
    }
  },
  methods: {
    async getDataSummary() {
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing + propertyRouter.routerSummary +
          propertyRouter.separationRouter + propertyRouter.paramInitialDate + propertyRouter.equalOperator + this.dateInitial + '&' +
          propertyRouter.paramFinalDate + propertyRouter.equalOperator + this.dateFinal + '&' +
          propertyRouter.paramName + propertyRouter.equalOperator + this.atmName)
          .then((response) => {
            if (response.data.code === -1000) {
              return
            }
            if (response.data.info !== null) {
              sessionStorage.setItem('localSaveListSummary', JSON.stringify(response.data.info));
              this.summaryList = JSON.parse(sessionStorage.getItem('localSaveListSummary'));
              return;
            }
            this.errors.push(response.data.message);
          })
          .catch((errorApi) => {
            errorApi = 'La conexión con el servicio reportes no es posible';
            this.errors.push(errorApi);
          })

    },
    async getDataFillEmpty() {
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing + propertyRouter.routerFillEmpty +
          propertyRouter.separationRouter + propertyRouter.paramInitialDate + propertyRouter.equalOperator + this.dateInitial + '&' +
          propertyRouter.paramFinalDate + propertyRouter.equalOperator + this.dateFinal + '&' +
          propertyRouter.paramName + propertyRouter.equalOperator + this.atmName)
          .then((response) => {
            if (response.data.code === -1000) {
              return
            }
            if (response.data.info !== null) {
              sessionStorage.setItem('localSaveListFill', JSON.stringify(response.data.info));
              this.fillEmptyList = JSON.parse(sessionStorage.getItem('localSaveListFill'));
              return;
            }
            this.errors.push(response.data.message);
          })
          .catch((errorApi) => {
            errorApi = 'La conexión con el servicio reportes no es posible';
            this.errors.push(errorApi);
          })

    },
    handleSelected(tr) {
      if (this.disableSelect) {
        this.itemview = tr;
        // sessionStorage.setItem('localSaveItem', JSON.stringify(tr));
        this.popupTab = true;
        this.disableSelect = false
        return
      }
      this.disableSelect = true;
      this.popupTab = false;
    },
    errorNotFound() {
      this.$vs.notify({
        title: `Debe seleccionar un item a exportar`,
        text: `-------------------------------------`,
        color: 'danger'
      });
    },
    initialPdf() {
      if (this.multiSelected.length > 0) {
        this.exportPDF();
        return;
      }
      this.errorNotFound()
    },
    exportPDF() {
      let vm = this
      let columns = [
        {title: "Fecha", dataKey: "instant"},
        {title: "Valor", dataKey: "amount"},
        {title: "Operación", dataKey: "kind"},
        {title: "Cantidad", dataKey: "quantity"},
        {title: "$ Total", dataKey: "total_amount"}
      ];
      let doc = new jsPDF('p', 'pt');
      doc.text('Balance diario', 40, 40);
      doc.autoTable(columns, vm.multiSelected, {
        margin: {top: 60},
      });
      doc.save('BalanceDiario.pdf');
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(2).replace('.', ',')
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")
    }
  }
  ,
  created() {
    this.getDataSummary()
    this.getDataFillEmpty()
  }
  ,


}
</script>

<style scoped>

</style>