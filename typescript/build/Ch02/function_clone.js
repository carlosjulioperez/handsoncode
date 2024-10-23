function clone(source) {
    return Object.apply({}, source);
}
const f1a = { id: 123, name: "Carlos Perez" };
const f1b = clone(f1a);
