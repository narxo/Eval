import { Injectable } from '@angular/core';
import {Http, Response, Headers, ResponseContentType} from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'
import { Stampcardperiod } from '../stampcard/Stampcardperiod.class';
import { Employee } from '../users/employee.class';

@Injectable()
export class BackendService {

    constructor(private http: Http) { }

    getUserName(userName: string): Observable<string> {
        let header = new Headers({ 'Accept': 'application/json' });
        let response = this.http.get(`${environment.baseUrl}/employee/` + userName, { headers: header, withCredentials: true })
        return response.map(res => res.json().userName);
    }

    getTeamMembersByTeamManagerUserName(userName: string): Observable<Employee[]> {
        let header = new Headers({ 'Accept': 'application/json' });
        return this.http.get(`${environment.baseUrl}/employee/teamMembers/` + userName, { headers: header, withCredentials: true })
            .map((res: Response) => res.json());
    }

    getEmployeeListForSuperUserView() {
        let header = new Headers({ 'Accept': 'application/json' });
        return this.http.get(`${environment.baseUrl}/employee/employeesForAsfLead/`, { headers: header, withCredentials: true })
            .map((res: Response) => res.json());
    }

    getStampcardByUserNameAndYear(userName: string, year: number): Observable<Stampcardperiod[]> {
        let header = new Headers({ 'Accept': 'application/json' });
        return this.http.get(`${environment.baseUrl}/stampcard/` + userName + "/" + year, { headers: header, withCredentials: true })
            .map((res: Response) => res.json());
    }

    reverseConfirmation(currentUser: string, userName: string, period: string) {
        return this.http.put(`${environment.baseUrl}/agreement/reverseConfirmation`,
            { evaluator: currentUser, evaluee: userName, period: period }, { withCredentials: true });
    }

    updateQScore(currentUser: string, userName: string, quadrimester: string, qScore: number, comment: string) {
        return this.http.put(`${environment.baseUrl}/qscore/updatescore`, {
            evaluator: currentUser, evaluee: userName,
            quadrimester: quadrimester, qScore: qScore, comment: comment
        }, { withCredentials: true });
    }

    getEmployeeByUsername(userName: string): Observable<Employee> {
        let header = new Headers({ "Accept": 'application/json' });
        let response = this.http.get(`${environment.baseUrl}/employee/` + userName, { headers: header, withCredentials: true });
        return response.map((response: Response) => response.json());
    }

    getCsv() : Observable<Blob> {
      let header = new Headers({ "Accept": 'application/octet-stream"' });
      let response = this.http.get(`${environment.baseUrl}/employee/getCsv` , { headers: header, withCredentials: true, responseType : ResponseContentType.Blob });
      return response.map((response  => {
          return new Blob( [response.blob()], { type: "application/octet-stream"});
        }));
    }
}
