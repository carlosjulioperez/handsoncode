-- ALTER TABLE ingenio.ingenio.cto24h ALTER COLUMN descripcion TYPE varchar(10);

-- Actualizar en servidor
-- ALTER TABLE ingenio.ingenio.blcpdetalle1 ADD modificable bool NULL;
-- UPDATE blcpdetalle1 SET modificable=false;
ALTER TABLE ingenio.ingenio.blcdetalle1 ADD modificable bool NULL;
UPDATE blcdetalle1 SET modificable=false;
