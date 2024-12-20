<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>


<script>
import 'sweetalert2/dist/sweetalert2.min.css';
import axios from "axios";
import propertyRouter from "@/propertyRouter";
import {useSound} from '@vueuse/sound';
import alertError from '/src/assets/tone/alert.mp3';


let webSocket = null;
let alertSound = null;
export default {
  name: 'app',
  data: () => ({
    error: [],
    dataLowLevel: [],
    infoAtmList: []
  }),
  setup() {
    alertSound = useSound(alertError)
  },
  components: {},
  watch: {},
  methods: {
    connect() {
      console.log('Init connection');
      webSocket = new WebSocket('ws://127.0.0.1:8080/monitor/websocket',
          'subprotocol.demo.websocket');
      webSocket.onopen = function () {
        console.log('Client connection opened');
        console.log('Subprotocol: ' + webSocket.protocol);
        console.log('Extensions: ' + webSocket.extensions);
      };
      webSocket.onmessage = (event) => {
        this.alertSweet(JSON.parse(event.data));
        this.validateAlert(true);
      };
      webSocket.onerror = function (event) {
        console.log('Client error: ' + event);
      };
      webSocket.onclose = function (event) {
        console.log('Client connection closed: ' + event.code);
      };
    },
    alertSweet(val) {

      for (let list of val) {
        this.$swal.fire({
          icon: 'error',
          showDenyButton: true,
          confirmButtonText: 'Aceptar',
          denyButtonText: 'Posponer',
          allowOutsideClick: false,
          allowEscapeKey: false,
          allowEnterKey: false,
          html: `<h1>` + list.Location + `</h1>
        ${list.error.map(
              cap => `<h3> ${cap.toString()} </h3>`
          )} `,
          title: 'Oops...',
          footer: '<a href>¿ Necesitas ayuda ?</a>'
        }).then((result) => {
          /* Read more about isConfirmed, isDenied below */
          if (result.isConfirmed) {
            this.infoAtmList = JSON.parse(sessionStorage.getItem('localSaveList'));
            for (let data of this.infoAtmList) {
              this.acceptAlert(data.location)
            }
          } else if (result.isDenied) {
            this.$swal.fire('Los cambios no se guardan', '', 'info')
            this.validateAlert(false);
          }
        });

      }
    },
    async acceptAlert(nameConnection) {
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing +
          propertyRouter.routerSaveAlert + "?name=" + nameConnection
      )
          .then((response) => {
            if (response.data.code === 1) {
              this.$swal.fire('Guardado!!', '', 'success');
              this.validateAlert(false);
            }
          })
          .catch((errorApi) => {
            errorApi = 'La conexión con el servicio de alertas no es posible';
            this.errors.push(errorApi);
            this.validateAlert(true);
          });
    },
    async getDataConnection() {
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing + propertyRouter.routerStationActive)
          .then((response) => {
            if (response.data.code === -1001) {
              this.errors.push('No hay datos de activos');
              return;
            }
            this.errors = null;
            this.connectionList = response.data.info;
          })
    },

    validateAlert(condition) {
      if (condition) {
        alertSound.play();
        return;
      }
      alertSound.stop();
    },

  },


  created() {
    this.getDataConnection();
    this.connect();
  },

};

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
