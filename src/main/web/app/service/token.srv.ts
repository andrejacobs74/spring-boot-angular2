import { Injectable }           from '@angular/core';

@Injectable()
export class TokenService {

    constructor() {}

    storeToken(key : string, token: string): void {
        localStorage.setItem(key, token);
    }

    removeToken(key: string): void {
        localStorage.removeItem(key);
    }

    retrieveToken(key: string): string {
        return localStorage.getItem(key);
    }
}