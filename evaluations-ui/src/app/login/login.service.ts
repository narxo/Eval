import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions, RequestMethod, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import 'rxjs/add/operator/map';
import { Employee } from '../users/employee.class';


@Injectable()
export class LoginService {
    private loggedIn: boolean = false;

    constructor(private http: Http) { }

    login(username: string, password: string): Observable<Response> {
        let headers = new Headers();
        headers.append("Content-Type", 'application/json');
        headers.append("Accept", 'application/json')
        let requestoptions = new RequestOptions({
            method: RequestMethod.Post,
            url: environment.baseUrl + '/login?username=' + (username) + '&password=' + encodeURIComponent(password),
            headers: headers,
            withCredentials: true,
        });
        return this.http.request(new Request(requestoptions))
            .map((res: Response) => {
                sessionStorage.setItem('currentUser', username.toLowerCase());
                this.loggedIn = true;
                return res;
            },
            error => {
                return error;
            });
    }

    isLoggedIn(): boolean {
        return this.loggedIn;
    }

    logout() {
        let headers = new Headers();
        headers.append("Content-Type", 'application/json');
        let requestoptions = new RequestOptions({
            method: RequestMethod.Post,
            url: environment.baseUrl + '/logout',
            headers: headers,
            withCredentials: true
        });
        this.http.request(new Request(requestoptions)).subscribe();
        sessionStorage.clear();
        this.loggedIn = false;
    }

    getEmployeeBy(userName: string): Observable<Employee> {
        let header = new Headers({ "Accept": 'application/json' });
        let response = this.http.get(`${environment.baseUrl}/employee/` + userName, { headers: header, withCredentials: true });
        return response.map((response :Response) => response.json());
    }
}
