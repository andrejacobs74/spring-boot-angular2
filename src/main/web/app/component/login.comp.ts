import { Component }                            from '@angular/core';
import { Headers, Response }                    from '@angular/http';
import { Router }                               from '@angular/router'
import { User }                                 from '../model/user'
import { TokenService }                         from '../service/token.srv';
import { CommunicationService }                 from '../service/communication.srv';
import { Eventservice }                         from '../service/event.srv';

@Component({
    templateUrl: './app/template/login.tmpl.html'
})

export class Login {

    private user : User = new User();
    private loginError: boolean = false;
    private notValid: boolean = false;

    constructor(private tokenService: TokenService,
                private communicationService: CommunicationService,
                private router: Router,
                private eventService: Eventservice) {}

    onSubmit() {
        let url = '/login';
        let data = 'username=' + encodeURIComponent(this.user.getUsername()) + '&password=' + encodeURIComponent(this.user.getPassword());
        let headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        this.communicationService.httpPostWithHeaders(url, data, headers).subscribe(
            data => {this.handleSuccess(data)},
            error => this.handleError(error)
        );
    }

    private handleSuccess(response: Response) {
        this.eventService.announceMessage(this.user);

        let headers = response.headers;
        let token = headers.get('X-AUTH-TOKEN');

        this.tokenService.storeToken('authtoken',token);
        this.router.navigate(['/home']);
    }

    private handleError(error: Error) {
        console.log(error);
        this.loginError = true;
    }
}