<template>

  <vs-tabs alignment="fixed">
    <vs-tab label="Transacciones">
      <div>
        <TableTransactions :atm-name="atmName" :date-final="dateFinal" :date-initial="dateInitial"> </TableTransactions>
      </div>
    </vs-tab>
    <vs-tab label="Balance diario">
      <div>
        <TableSummaryDay :atm-name="atmName" :date-final="dateFinal" :date-initial="dateInitial" ></TableSummaryDay>
      </div>
    </vs-tab>
    <vs-tab label="Retornos">
      <div>
        <TableRefunds :atm-name="atmName" :date-final="dateFinal" :date-initial="dateInitial"></TableRefunds>
      </div>
    </vs-tab>
  </vs-tabs>
</template>

<script>
import TableTransactions from "../../../generic-components/tables/TableTransactions";
import TableSummaryDay from "@/components/generic-components/tables/TableSummaryDay";
import TableRefunds from "@/components/generic-components/tables/TableRefunds";

export default {
  name: "tabsReport",
  props:{
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
  components:{
    TableTransactions,
    TableSummaryDay,
    TableRefunds
  },
}
</script>

<style scoped>

</style>