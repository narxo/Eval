import { Component, OnInit, QueryList, ViewChildren } from "@angular/core";
import { BackendService } from "./backend/backend.service";
import { Employee } from "./users/employee.class";
import { Stampcard } from "./stampcard/stampcard.component";

@Component({
    selector: 'combinedView',
    templateUrl: './combinedView.component.html',
})

export class CombinedView implements OnInit {
    _employeeLoggedInUser: Employee;
    _loggedInName: string;
    _role: string;
    @ViewChildren (Stampcard) allStampcardRefresh: QueryList<Stampcard>;

    private _currentUser: string;

    constructor(private backendService: BackendService) { };

    ngOnInit() {
        this._loggedInName = sessionStorage.getItem('name');
        this._role = sessionStorage.getItem('userRole');
        this._currentUser = sessionStorage.getItem('currentUser');

        this.backendService.getEmployeeByUsername(this._currentUser)
            .subscribe(user =>
                this._employeeLoggedInUser = user)
    }

    public whenLoggedIn() {
        return this._role !== null && this._role !== 'ASFLead';
    }

    public getNextYear(nextYear: number) {
        this.allStampcardRefresh.forEach(stampcard => stampcard.refreshWithUpdatedYear(nextYear));
    }
}