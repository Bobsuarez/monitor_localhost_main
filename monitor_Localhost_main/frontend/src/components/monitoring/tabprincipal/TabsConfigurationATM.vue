<template>
  <vs-card >
    <vs-tabs alignment="fixed" >
      <vs-tab label="Conectar Cajero" >
        <div>
          <ConnectATM></ConnectATM>
        </div>
      </vs-tab>
      <vs-tab label="Usuarios">
        <div>
          <user></user>
        </div>
      </vs-tab>
      <vs-tab label="Otros">
      </vs-tab>
    </vs-tabs>
  </vs-card>
</template>

<script>

import User from "@/components/configuration/user/User";
import ConnectATM from "@/components/configuration/connectatm/ConnectATM";

export default {
  name: "TabsConfigurationATM",
  data: () => ({
  }),
  components: {
    User,
    ConnectATM
  }
}

</script>
<style >
.vs-tabs--li{
  font-size: 21px;
}


</style>