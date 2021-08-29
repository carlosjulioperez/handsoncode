INSERT INTO 
    CLIENTE (NOMBRE, APELLIDO)
VALUES
    ('Carlos Julio', 'Pérez Quizhpe'),
    ('Ana', 'Alarcón'),
    ('Juan', 'Delgado');

INSERT INTO 
    ARTICULO (NOMBRE, PRECIO)
VALUES
    ('Monitor', 60.5),
    ('Teclado USB', 12.25),
    ('Mouse',5.56);

INSERT INTO 
    ORDEN (CLIENTE_ID, ARTICULOS)
VALUES
    (1, '1,2,3'),
    (2, '1,2'),
    (3, '2,3');