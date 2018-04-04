import { Component, OnInit, Input } from '@angular/core';
import { ExpansionPanelsModule } from 'ng2-expansion-panels';
import { BackendService } from '../../backend/backend.service'
import { Employee } from '../../users/employee.class';

@Component({
    selector: 'teamMemberView',
    templateUrl: `./teamMemberView.component.html`
})

export class TeamMemberView implements OnInit {
    @Input() teamMember: Employee;
    _employeeLoggedInUserName;
    _teamMembersList: Array<Employee> = [];
    _employeeList: Array<Employee> = [];

    constructor(private backendService: BackendService) {
    }

    ngOnInit() {
        this._employeeLoggedInUserName = sessionStorage.getItem('currentUser');
        this.backendService.getTeamMembersByTeamManagerUserName(this._employeeLoggedInUserName)
            .subscribe(teamMembers => {
                this._teamMembersList = teamMembers;
            });
    }
    viewLoader(managerInTeam, event) {
        event.stopPropagation();
        this.backendService.getTeamMembersByTeamManagerUserName(managerInTeam.userName)
            .subscribe(teamMembers => {
                this._employeeList = teamMembers;
            });
    }
   
}