package ec.carper.ingenio.test

import ec.carper.ingenio.model.*

import javax.persistence.Query;
import org.openxava.tests.*
import org.openxava.validators.*
import org.openxava.util.*
import static org.openxava.jpa.XPersistence.*;

class BlcTest extends ModuleTestBase {

    BlcTest(String testName) {
        super(testName, "Ingenio", "Blc")
    }

    // Ejemplo JPA
    void cargarItems() throws ValidationException{
        try{
            Blc blc           = new Blc()
            blc.id            = null

            //blc.diaTrabajo  = this.diaTrabajo
            def diaTrabajo    = new DiaTrabajo()
            diaTrabajo.id     = "ff808081711cd37c01711cd403a70000"

            blc.diaTrabajo    = diaTrabajo
            blc.itemsCargados = true
            getManager().persist(blc)

            def lista = getManager().createQuery("FROM BlcPDetalle1 WHERE blcP.id = 1 ORDER BY orden").getResultList()

            lista.each{
                def d1      = new BlcDetalle1()
                d1.id       = null
                d1.blc      = blc
                d1.material = it.material
                d1.unidad   = it.unidad
                d1.unidad2  = it.unidad2
                getManager().persist(d1)
            }
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

    void test() throws Exception {
        cargarItems()
    }

}
