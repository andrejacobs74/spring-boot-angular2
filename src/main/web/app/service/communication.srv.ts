///<reference path="../../typings/modules/es6-promise/index.d.ts"/>

import { Injectable }                   from '@angular/core';
import { Http, Response, Headers }      from '@angular/http';
import { Observable }                   from 'rxjs/Observable';

import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


@Injectable()
export class CommunicationService {

    constructor(private http: Http) {
    }

    httpGet(url: string): Observable<Response> {
        return this.http.get(url)
            .map(this.extractData)
            .catch(this.handleError);
    }

    httpPost(url: string, data: Object): Observable<Response>{
        return this.http.post(url, data)
            .map(this.extractData)
            .catch(this.handleError);
    }

    httpPostWithHeaders(url: string, data: Object, headers: Headers): Observable<Response>{
        return this.http.post(url, data, {
                headers: headers
            });
    }

    private extractData(res: Response): Response {
        return res;
    }

    private handleError (error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.log(errMsg);
        return Observable.throw(errMsg);
    }
}