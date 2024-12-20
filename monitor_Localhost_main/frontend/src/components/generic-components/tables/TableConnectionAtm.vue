<template>
  <div>
    <vs-col>
      <vs-alert v-if="errors.length > 0" class="mb-5 mt-5" color="danger" style="height: 55px">
        <b>Error</b>
        <ul class="common-list">
          <li v-for="error in errors" :key="error.id">{{ error }}</li>
        </ul>
      </vs-alert>
    </vs-col>
    <vs-card>
      <div>
        <vs-table multiple
                  search
                  max-items="20"
                  pagination
                  :data="connectionList"
                  v-model="multiSelected"
                  id="#table">

          <template slot="header">
            <h3 class="ml-5">Cajeros activos</h3>
          </template>

          <template slot="thead">
            <vs-th>ip-Host</vs-th>
            <vs-th>Nombre Cajero</vs-th>
            <vs-th>Ubicación</vs-th>
            <vs-th>Estado</vs-th>
          </template>

          <template slot-scope="{data}">
            <vs-tr :data="tr" :key="indextr" v-for="(tr, indextr) in data">
              <vs-td :data="data[indextr].ip">{{ data[indextr].ip }}</vs-td>
              <vs-td :data="data[indextr].name">{{ data[indextr].name }}</vs-td>
              <vs-td :data="data[indextr].location">{{ data[indextr].location }}</vs-td>
              <vs-td :data="data[indextr].status">{{ data[indextr].status }}</vs-td>
            </vs-tr>
          </template>
        </vs-table>
      </div>
    </vs-card>
    <div>
      <vs-row>
        <vs-col vs-lg="4" class="pt-3 pb-3">
          <vs-button color="success" @click="getDataConnection()" type="border" icon="cloud_download">
            Consultar
          </vs-button>
        </vs-col>
        <vs-col vs-lg="4" class="pt-3 pb-3">
          <vs-button
              color="primary"
              type="border"
              icon="cloud_download"
              @click="getDataATM">
            Editar
          </vs-button>
        </vs-col>

      </vs-row>
    </div>

  </div>
</template>

<script>
import axios from "axios";
import propertyRouter from "@/propertyRouter";

export default {
  name: "TableConnectionAtm",
  data: () => ({
    connectionList: [],
    errors: [],
    multiSelected: [],
  }),
  methods: {
    async getDataConnection() {
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing + propertyRouter.routerStationActive)
          .then((response) => {
            if (response.data.code === -1001) {
              this.errors.push('No hay datos de activos');
              return;
            }
            this.errors.length = 0;
            this.connectionList = response.data.info;
          })
    },
    getDataATM() {
      if (this.multiSelected.length === 1) {
        console.log(this.multiSelected)
        this.$emit('click', this.multiSelected)
      }

    },
    // async deleteAtm() {
    //   if (this.multiSelected.length !== 1) {
    //     return;
    //   }
    //   let data = this.multiSelected;
    //   await axios.post('http://localhost:8080/monitor/api/atm/disable', data)
    //       .then((response) => {
    //         console.log(response);
    //         this.getDataConnection();
    //       })
    // }

  }

}
</script>

<style scoped>

</style>