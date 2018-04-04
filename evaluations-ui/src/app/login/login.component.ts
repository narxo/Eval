import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { AlertService } from '../alert/alert.service';
import { LoginService } from './login.service';
import { Http, Response } from '@angular/http';
import { error } from 'util';
import { BackendService } from '../backend/backend.service'

@Component({
    selector: 'loginComponent',
    templateUrl: 'login.component.html'
})

export class LoginComponent implements OnInit {
    model: any = {};
    loading = false;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private loginService: LoginService,
        private alertService: AlertService,
        private backendService: BackendService) { }

    ngOnInit() {
        this.loginService.logout();
    }

    login() {
        this.loading = true;
        this.loginService.login(this.model.username, this.model.password).subscribe(
            response => {
                this.backendService.getEmployeeByUsername(this.model.username).subscribe(
                    user => {
                        this.setFirstName(user.firstName);
                        this.setLastName(user.lastName);
                        this.setName(user.firstName + " " + user.lastName);
                        this.setRole(user.role);
                        this.setManagerUserName(user.managerUserName);
                    },
                    error => {
                        this.alertService.error('This user currently does not have access to the evaluations tool.');
                        this.loginService.logout();
                        this.loading = false;
                    });
            },
            error => {
                if (error.status != 0) {
                    this.alertService.error(JSON.parse(error._body).message);
                }
                else {
                    this.alertService.error("Server could not be reached.");
                }
                this.loading = false;
            });
    }

    setRole(role) {
        sessionStorage.setItem('userRole', role);
        this.router.navigate(['/combinedView']);
        this.loading = false;
    }

    setFirstName(firstName) {
        sessionStorage.setItem('firstName', firstName);
    }

    setLastName(lastName) {
        sessionStorage.setItem('lastName', lastName);
    }

    setName(name) {
        sessionStorage.setItem('name', name);
    }

    setManagerUserName(managerUserName){
        sessionStorage.setItem('managerUserName', managerUserName);
    }

}
