package ec.carper.ingenio.test

import org.openxava.tests.*

class BlcTest extends ModuleTestBase {

    BlcTest(String testName) {
        super(testName, "Ingenio", "Blc")
    }

    void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")
        
        setValue("diaTrabajo.id" , Aux.instance.diaTrabajoId)
        setValue("descripcion"   , "JUNIT")
        execute ("Ingenio.save")
        assertNoErrors()
        
        setConditionValues("", "JUNIT"); // Establece los valores para filtrar los datos
        setConditionComparators("=", "contains_comparator"); // Pone los comparadores para filtrar los datos
        execute("List.filter"); 
        execute("List.viewDetail", "row=0"); // Pulsamos en la primera fila
        assertValue("descripcion", "JUNIT")
        execute    ("Blc.cargarItems")
        assertCollectionRowCount("detalle1", 10)

        execute("Sections.change", "activeSection=2")
        assertCollectionRowCount("detalle21", 6)
        
        // FINALIZAR
        execute    ("CRUD.delete")
        assertNoErrors()
    }
}
