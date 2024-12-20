<template>
  <vs-row>
    <vs-col vs-lg="6" vs-xs="12" vs-sm="12">
      <vs-card>
        <h3 class="card-title"></h3>
        <vs-alert v-if="errors.length" class="mb-3 mt-2" color="danger" :active="inputValid">
          <b> Verifique los error(s):</b>
          <ul class="common-list">
            <li v-for="error in errors" :key="error">{{ error }}</li>
          </ul>
        </vs-alert>
        <hr class="custom-hr"/>
        <!-- Section 1 -->
        <h4 class>Creación de usuarios</h4>
        <vs-row class="mt-4">
          <vs-col vs-lg="6" vs-xs="12">
            <vs-input
                label="Cedula - usuario"
                class="w-100"
                placeholder="Cedula - usuario Here"
                v-model="idUser"
            />
          </vs-col>
          <vs-col vs-lg="6" vs-xs="12" class="mb-4">
            <vs-input
                label="Nombre"
                class="mt-3 mt-md-0 w-100"
                placeholder="Nombre Here"
                v-model="nameUser"
            />
          </vs-col>
          <vs-col vs-lg="6" vs-xs="12">
            <vs-input
                label="Correo - usuario"
                class="w-100"
                placeholder="Correo - usuario Here"
                v-model="emailUser"
            />
          </vs-col>
          <vs-col vs-lg="6" vs-xs="12" class="mb-4">
            <vs-input
                type="password"
                @keypress="isNumber()"
                label="Contraseña"
                maxlength="10"
                class="mt-3 mt-md-0 w-100"
                placeholder="Contraseña Here"
                v-model="passUser"
            />
          </vs-col>
          <vs-col vs-lg="6" vs-xs="12">
            <vs-input
                label="Card-id usuario"
                class="w-100"
                placeholder="Card-ID Here"
                v-model="cardIdUser"
            />
          </vs-col>

        </vs-row>
        <!-- End Section 1 -->
        <!-- Section 2 -->
        <hr class="custom-hr"/>
        <h4 class="pt-3">Asignar privilegios</h4>
        <vs-row class="mt-4">
          <vs-col vs-lg="6" vs-xs="12">
            <vs-select class="selectExample" label="Tipo Usuario" v-model="select1">
              <vs-select-item
                  :key="index"
                  :value="item.code"
                  :text="item.title"
                  v-for="(item,index) in opcionAccount"
              />
            </vs-select>

          </vs-col>
          <vs-col vs-lg="6" vs-xs="12">
            <vs-select class="selectExample" label="Estado Usuario" v-model="select2">
              <vs-select-item
                  :key="index"
                  :value="item.value"
                  :text="item.text"
                  v-for="(item,index) in opcionsStatus"
              />
            </vs-select>
          </vs-col>
          <vs-col class="pt-4" vs-lg="12" vs-xs="12">
            <vs-button @click="saveData">Guardar Usuario</vs-button>
          </vs-col>

        </vs-row>
      </vs-card>
    </vs-col>
    <vs-col vs-lg="6" vs-xs="12" vs-sm="12">
      <TableUser v-bind:key="refresh" @click="getDataEdit"></TableUser>
    </vs-col>
  </vs-row>
</template>

<script>
import axios from "axios";
import propertyRouter from "@/propertyRouter";
import TableUser from "@/components/generic-components/tables/TableUser";


export default {
  name: "User",
  data: () => ({
    errors: [],
    codeUser: null,
    idUser: '',
    nameUser: '',
    emailUser: null,
    passUser: '',
    cardIdUser: '',
    select1: 1,
    select2: "",
    refresh: "",
    opcionAccount: [],
    opcionsStatus: [
      {text: "Seleccionar", value: ""},
      {text: "Activar", value: "A"},
      {text: "Eliminar", value: "E"},
      {text: "Suspender", value: "S"}
    ],
  }),
  methods: {
    isNumber: function(evt) {
      evt = (evt) ? evt : window.event;
      var charCode = (evt.which) ? evt.which : evt.keyCode;
      if ((charCode > 31 && (charCode < 48 || charCode > 57)) && charCode !== 46) {
        evt.preventDefault();
      } else {
        return true;
      }
    },
    checkForm() {
      this.errors = [];
      if (this.emailUser !== null) {
        if (!this.validEmail(this.emailUser)) {
          this.errors.push("Verifique el correo expuesto.");
        }
      }
      if (!this.cardIdUser) this.errors.push("El Id-Card es obligatorio.");
      if (!this.nameUser) this.errors.push("El nombre es obligatorio.");
      if (!this.idUser) this.errors.push("La cédula es obligatoria.");
      if (!this.select2) this.errors.push("El estado es obligatorio");
      if (!this.errors.length) {
        return true;
      }
    },
    /* eslint-disable */
    validEmail: function (email) {
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,24}))$/;
      return re.test(email);
    },
    async getDataUser() {
      console.log('The connection User')
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing +
          propertyRouter.routerStationActive, {
        "idUser": this.idUser,
        "nameUser": this.nameUser,
        "typeUser": this.select1
      }).then((response) => {
        console.log(response)
      });
    },
    async saveData() {
      this.checkForm();
      if (this.inputValid) {
        return;
      }
      console.log(this.select1)
      console.log(this.select2)
      let data = {
        "identification": this.idUser,
        "title": this.nameUser,
        "status": this.select2,
        "account": {
          "code": this.select1
        },
        "email": this.emailUser,
        "secret": this.passUser,
        "card_id": this.cardIdUser
      };

      if (this.codeUser !== null) {
        data = {
          "code": this.codeUser,
          "identification": this.idUser,
          "title": this.nameUser,
          "status": this.select2,
          "account": {
            "code": this.select1
          },
          "email": this.emailUser,
          "secret": this.passUser,
          "card_id": this.cardIdUser
        };
      }
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing +
          propertyRouter.routerSaveUser, data
      )
          .then((response) => {
            console.log(response)
            if (response.data.code === 1) {
              this.$swal.fire('Saved!', '', 'success');
              this.refresh = this.cardIdUser;
            }
          })
          .catch((errorApi) => {
            errorApi = 'La conexión con el servicio de alertas no es posible';
            this.errors.push(errorApi);
          });
    },

    async searchProfileUser() {
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing +
          propertyRouter.routerSearchProfileUser)
          .then((response) => {
            if (response.data.code === 1) {
              this.opcionAccount = response.data.info;
            }
          }).catch((errorApi) => {
            errorApi = 'La conexión con el servicios de perfiles no es posible';
            this.errors.push(errorApi);
          });

    },
    getDataEdit(editAtmSelection) {
      for (let valor of editAtmSelection) {
        console.log(valor)
        this.codeUser = valor.code
        this.idUser = valor.identification
        this.nameUser = valor.title
        this.emailUser = valor.email
        this.cardIdUser = valor.card_id
        this.select1 = valor.account.code
        this.select2 = valor.status
        this.disableSave = true;
      }
    }
  },
  computed: {
    inputValid() {
      console.log(this.cardIdUser)
      console.log(this.select2)
      console.log(this.nameUser)
      console.log(this.idUser)

      if (this.cardIdUser && this.idUser && this.nameUser && this.select2) {
        return false
      } else {
        return true
      }
    }
  },
  components: {
    TableUser
  },
  created() {
    this.searchProfileUser();
  }
}
</script>

<style scoped>

</style>