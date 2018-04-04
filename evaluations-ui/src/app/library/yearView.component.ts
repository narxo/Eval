import { Component, Output, EventEmitter } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { YearService } from './year.service';

@Component({
    selector: 'yearViewComponent',
    templateUrl: './yearView.component.html'
})

export class YearViewComponent {
    activeYear: number;
    @Output() nextYear: EventEmitter<number> = new EventEmitter();

    constructor(private yearService: YearService){
        this.activeYear = YearService.getCurrentYear();
    }

    public whenLoggedIn() {
        return sessionStorage.getItem('userRole') !== null;
    }

    public selectYear(yearIndex: number, event) {
        this.activeYear = this.activeYear + yearIndex;
        this.nextYear.emit(this.activeYear);
    }

    public isCurrentYear() {
        return this.activeYear === YearService.getCurrentYear();
    }
}