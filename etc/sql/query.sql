-- SELECT * FROM ingenio.paro;
-- SELECT * FROM ingenio.parodetalle;
-- SELECT * FROM ingenio.azucarcrudo;
-- SELECT * FROM ingenio.azucarcrudodetalle;

-- SELECT * FROM ingenio.material;

-- SELECT   orden, m.descripcion, m.campo
-- FROM     ingenio.stockprocesopdetalle d, ingenio.material m
-- WHERE    d.material_id = m.id
-- ORDER BY orden;

-- select i.descripcion, valor, i.campo, modificable
-- from ingenio.stockfabricadetalle73 d, ingenio.indicador i
-- where d.indicador_id = i.id
-- order by orden;

-- select * from ingenio.jugodetalle;

-- blc principal: ff80808174dff5150174e00f7cfc0000, diatrabajo_id: 
-- blc anterior : ff80808174d572200174d5b05424014d, diatrabajo_id: ff80808174d2eb750174d3096a920000

SELECT   orden, m.id, m.descripcion, m.campo, valor, cantidad, acumulado
FROM     ingenio.blcdetalle1 d, ingenio.material m
WHERE    d.material_id = m.id AND
         d.blc_id='ff80808174dff5150174e00f7cfc0000'
ORDER BY orden;

-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 0, acumulado = 0 WHERE "id" = 'ff80808174d572200174d5a378e6010c';

-- Para usar en razorsql
-- WHERE blc_id='ff80808174d572200174d5a3721d010b' ORDER BY orden
-- SELECT   *
-- 		 from ingenio.blcdetalle1 d, ingenio.blc c
-- WHERE	 d.blc_id = c.id
-- 		 AND c.diatrabajo_id='ff80808174d2eb750174d3096a920000'
-- ORDER BY orden

-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 7807.45 WHERE "id" = 'ff80808174d572200174d5b05a440150';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 2114.54 WHERE "id" = 'ff80808174d572200174d5b05a440151';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 216.01 WHERE "id" = 'ff80808174d572200174d5b05a450154';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 11401.34 WHERE "id" = 'ff80808174d572200174d5b05a450155';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 7362.48 WHERE "id" = 'ff80808174d572200174d5b05a450156';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 7672.95 WHERE "id" = 'ff80808174d572200174d5b05a450157';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 1719.97 WHERE "id" = 'ff80808174d572200174d5b05a450158';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 1435.00 WHERE "id" = 'ff80808174d572200174d5b05a45015a';
