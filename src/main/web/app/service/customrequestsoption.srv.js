"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var http_1 = require('@angular/http');
var token_srv_1 = require('./token.srv');
var CustomRequestOptions = (function (_super) {
    __extends(CustomRequestOptions, _super);
    function CustomRequestOptions() {
        _super.call(this);
        this.tokenStorage = new token_srv_1.TokenService();
        this.headers.append('X-XSRF-TOKEN', this.getCookie('XSRF-TOKEN'));
    }
    CustomRequestOptions.prototype.merge = function (options) {
        options.headers = new http_1.Headers();
        var token = this.tokenStorage.retrieveToken('authtoken');
        if (token != null) {
            options.headers.append('X-AUTH-TOKEN', token);
        }
        return _super.prototype.merge.call(this, options);
    };
    CustomRequestOptions.prototype.getCookie = function (name) {
        var value = "; " + document.cookie;
        var parts = value.split("; " + name + "=");
        if (parts.length == 2)
            return parts.pop().split(";").shift();
    };
    return CustomRequestOptions;
}(http_1.BaseRequestOptions));
exports.CustomRequestOptions = CustomRequestOptions;
//# sourceMappingURL=customrequestsoption.srv.js.map