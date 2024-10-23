var ContactStatus;
(function (ContactStatus) {
    ContactStatus[ContactStatus["Active"] = 0] = "Active";
    ContactStatus[ContactStatus["Inactive"] = 1] = "Inactive";
    ContactStatus[ContactStatus["New"] = 2] = "New";
})(ContactStatus || (ContactStatus = {}));
let primaryContact3 = {
    birthDate: new Date("01-01-1980"),
    id: 12345,
    name: "Carlos Perez",
    status: ContactStatus.Active
};
