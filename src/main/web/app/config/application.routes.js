"use strict";
var router_1 = require('@angular/router');
var login_comp_1 = require('../component/login.comp');
var home_comp_1 = require('../component/home.comp');
exports.appRoutes = [
    {
        path: '',
        redirectTo: '/loginForm',
        terminal: true
    },
    { path: 'loginForm', component: login_comp_1.Login },
    { path: 'home', component: home_comp_1.Home }
];
exports.appRoutingProviders = [];
exports.routing = router_1.RouterModule.forRoot(exports.appRoutes);
//# sourceMappingURL=application.routes.js.map