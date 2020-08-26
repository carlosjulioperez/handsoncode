-- SELECT * FROM ingenio.paro;
-- SELECT * FROM ingenio.parodetalle;
-- SELECT * FROM ingenio.azucarcrudo;
-- SELECT * FROM ingenio.azucarcrudodetalle;

SELECT * FROM ingenio.material;

select m.descripcion, valor, m.campo
from ingenio.blcpdetalle6 d, ingenio.material m
where d.material_id = m.id
order by orden

select i.descripcion, valor, i.campo, modificable
from ingenio.stockfabricapdetalle1 d, ingenio.indicador i
where d.indicador_id = i.id
order by orden

INSERT INTO ingenio.ingenio.stockfabricapdetalle20
SELECT * FROM ingenio.ingenio.stockfabricapdetalle19;

ALTER TABLE ingenio.ingenio.stockfabricapdetalle1  ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle2  ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle3  ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle4  ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle5  ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle6  ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle7  ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle8  ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle9  ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle10 ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle11 ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle12 ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle13 ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle14 ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle15 ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle16 ALTER COLUMN valor TYPE numeric(19,3);
ALTER TABLE ingenio.ingenio.stockfabricapdetalle17 ALTER COLUMN valor TYPE numeric(19,3);
