-- ALTER TABLE ingenio.ingenio.cto24h ALTER COLUMN descripcion TYPE varchar(10);
-- ALTER TABLE ingenio.ingenio.blccelicanapdetalle DROP COLUMN porcc;

-- ALTER TABLE ingenio.ingenio.cto24h DROP COLUMN ffelining;
-- ALTER TABLE ingenio.ingenio.cto24h DROP COLUMN fr;
-- ALTER TABLE ingenio.ingenio.cto24h DROP COLUMN modulo1_id;
-- ALTER TABLE ingenio.ingenio.cto24h DROP COLUMN modulo2_id;
-- ALTER TABLE ingenio.ingenio.cto24h DROP COLUMN turno1_id;
-- ALTER TABLE ingenio.ingenio.cto24h DROP COLUMN turno2_id;

-- Actualizar en servidor
-- ALTER TABLE ingenio.ingenio.blcpdetalle1 ADD modificable bool NULL;
-- UPDATE blcpdetalle1 SET modificable=false;
-- ALTER TABLE ingenio.ingenio.blcdetalle1 ADD modificable bool NULL;
-- UPDATE blcdetalle1 SET modificable=false;

ALTER TABLE ingenio.ingenio.bagazo ADD itemsporhoracreados bool NULL;
UPDATE ingenio.ingenio.bagazo SET itemsporhoracreados=false;

-- ALTER TABLE ingenio.ingenio.blcdetalle12 ADD modificable bool
-- ALTER TABLE ingenio.ingenio.blcpdetalle12 ADD modificable bool

-- DROP SCHEMA ingenio CASCADE;
