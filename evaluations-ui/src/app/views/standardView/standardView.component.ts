import { Component, OnInit, Input } from '@angular/core';
import { ExpansionPanelsModule } from 'ng2-expansion-panels';
import { BackendService } from '../../backend/backend.service'
import { Employee } from '../../users/employee.class';
import { Router } from "@angular/router";

@Component({
    selector: 'standardView',
    templateUrl: `./standardView.component.html`
})

export class StandardView implements OnInit {
    _employeeLoggedInUserName;
    _teamMembersList: Array<Employee> = [];
    _role: string;
    constructor(private backendService: BackendService, private router: Router) {
    }

    ngOnInit() {
        this._role = sessionStorage.getItem('userRole');
        this._employeeLoggedInUserName = sessionStorage.getItem('currentUser');
        this.backendService.getTeamMembersByTeamManagerUserName(this._employeeLoggedInUserName)
            .subscribe(teamMembers => {
                this._teamMembersList = teamMembers;
            })
            ;
    }
    redirect() {
        this.router.navigateByUrl('/asfView');
    }
}