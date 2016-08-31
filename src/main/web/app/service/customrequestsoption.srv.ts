import { BaseRequestOptions,
    RequestOptions,
    RequestOptionsArgs,
    Headers }                               from '@angular/http';
import { TokenService }                     from './token.srv'

export class CustomRequestOptions extends BaseRequestOptions {

    tokenStorage : TokenService;
    constructor () {
        super();
        this.tokenStorage = new TokenService();

        this.headers.append('X-XSRF-TOKEN', this.getCookie('XSRF-TOKEN'));
    }

    merge(options?:RequestOptionsArgs):RequestOptions {
        options.headers = new Headers();
        let token = this.tokenStorage.retrieveToken('authtoken');
        if (token != null) {
            options.headers.append('X-AUTH-TOKEN', token);
        }

        return super.merge(options);
    }

    private getCookie(name) {
        let value = "; " + document.cookie;
        let parts = value.split("; " + name + "=");
        if (parts.length == 2)
            return parts.pop().split(";").shift();
    }
}