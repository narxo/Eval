import { Component, OnInit, ElementRef, ViewChild, ViewChildDecorator, ViewChildren, ContentChild, Input, Injectable } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser'
import { Stampcardperiod } from "./Stampcardperiod.class";
import { BackendService } from '../backend/backend.service'
import { Employee } from '../users/employee.class'
import { YearService } from '../library/year.service';
import { MatDialog } from '@angular/material';
import { FeedbackDialogContent } from '../feedbackDialog/feedbackDialogContent.component';

@Component({
    selector: 'stampcard',
    templateUrl: './stampcard.component.html'
})

@Injectable()
export class Stampcard implements OnInit {

    @Input("stampCardUser") user: Employee;
    stampcard: Array<Stampcardperiod> = [];
    currentUser: string;
    currentUserRole: string;
    switcher: boolean;
    yearToGetFromBackend: number;

    constructor(private backendService: BackendService, private yearService: YearService, public dialog: MatDialog) { }

    ngOnInit() {
        this.currentUser = sessionStorage.getItem('currentUser');
        this.currentUserRole = sessionStorage.getItem('userRole');
        this.yearToGetFromBackend = YearService.getCurrentYear();
        this.switcher = false;
        this.refreshStampcard();
    }

    public confirmedByNone(stampcardPeriod: Stampcardperiod) {
        return stampcardPeriod.type === 'AGREEMENT' && stampcardPeriod.value === '0';
    }

    public confirmedByOne(stampcardPeriod: Stampcardperiod) {
        return stampcardPeriod.type === 'AGREEMENT' && stampcardPeriod.value === '1';
    }

    public confirmedByTwo(stampcardPeriod: Stampcardperiod) {
        return stampcardPeriod.type === 'AGREEMENT' && stampcardPeriod.value === '2';
    }

    public futureAgreement(stampcardPeriod: Stampcardperiod) {
        return stampcardPeriod.type === 'AGREEMENT' && stampcardPeriod.value === '3';
    }

    public qscoreEntry(stampcardPeriod: Stampcardperiod) {
        return stampcardPeriod.type === 'QSCORE';
    }

    public stampcardPeriodClicked(index: number, event) {
        event.stopPropagation();
        if (this.stampcard[index].type === 'AGREEMENT') {
            this.backendService.reverseConfirmation(this.currentUser, this.user.userName,
                this.stampcard[index].period).subscribe(res => { this.refreshStampcard() });
        }
    }

    public evaluationPeriodState(stampcardPeriod: Stampcardperiod): String {
        if (this.futureAgreement(stampcardPeriod)) {
            return 'futureAgreement' + this.stampCardPeriodPastOrPresent(stampcardPeriod);
        }
        if (this.confirmedByNone(stampcardPeriod)) {
            return 'agreementTodo' + this.stampCardPeriodPastOrPresent(stampcardPeriod);
        }
        if (this.confirmedByOne(stampcardPeriod)) {
            return 'agreementBusy' + this.stampCardPeriodPastOrPresent(stampcardPeriod);
        }
        if (this.confirmedByTwo(stampcardPeriod)) {
            return 'agreementDone' + this.stampCardPeriodPastOrPresent(stampcardPeriod);
        }
        if (this.qscoreEntry(stampcardPeriod)) {
            return 'qscorefill' + this.stampCardPeriodPastOrPresent(stampcardPeriod);
        }
        return 'nofill';
    }

    public stampCardPeriodPastOrPresent(stampcardPeriod: Stampcardperiod): string {
        if (stampcardPeriod.isPastOrPresentStampCardPeriod && stampcardPeriod.type === 'AGREEMENT') {
            return ' hasinfo';
        }
        return '';
    }

    public isManager(): boolean {
        if (this.currentUser === this.user.managerUserName) { return true; }
        return false;
    }

    public isActive(stampcardPeriod: Stampcardperiod): boolean {
        return stampcardPeriod.isActive;
    }

    public refreshStampcard() {
        this.backendService.getStampcardByUserNameAndYear(this.user.userName, this.yearToGetFromBackend)
            .subscribe(backendStampcard => {
                this.stampcard = backendStampcard;
            });
    }

    public refreshWithUpdatedYear(year:number){
        this.yearToGetFromBackend = year;
        this.refreshStampcard();
    }

    evaluationText(checkEvaluation: boolean): string {
        if (checkEvaluation) {
            return 'confirmed';
        } else {
            return 'not confirmed';
        }
    }

    openDialog(
        index: number,
        managerName: string,
        employeeName: string,
        comment: string,
        score: string
    ) {

        const dialogRef = this.dialog.open(FeedbackDialogContent, {
            disableClose: false,
            hasBackdrop: true,
            backdropClass: '',
            width: '60vw',
            height: '80vh',
            data: {
                score: score,
                comment: comment,
                userName: this.currentUser,
                period: this.stampcard[index].period,
                evaluator: managerName,
                evaluee: this.user.userName,
                isManager: this.isManager()
            }
        });
        dialogRef.afterClosed().subscribe(result => {
            this.refreshStampcard();
        });
    }
}


