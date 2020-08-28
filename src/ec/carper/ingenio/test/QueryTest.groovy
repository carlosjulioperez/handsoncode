package ec.carper.ingenio.test
 
import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import javax.persistence.Query;
import org.apache.commons.logging.*
import org.openxava.tests.*
import static org.openxava.jpa.XPersistence.*;

class QueryTest extends ModuleTestBase {

/*
    Customer pedro = (Customer) XPersistence.getManager()
       .createQuery( "from Customer c where c.name = 'PEDRO')")
       .getSingleResult(); // Para obtener una única entidad (2)
   
    List pedros = XPersistence.getManager()
        .createQuery("from Customer c where c.name like 'PEDRO%')")
        .getResultList(); // Para obtener una colección de entidades (3)
        
    ColorMatDetalle o = (ColorMatDetalle) getManager()
        .createQuery("FROM ColorMatDetalle WHERE colorMat.id = :id ")
        .setParameter("id", "ff808081726e376301726e3947fe0000")
        .getSingleResult()

*/
 
    private static Log log = LogFactory.getLog(QueryTest.class)

    QueryTest(String testName) {
        super(testName, "Ingenio", "DiaTrabajo")
    }

    void test() throws Exception {
        //getValorCampo()
        getSumaValorDetallesPorIndicador()
        //getDetallePorIndicador()
        //getCampoPorId()
        //obtenerFecha()
        //getParoTotal()
        //unSoloRegistro()
        //getListaOrdenadaParoDetalle()
        //getCto24H() //DESCARTADO
        //getDiaTrabajo()
        //isDiaTrabajoCerrado()
        //getTrashCanaDiaTrabajoCerrado()
        //getTrashCanaDetalle2()
        //getNativo()
    }

    void getSumaValorDetallesPorIndicador(){
        def padreId = "ff8080817431ea2c017431ebc7a40000"
        def campoFk = "stockFabrica.id"
        def d = SqlUtil.instance.getDetallePorIndicador(padreId, "StockFabricaDetalle1", campoFk, "TonSacJDil")
        if (d)
            println ">>> Indicador: ${d.indicador.descripcion}, valor: ${d.valor}"
    }

    void getDetallePorIndicador(){
        def padreId = "ff808081741db3e901741ddb6d7d00df"
        def modulo  = "StockFabricaDetalle2"
        def campoFk = "stockFabrica.id"
        def campo   = "porcN"
        def d = SqlUtil.instance.getDetallePorIndicador(padreId, modulo, campoFk, campo)

        println "valor anterior : ${d.valor}"
        d.valor = 100
        getManager().persist(d)
        println "valor nuevo    : ${d.valor}"
    }
    
    void obtenerFecha(){
        println SqlUtil.instance.obtenerFecha("06:30", Aux.instance.diaTrabajoId)
    }

    void getValorCampo(){
        println SqlUtil.instance.getCampo(Aux.instance.diaTrabajoId, "Paro" , "totalParada")
        println SqlUtil.instance.getValorCampoBlc(Aux.instance.diaTrabajoId, "BlcDetalle1" , "canaDia")
        
        // BLC
        println "\n>>> BLC"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Trash" , "avgPorcTrash")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cana" , "brix")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cana" , "porcSacarosa")
        
