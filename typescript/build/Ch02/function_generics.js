function clone2(source) {
    return Object.apply({}, source);
}
const f2a = { id: 123, name: "Carlos Perez" };
const f2b = clone2(f2a);
const dateRange = { startDate: Date.now(), endDate: Date.now() };
const dateRangeCopy = clone2(dateRange);
