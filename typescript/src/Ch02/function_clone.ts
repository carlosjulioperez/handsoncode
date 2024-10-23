interface Contact {
    id: number;
    name: string;
}

function clone(source: Contact): Contact{
    return Object.apply({}, source);
}

const f1a: Contact = {id: 123, name: "Carlos Perez"};
const f1b = clone(f1a);