        println SqlUtil.instance.getValorCampoBlc(Aux.instance.diaTrabajoId, "BlcDetalle21" , "sacCanaDac")
        println SqlUtil.instance.getValorCampoBlc(Aux.instance.diaTrabajoId, "BlcDetalle21" , "brixCanaDac")
        
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cana" , "porcFibra")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cana" , "porcHumedad")

        println "\n>>> Bagazo"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "porcSacarosa")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "porcFibra")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "porcHumedad")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "brix")
        
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "BtuLbBagazo" , "pcBtuLb")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "BtuLbBagazo" , "porcCenBs")

        println "\n>>> Miel Fina Melaza"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles" , "mfBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles" , "mfSac")
        
        println "\n>>> cto24H"
        println SqlUtil.instance.getValorDetalleCampo(Aux.instance.diaTrabajoId, "cto24H", "Cto24HDetalle5" , "porcCenizas")
        
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H", "mielF2")
        
        println "\n>>> Jugo Diluido"
        def brixJDil = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jdBri")
        def pJDil = new BrixDensidadWp().getP(brixJDil)
        println brixJDil
        println pJDil
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H", "porcInso")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Fosfatos", "jdFosfatos")
        def jdSac = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jdSac")
        println jdSac
        // =+F10*100/D8
        def pzaJDil = brixJDil ? Calculo.instance.redondear(jdSac*100/brixJDil,2): 0
        println pzaJDil 

        println "\n>>> Jugo Claro"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Turbiedad", "turJClaro")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Fosfatos", "jcFosfatos")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jcBri")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jcSac")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jcPur")

        println "\n>>> Jugo Primera Extracción"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jeBri")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jeSac")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jePur")
        
        println "\n>>> Jugo Residual"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jcBri")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jcSac")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jcPur")

        println "\n>>> Cachaza"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Turbiedad", "polCachaza")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H", "porcConTotLinEva")
        println SqlUtil.instance.getValorDetalleCampo(Aux.instance.diaTrabajoId, "cto24H", "Cto24HDetalle6" , "porc")
        
        println "\n>>> Azucar granel y grasshoper"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "AzucarGranel", "color")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "AzucarGranel", "turb")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "AzucarGranel", "pol")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "AzucarGranel", "humedad")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Grasshoper", "color")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Grasshoper", "turb")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Grasshoper", "humedad")
        
        println "\n>>> Jugo Encalado"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jnBri")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jnSac")
        
        println "\n>>> Jugo Filtrado"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jfBri")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jfSac")
        
        println "\n>>> Meladura"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Meladura", "mcrBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Meladura", "mcrSac")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Meladura", "mclBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Meladura", "mclSac")
        
        println "\n>>> Masas"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Masas", "maBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Masas", "maSac")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Masas", "mbBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Masas", "mbSac")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Masas", "mcBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Masas", "mcSac")
        
        println "\n>>> Tq Fundidor"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "TqFundidor", "bri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "TqFundidor", "sac")
        
        println "\n>>> Mieles"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles", "maBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles", "maSac")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles", "mbBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles", "mbSac")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles", "mrBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles", "mrSac")
        
        println "\n>>> Magmas"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Magmas", "mbBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Magmas", "mbSac")
    }

    void getParoTotal(){
        def lista = getManager().createQuery("FROM Paro where diaTrabajo.id= :id")
                                .setParameter("id", Aux.instance.diaTrabajoId).resultList
        lista.each{
            it.total.each{
                println it.area.descripcion + " " + it.totalParo
            }
        }
    }

    void getDiaTrabajo(){
        DiaTrabajo diaTrabajo = getManager().find( DiaTrabajo.class, Aux.instance.diaTrabajoId) 
        println diaTrabajo.fecha
    }

    void unSoloRegistro(){
        def modulo = "Paro"
        Query query = getManager().createQuery("select count(*) from ${modulo} where diaTrabajo.id= :id")
        query.setParameter("id", Aux.instance.diaTrabajoId)
        def numero = (Integer)query.getSingleResult()
        println numero
    }

    void isDiaTrabajoCerrado(){
        boolean cerrado = (boolean) getManager()
            .createQuery("""
                SELECT d.cerrado 
                FROM Cana o, DiaTrabajo d
                WHERE o.id = :id AND o.diaTrabajo.id = d.id
            """)
            .setParameter("id", "ff8080817211f247017211fcf5810000")
            .getSingleResult()

        println cerrado
    }

    void getTrashCanaDetalle2(){
        Query query = getManager().createQuery("from TrashCanaDetalle2 where trashCana.id= :id ")
        query.setParameter("id", "ff808081716c39f201716c43535d0000")

        //println "*****" + query.getResultList().size()
        query.resultList.each{
            def o = (TrashCanaDetalle2)it
            println ("**************************************************")
            println (o.id)
            println (o.hora)
            println (o.mlReductores)
            println (o.valTab7SusRed)
            println (o.valPorcAzuRed )
        }
        /*
        // Java's way
        for (Object o: query.getResultList()){
            TrashCanaDetalle2 det = (TrashCanaDetalle2)o
            log.warn (">>>>>>" + det.hora)
//hora, mlReductores, valTab7SusRed, valPorcAzuRed f
        }
        */
    }

    void getTrashCanaDiaTrabajoCerrado(){
        Query query = getManager().createQuery("select diaTrabajo.cerrado from TrashCana o where id= :id ")
        query.setParameter("id", "ff808081711cd37c01711cd45a1f0001")
        def cerrado = (boolean) query.getSingleResult()
        log.warn (cerrado)
    }
    
    void getCto24H(){
        Query query = getManager().createQuery("select pd11 from Cto24H where diaTrabajo.id= :id ")
        query.setParameter("id", Aux.instance.diaTrabajoId)
        query.resultList.each{
            def o = (Cto24H)it
            println ("**************************************************")
            println (o.pd11)
            println (o.pd12)
        }
    }
    
    void getListaOrdenadaParoDetalle(){
        Query query = getManager().createQuery("from ParoDetalle o where paro.id= :id order by area.id")
        query.setParameter("id", "ff808081738e55b401738e567b880000")
        query.resultList.each{
            println it.area.id + " " + it.totalParo 
        }
    }
    
    // Ejemplo JPA
    void cargarItems(){
        try{
            Blc blc           = new Blc()
            blc.id            = null

            //blc.diaTrabajo  = this.diaTrabajo
            // TODO optimizar codigo groovy
            def diaTrabajo    = new DiaTrabajo()
            diaTrabajo.id     = Aux.instance.diaTrabajoId

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
            ex.printStackTrace()
        }
    }

    /*
    void getNativo(){
        Query q = em.createNativeQuery("SELECT o.diaTrabajo.cerrado FROM TrashCana o WHERE o.id= :id ")
        q.setParameter("id", "ff808081711cd37c01711cd45a1f0001")
        Object[] arreglo = (Object[]) q.getSingleResult();
        // System.out.println("Author "
        //         + arreglo[0]
        //         + " "
        //         + arreglo[1]);
    }
    */
}
