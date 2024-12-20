export default {
    host: '',
    port: '',
    packing: '/monitor/',
    // host: 'http://localhost',
    // port: ':8080',
    // packing: '/monitor/',


    //***Ruoter servidor

    //property
    routerStationActive: 'api/property/read',

    //atm
    routerSearchATM: 'api/atm/searchstation',
    routerPopup: 'api/atm/search/active',
    routerTest: 'api/atm/property/testconnection',
    routerSaveConnection: 'api/atm/property/save',

    //monitor
    routerLevel: 'api/monitor/property/level',

    //report
    routerTransaction: 'api/reports/transactions',
    routerSummary: 'api/reports/fill&empty',
    routerFillEmpty: 'api/reports/refunds&colletions',
    routerRefundsDays: 'api/reports/refunds&days',

    //alert
    routerSaveAlert: 'api/alert/save',

    //user
    routerSaveUser: 'api/user/createuser',
    routerSearchUser: 'api/user/searchuser',
    routerDeleteUser: 'api/user/deleteuser',
    routerSearchProfileUser: 'api/user/profileuser',


    //*******Genericos
    separationRouter: '?',
    equalOperator: '=',
    and: '&',

    //******ParamRouter
    paramName: 'name',
    paramInitialDate: 'initialDate',
    paramFinalDate: 'finalDate',


}

