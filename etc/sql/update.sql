DELETE FROM ingenio.blcpdetalle1;
DELETE FROM ingenio.blcp;
DELETE FROM ingenio.blcdetalle1;
DELETE FROM ingenio.blc;
DELETE FROM ingenio.material;

UPDATE ingenio.ingenio.indicador SET campo = 'Bx', descripcion = 'Bx' WHERE "id" = '13';
