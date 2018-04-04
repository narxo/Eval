export class Employee {
    firstName: string;
    lastName: string;
    userName: string;
    role: string;
    managerUserName: string;

    constructor(firstName: string, lastName: string, userName: string, role: string, managerUserName: string) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.role = role;  
        this.managerUserName = managerUserName;      
    }
}