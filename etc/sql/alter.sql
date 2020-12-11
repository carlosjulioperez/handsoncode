-- DROP SCHEMA ingenio CASCADE;

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
-- ALTER TABLE ingenio.ingenio.blcdetalle12 ADD modificable bool
-- ALTER TABLE ingenio.ingenio.blcpdetalle12 ADD modificable bool

-- ALTER TABLE ingenio.ingenio.bagazo ADD itemsporhoracreados bool NULL;
-- UPDATE ingenio.ingenio.bagazo SET itemsporhoracreados=false;

-- ALTER TABLE ingenio.ingenio.ph ADD itemsporhoracreados bool NULL;
-- UPDATE ingenio.ingenio.ph SET itemsporhoracreados=true; --Para evitar que los usuarios sobreescriban los registros anteriores

-- ALTER TABLE ingenio.ingenio.fosfatos DROP COLUMN cten1;
-- ALTER TABLE ingenio.ingenio.fosfatos DROP COLUMN cten2;

-- ALTER TABLE ingenio.ingenio.btulbbagazo ALTER COLUMN phum    TYPE numeric(19,4);
-- ALTER TABLE ingenio.ingenio.btulbbagazo ALTER COLUMN pcrisol TYPE numeric(19,4);
-- ALTER TABLE ingenio.ingenio.btulbbagazo ALTER COLUMN pcricen TYPE numeric(19,4);
-- ALTER TABLE ingenio.ingenio.btulbbagazo ALTER COLUMN pmtra   TYPE numeric(19,4);

-- ALTER TABLE ingenio.ingenio.cto24hdetalle2 ALTER COLUMN pj  TYPE numeric(19,4);
-- ALTER TABLE ingenio.ingenio.cto24hdetalle2 ALTER COLUMN sc8 TYPE numeric(19,4);


-- ALTER TABLE ingenio.ingenio.stockprocesopdetalle ADD modificable bool;
-- ALTER TABLE ingenio.ingenio.stockprocesodetalle1 ADD modificable bool;
-- UPDATE ingenio.ingenio.stockprocesopdetalle SET modificable=false;
-- UPDATE ingenio.ingenio.stockprocesodetalle1 SET modificable=false;

-- ALTER TABLE ingenio.ingenio.flujojugo ADD itemsporhoracreados bool NULL;
-- UPDATE ingenio.ingenio.flujojugo SET itemsporhoracreados=true;

-- ALTER TABLE ingenio.ingenio.trashcana ADD itemsporhoracreados bool NULL;
-- UPDATE ingenio.ingenio.trashcana SET itemsporhoracreados=true;

-- ALTER TABLE ingenio.ingenio.cana ADD itemsporhoracreados bool NULL;
-- UPDATE ingenio.ingenio.cana SET itemsporhoracreados=true;

-- ALTER TABLE ingenio.ingenio.jugo ADD itemsporhoracreados bool NULL;
-- UPDATE ingenio.ingenio.jugo SET itemsporhoracreados=true;

-- ALTER TABLE ingenio.ingenio.azucargranel ADD itemsporhoracreados bool NULL;
-- UPDATE ingenio.ingenio.azucargranel SET itemsporhoracreados=true;

ALTER TABLE ingenio.ingenio.cto24hdetalle5 ALTER COLUMN pmtra       TYPE numeric(19,4);
ALTER TABLE ingenio.ingenio.cto24hdetalle5 ALTER COLUMN pcrisol     TYPE numeric(19,4);
ALTER TABLE ingenio.ingenio.cto24hdetalle5 ALTER COLUMN pcricen     TYPE numeric(19,4);
ALTER TABLE ingenio.ingenio.cto24hdetalle5 ALTER COLUMN porccenizas TYPE numeric(19,2);
