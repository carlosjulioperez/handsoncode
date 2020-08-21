-- SELECT * FROM ingenio.paro;
-- SELECT * FROM ingenio.parodetalle;
-- SELECT * FROM ingenio.azucarcrudo;
-- SELECT * FROM ingenio.azucarcrudodetalle;

SELECT * FROM ingenio.material;

select m.descripcion, valor, m.campo
from ingenio.blcpdetalle6 d, ingenio.material m
where d.material_id = m.id
order by orden

select i.descripcion, valor, i.campo
from ingenio.stockfabricapdetalle1 d, ingenio.indicador i
where d.indicador_id = i.id
order by orden
