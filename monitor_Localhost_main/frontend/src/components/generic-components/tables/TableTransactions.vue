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
    <vs-card>
    <div >
      <vs-table multiple
                search
                max-items="20"
                pagination
                :data="transactionList"
                v-model="multiSelected"
                @selected="handleSelected"
                id="#table">

        <template slot="header">
          <h3 class="ml-3">Transacciones</h3>
        </template>

        <template class="text-center" slot="thead">
          <vs-th>Fecha</vs-th>
          <vs-th>VehiculoID</vs-th>
          <vs-th>Estado</vs-th>
          <vs-th>Prefijo</vs-th>
          <vs-th>N° factura</vs-th>
          <vs-th>Total</vs-th>
        </template>

        <template slot-scope="{data}">
          <vs-tr :data="tr" :key="indextr" v-for="(tr, indextr) in data">
            <vs-td :data="data[indextr].instant">{{ data[indextr].instant }}</vs-td>
            <vs-td :data="data[indextr].id">{{ data[indextr].id }}</vs-td>
            <vs-td :data="data[indextr].status">{{ data[indextr].status }}</vs-td>
            <vs-td :data="data[indextr].prefix">{{ data[indextr].prefix }}</vs-td>
            <vs-td :data="data[indextr].bill_number">{{ data[indextr].bill_number }}</vs-td>
            <vs-td :data="data[indextr].total">{{ data[indextr].total }}</vs-td>
          </vs-tr>
        </template>
      </vs-table>
      <PopupReport :popupActivo="popupTab" :items="itemview"></PopupReport>
    </div>
    </vs-card>
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
import PopupReport from "../popup/PopupReport";
import jsPDF from 'jspdf'
import 'jspdf-autotable'

export default {
  name: "TableTransactions",
  data: () => ({
    transactionList: [],
    errors: [],
    multiSelected: [],
    popupTab: false,
    disableSelect: true,
    itemview: {}
  }),
  components: {
    PopupReport,
  },
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
    async getDataTransaction() {
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing + propertyRouter.routerTransaction +
          propertyRouter.separationRouter + propertyRouter.paramInitialDate + propertyRouter.equalOperator + this.dateInitial + '&' +
          propertyRouter.paramFinalDate + propertyRouter.equalOperator + this.dateFinal + '&' +
          propertyRouter.paramName + propertyRouter.equalOperator + this.atmName)
          .then((response) => {
            if (response.data.code === -1000) {
              return
            }
            if (response.data.info !== null) {
              sessionStorage.setItem('localSaveListTransaction', JSON.stringify(response.data.info));
              this.transactionList = JSON.parse(sessionStorage.getItem('localSaveListTransaction'));
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
        {title: "VehiculoId", dataKey: "id"},
        {title: "Estado", dataKey: "status"},
        {title: "Prefijo", dataKey: "prefix"},
        {title: "N° factura", dataKey: "bill_number"},
        {title: "Total", dataKey: "total"},
      ];
      let doc = new jsPDF('p', 'pt');
      doc.text('Transacciones', 40, 40);
      doc.autoTable(columns, vm.multiSelected, {
        margin: {top: 60},
      });
      doc.save('Transacción.pdf');
    },
  }
  ,
  created() {
    this.getDataTransaction()
  }
  ,


}
</script>

<style scoped>


</style>