export class Stampcardperiod {
  type: string;
  period: string;
  value: string;
  isActive: boolean;
  isPastOrPresentStampCardPeriod: boolean;
  managerName: string;
  employeeName: string;
  evalueeConfirmed: boolean;
  evaluatorConfirmed: boolean;
  comment: string;

  constructor(type: string, period: string, value: string, isActive: boolean,
     isPastOrPresentStampCardPeriod: boolean, managerName: string, employeeName: string,
    evalueeConfirmed: boolean, evaluatorConfirmed: boolean, comment: string) {
    this.type = type;
    this.period = period;
    this.value = value;
    this.isActive = isActive;
    this.isPastOrPresentStampCardPeriod = isPastOrPresentStampCardPeriod;
    this.managerName = managerName;
    this.employeeName = employeeName;
    this.evalueeConfirmed = evalueeConfirmed;
    this.evaluatorConfirmed = evaluatorConfirmed;
    this.comment = comment;

  }
}
