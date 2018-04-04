import { Injectable } from '@angular/core';

@Injectable()
export class YearService {
    private static currentYear: number = null;

    public static getCurrentYear(): number {
        if (YearService.currentYear === null) {
           YearService.currentYear = new Date().getFullYear();
        }
        return YearService.currentYear;
    }
}