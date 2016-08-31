"use strict";
var User = (function () {
    function User() {
        this.username = '';
        this.password = '';
    }
    User.prototype.getUsername = function () {
        return this.username;
    };
    User.prototype.getPassword = function () {
        return this.password;
    };
    return User;
}());
exports.User = User;
//# sourceMappingURL=user.js.map