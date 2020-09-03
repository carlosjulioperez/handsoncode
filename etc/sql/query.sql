-- SELECT * FROM ingenio.paro;
-- SELECT * FROM ingenio.parodetalle;
-- SELECT * FROM ingenio.azucarcrudo;
-- SELECT * FROM ingenio.azucarcrudodetalle;

-- SELECT * FROM ingenio.material;

SELECT   orden, m.descripcion, m.campo
FROM     ingenio.stockprocesopdetalle d, ingenio.material m
WHERE    d.material_id = m.id
ORDER BY orden;

select i.descripcion, valor, i.campo, modificable
from ingenio.stockfabricadetalle63 d, ingenio.indicador i
where d.indicador_id = i.id
order by orden;
