select i.descripcion, valor, i.campo, modificable, d.indicador_id
from ingenio.stockfabricadetalle17 d, ingenio.indicador i
where d.indicador_id = i.id
-- AND i.campo in ('H2','H3')
AND stockfabrica_id='ff80808175c9cc7b0175c9f6e9260013'
order by orden;

-- H2 = 03
-- H3 = 04
UPDATE ingenio.stockfabricadetalle17
set valor = 1.1
WHERE indicador_id = '03'

UPDATE ingenio.stockfabricadetalle17
set valor = 0.277
WHERE indicador_id = '04'

select * from ingenio.stockfabricadetalle17 where stockfabrica_id='ff80808175c9cc7b0175c9f6e9260013';
  
SELECT   orden, m.id, m.descripcion, m.campo, valor, cantidad, acumulado
FROM     ingenio.blcdetalle1 d, ingenio.material m, ingenio.blc c, ingenio.zafra z, ingenio.diatrabajo dt
WHERE    d.material_id = m.id 
AND      m.id in ('001','002','003','004')
--AND	     m.campo = 'jDiluidoBr'
--AND      d.blc_id='ff80808175cd9e8b0175cdbb8df70085'
AND      d.blc_id = c.id
AND 	 c.diatrabajo_id = dt."id"
AND 	 dt.zafra_id = z.id
AND      z.descripcion = 'ZAFRA 2020'
ORDER BY dt.fecha, orden;

DROP TABLE ingenio.ingenio.tablabxeq