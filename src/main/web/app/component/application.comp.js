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
var router_1 = require('@angular/router');
var token_srv_1 = require('../service/token.srv');
var communication_srv_1 = require('../service/communication.srv');
var event_srv_1 = require('../service/event.srv');
var user_1 = require("../model/user");
var Application = (function () {
    function Application(communicationService, tokenService, router, eventService) {
        this.communicationService = communicationService;
        this.tokenService = tokenService;
        this.router = router;
        this.eventService = eventService;
        this.user = new user_1.User();
        this.username = 'peter';
        this.isAuthenticated = false;
        this.registerListener();
    }
    Application.prototype.getName = function () {
        return this.user.getUsername();
    };
    Application.prototype.registerListener = function () {
        var _this = this;
        this.eventService.message$.subscribe(function (data) {
            _this.user = (data);
            if (_this.user.getUsername() != null) {
                _this.isAuthenticated = true;
            }
        });
    };
    Application.prototype.logout = function () {
        var _this = this;
        var url = '/logout';
        this.communicationService.httpPost(url, null).subscribe(function (data) { _this.handleSuccess(data); }, function (error) { return _this.handleError(error); });
    };
    Application.prototype.handleSuccess = function (data) {
        this.user = new user_1.User();
        this.tokenService.removeToken('authtoken');
        this.router.navigate(['/loginForm']);
    };
    Application.prototype.handleError = function (data) {
        console.log("error while logout");
    };
    Application = __decorate([
        core_1.Component({
            selector: 'application',
            templateUrl: './app/template/application.tmpl.html'
        }), 
        __metadata('design:paramtypes', [communication_srv_1.CommunicationService, token_srv_1.TokenService, router_1.Router, event_srv_1.Eventservice])
    ], Application);
    return Application;
}());
exports.Application = Application;
//# sourceMappingURL=application.comp.js.map