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
var http_1 = require('@angular/http');
var router_1 = require('@angular/router');
var user_1 = require('../model/user');
var token_srv_1 = require('../service/token.srv');
var communication_srv_1 = require('../service/communication.srv');
var event_srv_1 = require('../service/event.srv');
var Login = (function () {
    function Login(tokenService, communicationService, router, eventService) {
        this.tokenService = tokenService;
        this.communicationService = communicationService;
        this.router = router;
        this.eventService = eventService;
        this.user = new user_1.User();
        this.loginError = false;
        this.notValid = false;
    }
    Login.prototype.onSubmit = function () {
        var _this = this;
        var url = '/login';
        var data = 'username=' + encodeURIComponent(this.user.getUsername()) + '&password=' + encodeURIComponent(this.user.getPassword());
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        this.communicationService.httpPostWithHeaders(url, data, headers).subscribe(function (data) { _this.handleSuccess(data); }, function (error) { return _this.handleError(error); });
    };
    Login.prototype.handleSuccess = function (response) {
        this.eventService.announceMessage(this.user);
        var headers = response.headers;
        var token = headers.get('X-AUTH-TOKEN');
        this.tokenService.storeToken('authtoken', token);
        this.router.navigate(['/home']);
    };
    Login.prototype.handleError = function (error) {
        console.log(error);
        this.loginError = true;
    };
    Login = __decorate([
        core_1.Component({
            templateUrl: './app/template/login.tmpl.html'
        }), 
        __metadata('design:paramtypes', [token_srv_1.TokenService, communication_srv_1.CommunicationService, router_1.Router, event_srv_1.Eventservice])
    ], Login);
    return Login;
}());
exports.Login = Login;
//# sourceMappingURL=login.comp.js.map