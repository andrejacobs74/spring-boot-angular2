import { NgModule }                             from '@angular/core';
import { BrowserModule }                        from '@angular/platform-browser';
import { FormsModule }                          from '@angular/forms';

import { RequestOptions,
    Connection,
    XHRBackend,
    Http,
    XSRFStrategy,
    CookieXSRFStrategy,
    HttpModule}                                 from '@angular/http';

import { LocationStrategy,
    PathLocationStrategy,
    HashLocationStrategy }                      from '@angular/common';

import { Router }                               from '@angular/router';

import { appRoutingProviders, routing }         from '../config/application.routes';
import { Application }                          from '../component/application.comp';
import { Login }                                from '../component/login.comp';
import { Home }                                 from '../component/home.comp';

import { HttpInterceptor }                      from '../service/httpinterceptor.srv';
import { CustomRequestOptions }                 from '../service/customrequestsoption.srv';
import { CommunicationService }                 from '../service/communication.srv';
import { Eventservice }                         from '../service/event.srv';
import { TokenService }                         from '../service/token.srv';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing
    ],
    providers: [
        TokenService,
        CommunicationService,
        Eventservice,
        appRoutingProviders,
        {provide: LocationStrategy, useClass: PathLocationStrategy},
        {provide: RequestOptions,  useClass: CustomRequestOptions },
        {provide: XSRFStrategy, useValue: new CookieXSRFStrategy('XSRF-TOKEN', 'X-XSRF-TOKEN')},
        {
            provide: Http, useFactory: (xhrBackend: XHRBackend, requestOptions: RequestOptions, router: Router) => new HttpInterceptor(xhrBackend, requestOptions, router),
            deps: [XHRBackend, RequestOptions, Router]
        }
    ],
    declarations: [
        Application,
        Login,
        Home
    ],
    bootstrap: [
        Application
    ]
})
export class AppModule { }