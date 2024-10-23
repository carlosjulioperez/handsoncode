interface Contact {
    id: number;
    name: string;
}

function clone2<T>(source: T): T{
    return Object.apply({}, source);
}

const f2a: Contact = {id: 123, name: "Carlos Perez"};
const f2b = clone2(f2a);

const dateRange = { startDate: Date.now(), endDate: Date.now() }
const dateRangeCopy = clone2 (dateRange);