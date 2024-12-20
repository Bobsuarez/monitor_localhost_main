export default [{
    url: "/view/atm",
    name: "Monitor",
    icon: "mdi mdi-view-dashboard",
    i18n: "Dashboards",
    index: 1,
    child: [{
        url: '/view/diagnosis/atm',
        name: "diagnosisatm",
        icon: "mdi mdi mdi-adjust",
        i18n: "Diagnóstico Cajero",
        index: 1.2,
    }, {
        url: '/view/setting/atm',
        name: "settingAtm",
        icon: "mdi mdi mdi-adjust",
        i18n: "Configuracion Cajero",
        index: 1.3,
    }
    ]
}
]