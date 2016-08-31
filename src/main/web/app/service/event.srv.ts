///<reference path="../../typings/modules/es6-promise/index.d.ts"/>

import { Injectable }                           from '@angular/core';
import { Observable }                           from 'rxjs/Observable';
import { Subject }                              from 'rxjs/Subject';

@Injectable()
export class Eventservice{

    private messageSource = new Subject<Object>();

    public message$ = this.messageSource.asObservable();

    constructor() {}

    announceMessage(message: Object) {
        this.messageSource.next(message);
    }

}