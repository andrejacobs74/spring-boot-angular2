import { Component }                            from '@angular/core'
import { ROUTER_DIRECTIVES }                    from '@angular/router'
import { Response }                             from '@angular/http';
import { Router }                               from '@angular/router'
import { FORM_DIRECTIVES }                      from '@angular/forms';

import { TokenService }                         from '../service/token.srv';
import { CommunicationService}                  from '../service/communication.srv';
import { Eventservice }                         from '../service/event.srv';
import { User }                                 from "../model/user";

@Component({
    selector: 'application',
    directives: [ROUTER_DIRECTIVES, FORM_DIRECTIVES],
    templateUrl: './app/template/application.tmpl.html'

})
export class Application {

    private user : User = new User();
    private username : string = 'peter';
    private isAuthenticated = false;

    constructor(private communicationService: CommunicationService,
                private tokenService: TokenService,
                private router: Router,
                private eventService: Eventservice){
        this.registerListener();
    }

    private getName(): string {
        return this.user.getUsername();
    }

    private registerListener() {
        this.eventService.message$.subscribe(data => {
            this.user = <User>(data);
            if (this.user.getUsername() != null) {
                this.isAuthenticated = true;
            }
        });
    }
    logout() {
        let url = '/logout';
        this.communicationService.httpPost(url, null).subscribe(
            data => {this.handleSuccess(data)},
            error => this.handleError(error)
        );
    }

    private handleSuccess(data: Response) {
        this.user = new User();
        this.tokenService.removeToken('authtoken');
        this.router.navigate(['/loginForm']);
    }

    private handleError(data: Response) {
        console.log("error while logout");
    }
}