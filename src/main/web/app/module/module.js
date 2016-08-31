"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var platform_browser_1 = require('@angular/platform-browser');
var forms_1 = require('@angular/forms');
var http_1 = require('@angular/http');
var common_1 = require('@angular/common');
var router_1 = require('@angular/router');
var application_routes_1 = require('../config/application.routes');
var application_comp_1 = require('../component/application.comp');
var login_comp_1 = require('../component/login.comp');
var home_comp_1 = require('../component/home.comp');
var httpinterceptor_srv_1 = require('../service/httpinterceptor.srv');
var customrequestsoption_srv_1 = require('../service/customrequestsoption.srv');
var communication_srv_1 = require('../service/communication.srv');
var event_srv_1 = require('../service/event.srv');
var token_srv_1 = require('../service/token.srv');
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [
                platform_browser_1.BrowserModule,
                forms_1.FormsModule,
                http_1.HttpModule,
                application_routes_1.routing
            ],
            providers: [
                token_srv_1.TokenService,
                communication_srv_1.CommunicationService,
                event_srv_1.Eventservice,
                application_routes_1.appRoutingProviders,
                { provide: common_1.LocationStrategy, useClass: common_1.PathLocationStrategy },
                { provide: http_1.RequestOptions, useClass: customrequestsoption_srv_1.CustomRequestOptions },
                { provide: http_1.XSRFStrategy, useValue: new http_1.CookieXSRFStrategy('XSRF-TOKEN', 'X-XSRF-TOKEN') },
                {
                    provide: http_1.Http, useFactory: function (xhrBackend, requestOptions, router) { return new httpinterceptor_srv_1.HttpInterceptor(xhrBackend, requestOptions, router); },
                    deps: [http_1.XHRBackend, http_1.RequestOptions, router_1.Router]
                }
            ],
            declarations: [
                application_comp_1.Application,
                login_comp_1.Login,
                home_comp_1.Home
            ],
            bootstrap: [
                application_comp_1.Application
            ]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=module.js.map