<template>
  <vs-row>
    <vs-alert v-if="errors.length > 0" class="mb-5 mt-5" color="danger">
      <b>Error</b>
      <ul class="common-list">
        <li v-for="error in errors" :key="error.id">{{ error }}</li>
      </ul>
    </vs-alert>
    <vs-row vs-justify="center" vs-align="center" v-for="dato in infoSend" v-bind:key="dato.id">
      <vs-col vs-lg="3" vs-xs="12" vs-sm="12" class="ml-1" v-for="dat of dato" v-bind:key="dat.id">
        <vs-card>
          <card :namecard="dat.type" :value="dat.percentage" :denomination="dat.amount" :driver="dat.driver"
                :itemslevel="dat.cash_list"></card>
        </vs-card>
      </vs-col>
    </vs-row>
  </vs-row>
</template>

<script>
import card from "../../generic-components/card/Card";
import axios from "axios";
import propertyRouter from "@/propertyRouter";

export default {
  name: "level",
  data: () => ({
    // Month Table
    atmSelectItem: 0,
    infoSend: [],
    errors: []

  }),
  components: {
    card
  },
  methods: {
    async queryLevel(nameAtm) {
      await axios.post(propertyRouter.host + propertyRouter.port + propertyRouter.packing +
          propertyRouter.routerLevel + propertyRouter.separationRouter + propertyRouter.paramName
          + propertyRouter.equalOperator + nameAtm)
          .then((response) => {
            if (response.data.code === 1) {
              this.infoSend.push(response.data.info)
            }
            if (response.data.info === null) {
              this.errors.push(response.data.message);
            }
          });
    }
  },
  created() {
    this.queryLevel(this.nameAtm);

  },
  props: {
    nameAtm: {
      type: String,
      required: true
    }
  }

}
</script>

<style scoped>

</style>