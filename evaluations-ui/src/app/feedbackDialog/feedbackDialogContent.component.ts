import { Component, OnInit, ElementRef, ViewChild, ViewChildDecorator, ViewChildren, ContentChild, Input, Inject } from '@angular/core';
import { MatDialog } from '@angular/material';
import { MatDialogRef } from '@angular/material';
import { MAT_DIALOG_DATA } from '@angular/material';
import { BackendService } from '../backend/backend.service'
import { Employee } from '../users/employee.class';
import { Stampcardperiod } from '../stampcard/Stampcardperiod.class';
import { Stampcard } from '../stampcard/stampcard.component';


@Component({
    selector: 'feedbackDialogContent',
    templateUrl: 'feedbackDialogContent.component.html',
})

export class FeedbackDialogContent {
    @Input("stampCardUser") user: Employee;
    currentUser: string;
    comment = this.data.comment;
    
    constructor(
        private backendService: BackendService,
        public dialogRef: MatDialogRef<FeedbackDialogContent>,
        @Inject(MAT_DIALOG_DATA) public data: any) { }

    ngOnInit() {
        this.currentUser = sessionStorage.getItem('currentUser');
    }

    onCancel(): void {
        this.dialogRef.close();
    }

    onSubmit(userName: string, period: string, evaluator: string, evaluee: string, comment: string, qScore: number) {
        this.backendService.updateQScore(this.currentUser, evaluee, period, qScore, comment)
        .subscribe();
    }

}
