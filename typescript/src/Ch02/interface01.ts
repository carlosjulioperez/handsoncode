interface Contact1 {
    id: number;
    name: string;
    birthDate?: Date; //Optional 
}

let primaryContact1: Contact1 = {
    birthDate: new Date("01-01-1980"),
    id: 12345,
    name: "Carlos Perez"
}