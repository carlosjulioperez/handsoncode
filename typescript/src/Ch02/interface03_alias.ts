interface Contact3 {
    id: number;
    name: ContactName;
    birthDate?: Date; //Optional 
    status: ContactStatus
}

enum ContactStatus{
    Active,
    Inactive,
    New
}

let primaryContact3: Contact3 = {
    birthDate: new Date("01-01-1980"),
    id: 12345,
    name: "Carlos Perez",
    status: ContactStatus.Active
}

type ContactName = string