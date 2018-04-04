import { Component, OnInit, Input } from '@angular/core';
import { ExpansionPanelsModule } from 'ng2-expansion-panels';
import { BackendService } from '../../backend/backend.service'
import { Employee } from '../../users/employee.class';
import { Router } from "@angular/router";

@Component({
    selector: 'asfView',
    templateUrl: `./asfView.component.html`
})

export class AsfView implements OnInit {
    _employeeLoggedInUserName;
    _teamMembersList: Array<Employee> = [];
    _role: string;
    constructor(private backendService: BackendService, private router: Router) {
    }

    ngOnInit() {
        this._role = sessionStorage.getItem('userRole');
        this._employeeLoggedInUserName = sessionStorage.getItem('currentUser');
        this.backendService.getEmployeeListForSuperUserView()
            .subscribe(teamMembers => {
                this._teamMembersList = teamMembers;
            })
            ;
    }
    redirectBack() {
        this.router.navigateByUrl('/standardView');
    }

    getCsv(){
      this.backendService.getCsv().subscribe(blob => {
        let fileName = 'overview.csv';
        const objectUrl: string = URL.createObjectURL(blob);
        const a: HTMLAnchorElement = document.createElement('a') as HTMLAnchorElement;
        a.href = objectUrl;
        a.download = fileName;
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        URL.revokeObjectURL(objectUrl);
      })
    }
}
