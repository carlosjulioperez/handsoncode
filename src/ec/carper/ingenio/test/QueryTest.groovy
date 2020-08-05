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
        getValorCampo()
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

    void getValorCampo(){
        //println SqlUtil.instance.getCampo(Aux.instance.diaTrabajoId, "Paro" , "totalParada")
        //println SqlUtil.instance.getValorCampoBlc(Aux.instance.diaTrabajoId, "BlcDetalle1" , "canaDia")
        
        // BLC
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Trash" , "avgPorcTrash")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cana" , "brix")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cana" , "porcSacarosa")
        
        println SqlUtil.instance.getValorCampoBlc(Aux.instance.diaTrabajoId, "BlcDetalle21" , "sacCanaDac")
        println SqlUtil.instance.getValorCampoBlc(Aux.instance.diaTrabajoId, "BlcDetalle21" , "brixCanaDac")
        
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cana" , "porcFibra")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cana" , "porcHumedad")

        //Bagazo
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "porcSacarosa")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "porcFibra")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "porcHumedad")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "brix")
        
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "BtuLbBagazo" , "pcBtuLb")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "BtuLbBagazo" , "porcCenBs")

        //Miel Fina Melaza
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles" , "mfBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles" , "mfSac")
        
        //cto24H
        println SqlUtil.instance.getValorDetalleCampo(Aux.instance.diaTrabajoId, "cto24H", "Cto24HDetalle5" , "porcCenizas")
        //g184 miel final melaza 7.5
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
