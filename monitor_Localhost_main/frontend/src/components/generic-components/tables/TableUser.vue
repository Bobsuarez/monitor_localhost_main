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
    <div v-for="connection of connectionList" v-bind:key="connection">
      <vs-card>
        <div>
          <h1>{{ connection.nameAtm }}</h1>
          <vs-table multiple
                    search
                    max-items="20"
                    pagination
                    :data="connection.listUserAtm"
                    v-model="multiSelected"
                    id="#table">

            <template slot="header">
              <h3 class="ml-5">Usuarios Creados</h3>
            </template>

            <template slot="thead">
              <vs-th>Identificacion</vs-th>
              <vs-th>NombreUser</vs-th>
              <vs-th>Card_Id</vs-th>
              <vs-th>Nivel</vs-th>
              <vs-th>Estado</vs-th>
            </template>

            <template slot-scope="{data}">
              <vs-tr :data="tr" :key="indextr" v-for="(tr, indextr) in data">
                <vs-td :data="data[indextr]">{{ data[indextr].identification }}</vs-td>
                <vs-td :data="data[indextr]">{{ data[indextr].title }}</vs-td>
                <vs-td :data="data[indextr]">{{ data[indextr].card_id }}</vs-td>
                <vs-td :data="data[indextr]">{{ data[indextr].account.title }}</vs-td>
                <vs-td :data="data[indextr]">{{ data[indextr].status }}</vs-td>
              </vs-tr>
            </template>
          </vs-table>
        </div>
      </vs-card>
    </div>
    <vs-row class="ml-3">
      <vs-col vs-lg="4">
        <vs-button color="success"
                   @click="getDataConnection()"
                   type="border">
          Consultar
        </vs-button>
      </vs-col>
      <vs-col vs-lg="4">
        <vs-button
            color="primary"
            type="border"
            @click="getDataATM()">
          Editar
        </vs-button>
      </vs-col>
      <vs-col vs-lg="4">
        <vs-button
            color="danger"
            type="border"
            @click="deleteATM()">
          Eliminar
        </vs-button>
      </vs-col>
    </vs-row>
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
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing + propertyRouter.routerSearchUser)
          .then((response) => {
            console.log(response)
            if (response.data.code === -1001) {
              this.errors.push('No hay datos de activos');
              return;
            }
            this.errors.length = 0;
            this.connectionList = response.data.info;
            console.log(this.connectionList)
          })
    },
    getDataATM() {
      if (this.multiSelected.length === 1) {
        this.$emit('click', this.multiSelected)
      }
    },
    async deleteATM() {
      var datos = [];
      for (let valor of this.multiSelected) {
        datos.push({"card_id": valor.card_id});
      }
      if (this.multiSelected.length === 0) {
        return;
      }
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing + propertyRouter.routerDeleteUser,
          datos)
          .then((response) => {
            console.log(response)
            if (response.data.code === 1) {
              this.$swal.fire('Saved!', '', 'success');
              this.getDataConnection();
            }
          })
          .catch((errorApi) => {
            errorApi = 'La conexión con el servicio de eliminar no es posible';
            this.errors.push(errorApi);
          });

    }
  }
}

</script>

<style scoped>

</style>