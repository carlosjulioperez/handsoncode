interface Contact2 extends Address2{
    id: number
    name: string
    birthDate?: Date //Optional 
}

interface Address2 {
    line1: string
    line2: string
    province: string
    region: string
    postalCode: string
}

let primaryContact2: Contact2 = {
    birthDate: new Date("01-01-1980"),
    id: 12345,
    name: "Carlos Perez",
    line1: "Line1",
    line2: "Line2",
    province: "Manabi",
    region: "Coast",
    postalCode: "sss"
}