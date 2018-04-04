import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../login/login.service';

@Component({
    selector: 'logout',
    templateUrl: `./logout.component.html`
})

export class LogoutComponent {
    constructor(private loginService: LoginService, private router: Router) { }

    logoutInHere() {
        this.loginService.logout();
        this.router.navigate(['/login']);
    }
    public whenLoggedIn() {
        return sessionStorage.getItem('userRole') !== null;
        // && sessionStorage.getItem('userRole') !== 'ASFLead';
    }
}