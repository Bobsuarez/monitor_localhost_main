export default {
    donutChart: {
        chartOptions: {
            labels: [""],
            dataLabels: {
                enabled: false,
            },
            grid: {
                padding: {
                    left: 0,
                    right: 0
                },
                borderColor: 'rgba(0,0,0,0.1)'
            },
            plotOptions: {
                pie: {
                    donut: {
                        size: '70px'
                    }
                }
            },
            stroke: {
                width: 0
            },
            legend: {
                show: false,
            },
            colors: ['rgb(64, 196, 125)'],
            tooltip: {
                fillSeriesColor: false,
            },
        }
    },
    donutChartCode: `<template>
  <vue-apex-charts type="donut" height="550" :options="donutChart.chartOptions" :series="donutChart.series"></vue-apex-charts>
</template>`
}