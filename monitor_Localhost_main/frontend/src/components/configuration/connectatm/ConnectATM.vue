<template>
  <vs-row>
    <vs-col vs-lg="6" vs-xs="12" vs-sm="12">
      <vs-card>
        <h3 class="card-title"></h3>
        <!--
          /////////////////
          Alert
          /////////////////
        -->
        <vs-col>
          <vs-alert v-if="errors.length > 0" class="mb-5 mt-5" color="danger" style="height: 55px">
            <b>Error</b>
            <ul class="common-list">
              <li v-for="error in errors" :key="error.id">{{ error }}</li>
            </ul>
          </vs-alert>
        </vs-col>


        <hr class="custom-hr"/>
        <!-- Section 1 -->
        <h4 class>Informacion del cajero</h4>
        <vs-row class="mt-4">
          <vs-col vs-lg="12" vs-xs="12" class="mb-4">
            <vs-input
                v-on:keypress="disableButtonSave"
                v-on:keyup.delete="disableButtonSave"
                required="required"
                icon="mode_edit"
                label="Ip - host"
                placeholder="ip Here"
                class="mt-3 w-100"
                v-model="ipname"
            />
          </vs-col>

          <vs-col class="pb-4">
            <vs-button @click="checkForm()" color="dark">Test-Conexión</vs-button>
          </vs-col>

          <vs-col vs-lg="12" vs-xs="12" class="mb-4">
            <vs-input icon="mode_edit"
                      label="Nombre cajero"
                      class="w-100"
                      disabled="true"
                      placeholder="Nombre cajero Here"
                      v-model="nameAtm"
            />
          </vs-col>

          <vs-col vs-lg="12" vs-xs="12" class="mb-4">
            <vs-input
                icon="mode_edit"
                label="Ubicacion del cajero"
                class="mt-3 mt-md-0 w-100"
                placeholder="Ubicacion Here"
                v-model="locationbd"
            />
          </vs-col>
        </vs-row>
        <!-- End Section 1 -->
        <div v-if="disableSave" class="btn-alignment">
          <vs-button @click="addConnection()">Guardar</vs-button>
        </div>
        <PopupOk :popupActivo="viewPopupOk" :establishe="answerConnection" @click="getPopupName"></PopupOk>
        <!-- End Section 2 -->
      </vs-card>
    </vs-col>
    <vs-col vs-lg="6" vs-xs="12" vs-sm="12">
      <TableConnectionAtm @click="getDataEdit"></TableConnectionAtm>
    </vs-col>
  </vs-row>
</template>

<script>
import axios from "axios";
import propertyRouter from "@/propertyRouter";
import PopupOk from "@/components/generic-components/popup/PopupOk";
import dataConnection from "@/dataConnection";
import TableConnectionAtm from "@/components/generic-components/tables/TableConnectionAtm";

export default {
  name: "ConnectATM",
  data: () => ({
    dataConnection,
    errors: [],
    nameAtm: '',
    ipname: null,
    locationbd: '',

    /* false - the popup is evident
       true -  the popup is hidden  */
    viewPopupOk: false,

    /* The connection response and it
       was sent to the popup */
    answerConnection: '',

    /* The connection response and it
       was sent to the popup */
    answer: '',

    /* The disable button */
    disableSave: true

  }),
  methods: {
    getPopupName(modeDisable) {
      this.viewPopupOk = modeDisable;
    },
    getDataEdit(editAtmSelection) {
      for (let valor of editAtmSelection) {
        this.ipname = valor.ip
        this.nameAtm = valor.name
        this.locationbd = valor.location
        this.disableSave = true;
      }
    },


    async addConnection() {
      console.log('The connection starts')
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing +
          propertyRouter.routerSaveConnection, {
            "name": this.nameAtm,
            "ip": this.ipname,
            "port": dataConnection.port_bd,
            "user": dataConnection.user_bd,
            "password": dataConnection.pass_bd,
            "bdname": dataConnection.name_bd,
            "location": this.locationbd,
            "status": "Enable"
          }
      )
          .then((response) => {
            console.log('The connection starts response')
            console.log(response)
            // Se borra los datos de la session para volver a cargar la estacion en la pagina inicial
            sessionStorage.clear();
            console.log(sessionStorage)
            this.answerConnection = 'Se adicionó un cajero'
            this.viewPopupOk = true;

          })
          .catch((errorApi) => {
            errorApi = 'La conexión con el servicio de guardar cajero no es posible';
            this.errors.push(errorApi);
          })

    },
    async getDataATM() {
      sessionStorage.setItem('localSaveList', null);
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing +
          propertyRouter.routerStationActive)
          .then((response) => {
            console.log('/////////////// 163')
            console.log(response);
            if (response.data.info !== null) {
              sessionStorage.setItem('localSaveList', JSON.stringify(response.data.info));
              return;
            }
            this.errors.push(response.data.message);
          })
          .catch((errorApi) => {
            errorApi = 'La conexión con el servicio de cajeros no es posible';
            this.errors.push(errorApi);
          })
    },




    async test() {
      console.log('The test starts')
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing +
          propertyRouter.routerTest, {
            "name": this.nameAtm,
            "ip": this.ipname,
            "port": dataConnection.port_bd,
            "user": dataConnection.user_bd,
            "password": dataConnection.pass_bd,
            "bdname": dataConnection.name_bd,
          }
      )
          .then((response) => {
            console.log('The test starts response')
            console.log(response)
            if (response.data.info !== 'No Connection') {
              this.answerConnection = 'Conectado'
              this.viewPopupOk = true;
              this.disableSave = true;
              // se trae el nombre del cajero
              this.addNameAtm(response.data.info);
              return;
            }
            this.answerConnection = 'No se conecto'
            this.viewPopupOk = true;
            // this.disableSave = false;
          })
          .catch((errorApi) => {
            errorApi = 'La conexión con el servicio de testing no es posible';
            this.errors.push(errorApi);
          });

    },
    async addNameAtm(nameStationResponse) {
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing +
          propertyRouter.routerSearchATM + propertyRouter.separationRouter +
          propertyRouter.paramName + propertyRouter.equalOperator + nameStationResponse)
          .then((response) => {
            console.log('--------------')
            console.log(response)
                if (response.data.code !== -1003) {
                  this.nameAtm = response.data.info;
                  return
                }
                this.errors.push(response.data.message);
              }
          ).catch((errorApi) => {
            errorApi = 'La conexión con el servicio de consulta de cajero no es posible';
            this.errors.push(errorApi);
          })
    },


    checkForm() {
      this.errors = [];
      if (!this.ipname) {
        this.errors.push("IP-Host es requerida");
      }
      if (!this.errors.length) {
        this.test();
        return true;
      }
    },
    disableButtonSave() {
      // this.disableSave = false;
    }


  },
  components: {
    PopupOk,
    TableConnectionAtm,
  },
  created() {
    console.log('The automatic connection to the ATM starts');
  },


}
</script>

<style scoped>

</style>