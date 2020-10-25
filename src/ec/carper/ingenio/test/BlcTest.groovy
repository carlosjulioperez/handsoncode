package ec.carper.ingenio.test

import org.openxava.tests.*

class BlcTest extends ModuleTestBase {

    BlcTest(String testName) {
        super(testName, "Ingenio", "Blc")
    }

    void test() throws Exception {
        login("admin", "admin")
        crear()
        // consultar()
    }
    
    void consultar(){
        setConditionComparators(["contains_comparator"])
        setConditionValues(["principal"])
        execute ("List.filter") 
        execute ("List.viewDetail", "row=0")
        execute ("Blc.consultarDatos")
        assertNoErrors()
    }

    void crear(){
        
        execute("CRUD.new")
        
        setValue("diaTrabajo.id" , Aux.instance.diaTrabajoId)
        // setValue("diaTrabajo.id" ,"ff80808174d2eb750174d3096a920000")
        setValue("descripcion"   , "principal")
        execute ("Ingenio.save")
        assertNoErrors()
        
        setConditionComparators(["contains_comparator"])
        setConditionValues(["principal"])
        execute("List.filter") 
        //printHtml() 
        execute("List.viewDetail", "row=0"); // Pulsamos en la primera fila
        assertValue("descripcion", "principal")
        execute    ("Blc.cargarItems")
        assertCollectionRowCount("detalle1", 13)
        execute    ("Blc.consultarDatos")
        assertNoErrors()


        // Caña / Método laboratorio de caña
        execute("Sections.change", "activeSection=2")
        assertCollectionRowCount("detalle21", 6)
        
        // Usar índice en vez del nombre del campo permite validar solo los deseados
        //assertValueInCollection("detalle21" , 0 , "valor" , "7.88")
        assertValueInCollection("detalle21" , 0 , 2, "7.88") 

        assertValueInCollection("detalle21" , 1 , 2 , "15.72")
        assertValueInCollection("detalle21" , 2 , 2 , "13.98")
        assertValueInCollection("detalle21" , 3 , 2 , "88.93")
        assertValueInCollection("detalle21" , 4 , 2 , "17.24")
        assertValueInCollection("detalle21" , 5 , 2 , "67.04")

        // Caña / Método balance
        execute("Sections.change", "activeSection=1,viewObject=xava_view_section2_section0")
        assertCollectionRowCount("detalle22", 5)

        // Bagazo
        execute("Sections.change", "activeSection=1,viewObject=xava_view_section2")
        assertCollectionRowCount("detalle3", 6)
        assertValueInCollection("detalle3" , 0 , 2 , "2.69")
        assertValueInCollection("detalle3" , 1 , 2 , "50.04")
        assertValueInCollection("detalle3" , 2 , 2 , "46.00")
        assertValueInCollection("detalle3" , 3 , 2 , "3.96")
        assertValueInCollection("detalle3" , 4 , 2 , "3,542.33")
        assertValueInCollection("detalle3" , 5 , 2 , "25.00")

        // Miel fina melaza
        execute("Sections.change", "activeSection=2,viewObject=xava_view_section2")
        assertCollectionRowCount("detalle4", 6)
        assertValueInCollection("detalle4" , 0 , 2 , "86.25")
        assertValueInCollection("detalle4" , 1 , 2 , "102.00")
        assertValueInCollection("detalle4" , 2 , 2 , "118.26")
        assertValueInCollection("detalle4" , 3 , 2 , "100.00")
        assertValueInCollection("detalle4" , 4 , 2 , "0.08")
        // assertValueInCollection("detalle4" , 5 , 2 , "0.08")

        // Jugo Diluido
        execute("Sections.change", "activeSection=3,viewObject=xava_view_section2")
        assertCollectionRowCount("detalle5", 7)
        assertValueInCollection("detalle5" , 0 , 2 , "1,055.32")
        assertValueInCollection("detalle5" , 1 , 2 , "-0.53")
        assertValueInCollection("detalle5" , 2 , 2 , "230.17")
        // assertValueInCollection("detalle5" , 3 , 2 , "101.52")
        assertValueInCollection("detalle5" , 4 , 2 , "14.12")
        assertValueInCollection("detalle5" , 5 , 2 , "12.09")
        assertValueInCollection("detalle5" , 6 , 2 , "85.62")

        // Jugo Claro
        execute("Sections.change", "activeSection=4,viewObject=xava_view_section2")
        assertCollectionRowCount("detalle6", 5)
        assertValueInCollection("detalle6" , 0 , 2 , "183.60")
        assertValueInCollection("detalle6" , 1 , 2 , "31.77")
        assertValueInCollection("detalle6" , 2 , 2 , "15.38")
        assertValueInCollection("detalle6" , 3 , 2 , "13.24")
        assertValueInCollection("detalle6" , 4 , 2 , "86.11")
        
        // Jugo Primera Extracción
        execute("Sections.change", "activeSection=5,viewObject=xava_view_section2")
        assertCollectionRowCount("detalle7", 3)
        assertValueInCollection("detalle7" , 0 , 2 , "18.61")
        assertValueInCollection("detalle7" , 1 , 2 , "15.98")
        assertValueInCollection("detalle7" , 2 , 2 , "85.83")

        // Jugo Residual
        execute("Sections.change", "activeSection=6,viewObject=xava_view_section2")
        assertCollectionRowCount("detalle8", 3)
        assertValueInCollection("detalle8" , 0 , 2 , "15.38")
        assertValueInCollection("detalle8" , 1 , 2 , "13.24")
        assertValueInCollection("detalle8" , 2 , 2 , "86.11")

        // Cachaza
        execute("Sections.change", "activeSection=7,viewObject=xava_view_section2")
        assertCollectionRowCount("detalle9", 3)
        assertValueInCollection("detalle9" , 0 , 2 , "2.69")
        assertValueInCollection("detalle9" , 1 , 2 , "0.00")
        assertValueInCollection("detalle9" , 2 , 2 , "42.86")

        // AzucarGranel y Grasshoper
        execute("Sections.change", "activeSection=8,viewObject=xava_view_section2")
        assertCollectionRowCount("detalle101", 4)
        assertValueInCollection("detalle101" , 0 , 2 , "333.41")
        assertValueInCollection("detalle101" , 1 , 2 , "286.21")
        assertValueInCollection("detalle101" , 2 , 2 , "99.71")
        assertValueInCollection("detalle101" , 3 , 2 , "0.05")

        execute("Sections.change", "activeSection=1,viewObject=xava_view_section2_section8")
        assertCollectionRowCount("detalle102", 3)
        assertValueInCollection("detalle102" , 0 , 2 , "239.36")
        assertValueInCollection("detalle102" , 1 , 2 , "115.66")
        assertValueInCollection("detalle102" , 2 , 2 , "0.04")
        
        execute("Sections.change", "activeSection=3")
        execute('Collection.new','viewObject=xava_view_section3_detalle11')
        assertDialog()
        setValue ( "orden"  , "10")
        setValue ( "granel" , "1470")
        setValue ( "k5"     , "1330")
        // setValue ( "k2"     , "")
        setValue ( "k1"     , "630")
        // setValue ( "g500"   , "")
        setValue ( "g250"   , "504")
        // setValue ( "arroba" , "")
        execute("Collection.save")
        
        assertValue("calQqTotalesDia", "3,682.00")
        assertNoErrors()
        // FINALIZAR
        execute ("Ingenio.save")
        //execute    ("CRUD.delete")
        
        // VALORES DE cana dia
        setConditionComparators(["contains_comparator"])
        setConditionValues(["principal"])
        execute("List.filter") 
        execute("List.viewDetail", "row=0"); // Pulsamos en la primera fila

        execute("Sections.change", "activeSection=0")
        execute('Blc.editDetail','row=0,viewObject=xava_view_section0_detalle1')
        assertDialog(); setValue("valor" , "1259.19"); execute("Collection.save")

        execute('Blc.editDetail','row=4,viewObject=xava_view_section0_detalle1')
        assertDialog(); setValue("valor" , "292"); execute("Collection.save")

        execute('Blc.editDetail','row=5,viewObject=xava_view_section0_detalle1')
        assertDialog(); setValue("valor" , "35.93"); execute("Collection.save")

        execute('Blc.editDetail','row=11,viewObject=xava_view_section0_detalle1')
        assertDialog(); setValue("valor" , "0"); execute("Collection.save")

        assertValueInCollection("detalle1" , 0 , 2, "1,259.19")
        assertValueInCollection("detalle1" , 0 , 6, "8,621.67")

        assertValueInCollection("detalle1" , 1 , 2, "422.92")
        assertValueInCollection("detalle1" , 1 , 6, "2,915.43")

        assertValueInCollection("detalle1" , 2 , 2, "1,211.36") 
        assertValueInCollection("detalle1" , 2 , 4, "1,278.37") 
        assertValueInCollection("detalle1" , 2 , 6, "9,018.81") 

        assertValueInCollection("detalle1" , 3 , 2, "403.74") 
        assertValueInCollection("detalle1" , 3 , 4, "32.06") 
        assertValueInCollection("detalle1" , 3 , 6, "2,518.28") 

        assertValueInCollection("detalle1" , 4 , 2, "292.00") 
        assertValueInCollection("detalle1" , 4 , 4, "23.19") 

        assertValueInCollection("detalle1" , 5 , 2, "35.93") 
        assertValueInCollection("detalle1" , 5 , 4, "2.85") 
        assertValueInCollection("detalle1" , 5 , 6, "206.96") 

        assertValueInCollection("detalle1" , 6 , 2, "43.68") 
        assertValueInCollection("detalle1" , 6 , 4, "3.47") 
        assertValueInCollection("detalle1" , 6 , 6, "259.69") 

        assertValueInCollection("detalle1" , 7 , 2, "3,682.00") 
        assertValueInCollection("detalle1" , 7 , 4, "184.10") 
        assertValueInCollection("detalle1" , 7 , 6, "15,083.34") 

        assertValueInCollection("detalle1" , 8 , 2, "1,259.19") 
        assertValueInCollection("detalle1" , 8 , 6, "8,621.67") 

        assertValueInCollection("detalle1" , 9 , 2, "1,285.15") 
        assertValueInCollection("detalle1" , 9 , 4, "102.06") 
        assertValueInCollection("detalle1" , 9 , 6, "8,958.10") 

        assertValueInCollection("detalle1" , 10 , 2, "308.60") 
        assertValueInCollection("detalle1" , 10 , 4, "24.51") 
        assertValueInCollection("detalle1" , 10 , 6, "2,028.57") 

        assertValueInCollection("detalle1" , 11 , 2, "0.00") 
        assertValueInCollection("detalle1" , 11 , 6, "0.00") 

        assertValueInCollection("detalle1" , 12 , 2, "210.00") 
        assertValueInCollection("detalle1" , 12 , 4, "10.50") 
        assertValueInCollection("detalle1" , 12 , 6, "1,645.00") 
        
        assertNoErrors()
        
        execute("Blc.consultarDatos")
        assertNoErrors()

        execute('Sections.change','activeSection=2')
        execute('Sections.change','activeSection=3,viewObject=xava_view_section2')
        assertValueInCollection("detalle5" , 3 , 2 , "101.52")
        assertNoErrors()

        execute('Sections.change','activeSection=4')
        execute('Blc.editDetail12','row=40,viewObject=xava_view_section4_detalle12') 
        assertDialog(); setValue ( "acumulado" , "81.8"); execute("Collection.save")

        // Consumo / Servicios e insumos de fábrica
        execute('Sections.change','activeSection=5')
        
        execute('Collection.edit','row=0,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=1,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "51789"); execute("Collection.save")

        execute('Collection.edit','row=2,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "51789"); execute("Collection.save")

        execute('Collection.edit','row=3,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "685"); execute("Collection.save")

        execute('Collection.edit','row=4,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "776"); execute("Collection.save")

        execute('Collection.edit','row=5,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "717"); execute("Collection.save")

        execute('Collection.edit','row=7,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "8"); execute("Collection.save")

        execute('Collection.edit','row=8,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=9,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=10,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=11,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=13,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=14,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=15,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=16,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=17,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=19,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "22"); execute("Collection.save")

        execute('Collection.edit','row=20,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0.11"); execute("Collection.save")

        execute('Collection.edit','row=21,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "11"); execute("Collection.save")

        execute('Collection.edit','row=22,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=24,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=25,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=27,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=28,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=29,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=31,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=32,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "1.5"); execute("Collection.save")

        execute('Collection.edit','row=34,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=35,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=36,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "3.4"); execute("Collection.save")

        execute('Collection.edit','row=37,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "150"); execute("Collection.save")

        execute('Collection.edit','row=38,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "2125"); execute("Collection.save")

        execute('Collection.edit','row=40,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "10.15"); execute("Collection.save")

        execute('Collection.edit','row=41,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=42,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=43,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "2"); execute("Collection.save")

        execute('Collection.edit','row=45,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=46,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=47,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=48,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('List.goNextPage',',collection=detalle13')

        execute('Collection.edit','row=50,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "20"); execute("Collection.save")

        execute('Collection.edit','row=51,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "20"); execute("Collection.save")

        execute('Collection.edit','row=53,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "0"); execute("Collection.save")

        execute('Collection.edit','row=54,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "220"); execute("Collection.save")

        execute('Collection.edit','row=55,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "250"); execute("Collection.save")

        execute('Collection.edit','row=57,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "36"); execute("Collection.save")

        execute('Collection.edit','row=58,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "43"); execute("Collection.save")

        execute('Collection.edit','row=59,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "51"); execute("Collection.save")

        execute('Collection.edit','row=60,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "11"); execute("Collection.save")

        execute('Collection.edit','row=61,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "15"); execute("Collection.save")

        execute('Collection.edit','row=62,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "5"); execute("Collection.save")

        execute('Collection.edit','row=63,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "5"); execute("Collection.save")

        execute('Collection.edit','row=64,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "5"); execute("Collection.save")

        execute('Collection.edit','row=65,viewObject=xava_view_section5_detalle13')
        assertDialog(); setValue ( "unidades" , "7"); execute("Collection.save")
    }
}
