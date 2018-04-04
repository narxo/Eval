import { Component } from '@angular/core';
import { Output } from '@angular/core/src/metadata/directives';
import { EventEmitter } from '@angular/core/src/event_emitter';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html'
})

export class AppComponent {
    title = 'app works!';
    _employeeLoggedInUserName = sessionStorage.getItem('currentUser');
    _loggedInName = sessionStorage.getItem('name');

    public whenLoggedIn() {
        return sessionStorage.getItem('userRole') !== null;
        // && sessionStorage.getItem('userRole') !== 'ASFLead';
    }
}