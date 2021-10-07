INSERT INTO USER
    (id, name, email, password, created, modified, lastlogin, isactive)
VALUES
    (1, 'CARLOS JULIO PEREZ QUIZHPE', 'carlosjulioperez@gmail.com', '123', {ts '2021-10-07 09:00:00.00'}, {ts '2021-10-07 09:00:00.00'}, null, 1);

INSERT INTO PHONE 
    (id, number, citycode, countrycode, user_id)
VALUES
    (1, 1234567, 1, 57, 1);
