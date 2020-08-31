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
        getTotalesStockFabrica()
        //getValorCampo()
        //getSumaValorDetallesPorIndicador()
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
    
    def getSumaValores(def desde, def hasta, String indicador){
        def padreId = "ff8080817438ed5b0174391063400231"
        def campoFk = "stockFabrica.id"
        def (suma, i) = [0, 0]
        (desde..hasta).each{
            def d = SqlUtil.instance.getDetallePorIndicador(padreId, "StockFabricaDetalle${it}", campoFk, indicador)
            suma += d ? d.valor: 0
            // if (indicador=="Vt" || indicador=="VTot")
            //     println "${it}, ${d.valor}"
        }
        return suma
    }
    
    void getTotalesStockFabrica(){
        // **************************************************
        // BG149: Prom Solidos (Brix) Total/día
        // Brix:  1..28, 30..38, 40..42, 44..47, 49..49, 51..65
        // Bx  : 66..68
        // =+(Q4+AJ3+J13+AB15+BA13+J27+Q27+AF23+AL23+AR23+AF33+AL33+J52+Q52+X52+AE52+AL52+AZ47+O60+U60+AO59+AZ59+L71+R71+X71+AD71+AJ71+AP71+N81+T81+Z81+AF81+AL81+AR81+AX81+BD81+BJ81+K93+Q93+W93+AP97+AV97+BB97+BH97+BT97+J107+P107+V107+AB107+AM107+AS107+AY107+BE107+I120+O120+U120+AA120+AN120+AT120+BG121+K137+Q137+W137)/63
        def bg149 = Calculo.instance.redondear((
            getSumaValores(1 , 28, "Brix") + 
            getSumaValores(30, 38, "Brix") +  
            getSumaValores(40, 42, "Brix") +  
            getSumaValores(44, 47, "Brix") +  
            getSumaValores(49, 49, "Brix") +  
            getSumaValores(51, 65, "Brix") +
            getSumaValores(66, 68, "Bx")) / 63, 3
        )
        
        // **************************************************
        // BG150: Prom Sac (Pol) Total/día
        // Sac:  1..28, 30..38, 40..42, 44..47, 49..49, 51..68
        //
        // =+(Q5+AJ4+J14+AB16+BA14+J28+Q28+AF24+AL24+AR24+AF34+AL34+J53+Q53+X53+AE53+AL53+AZ48+O61+U61+AO60+AZ60+L72+R72+X72+AD72+AJ72+AP72+N82+T82+Z82+AF82+AL82+AR82+AX82+BD82+BJ82+K94+Q94+W94+AP98+AV98+BB98+BH98+BT98+J108+P108+V108+AB108+AM108+AS108+AY108+BE108+I121+O121+U121+AA121+AN121+AT121+BG122+K133+Q133+W133)/63
        def bg150 = Calculo.instance.redondear((
            getSumaValores(1 , 28, "Sac") + 
            getSumaValores(30, 38, "Sac") +  
            getSumaValores(40, 42, "Sac") +  
            getSumaValores(44, 47, "Sac") +  
            getSumaValores(49, 49, "Sac") +  
            getSumaValores(51, 68, "Sac")) / 63, 3
        )

        // BG151: Prom Pza Total/día
        // =+BG150/BG149*100
        def bg151 = Calculo.instance.redondear(bg149 ? bg150/bg149*100: 0, 3)
        def bg152 = new BrixDensidadWp().getP(bg149)
        
        // **************************************************
        // Peso Material Total/día (Tot)
        // Vt: 1..4, 8..12, 23..28, 30..38, 40..42, 44..47, 49..49, 51..68
        // VTot 5..7, 13..22
        // =+(K6+AQ9+J12+AB14+BA12+J26+Q26+AF22+AL22+AR22+AF32+AL32+J51+Q51+X51+AE51+AL51+AZ46+O59+U59+AO58+AZ58+L70+R70+X70+AD70+AJ70+AP70+N80+T80+Z80+AF80+AL80+AR80+AX80+BD80+BJ80+K92+Q92+W92+AP96+AV96+BB96+BH96+BT96+J106+P106+V106+AB106+AM106+AS106+AY106+BE106+I119+O119+U119+AA119+AN119+AT119+BG120+K132+Q132+W132)*BG152/1000

/*
         =+(K6+AQ9+J12+AB14+
        BA12+J26+Q26+AF22+AL22+
        AR22+AF32+AL32+J51+Q51+X51+
        AE51+AL51+AZ46+O59+U59+AO58+AZ58+L70+R70+X70+AD70+AJ70+AP70+N80+T80+Z80+AF80+AL80+AR80+AX80+BD80+BJ80+K92+Q92+W92+AP96+AV96+BB96+BH96+BT96+J106+P106+V106+AB106+AM106+AS106+AY106+BE106+I119+O119+U119+AA119+AN119+AT119+BG120+K132+Q132+W132)*BG152/1000
*/
        def bg147 = Calculo.instance.redondear((
            getSumaValores( 1 , 4  , "Vt") +
            getSumaValores( 8 , 12 , "Vt") +
            getSumaValores(23 , 28 , "Vt") +
            getSumaValores(40 , 42 , "Vt") +
            getSumaValores(44 , 47 , "Vt") +
            getSumaValores(49 , 49 , "Vt") +
            getSumaValores(65 , 68 , "Vt") +
            getSumaValores(5  , 7  , "VTot") +
            getSumaValores(13 , 22 , "VTot") +
            getSumaValores(30 , 38 , "VTot") +
            getSumaValores(51 , 64 , "VTot") ) * (bg152/1000), 3
        )
        
        def bg153 = Calculo.instance.redondear(bg147*bg149/100, 3)
        def bg154 = Calculo.instance.redondear(bg147*bg150/100, 3)

        def q133 = getSumaValores(67 , 67  , "Sac")
        def u150 = getSumaValores(70 , 70  , "Pza")

        def bg155 = Calculo.instance.redondear((q133*(bg151-u150))/(bg151*(q133-u150))*100 , 3)
        
        def bg156 = Calculo.instance.redondear(bg154*bg155/100, 3)
        
        def bg157 = Calculo.instance.redondear((bg156/q133)*100, 3)
        def bg158 = Calculo.instance.redondear(bg154-bg156, 3)
        
        def u146 = getSumaValores(70 , 70  , "Sac")
        def bg159 = Calculo.instance.redondear(bg158/u146*100 , 3)

        println ""
        println ">>> bg147: ${bg147}"
        println ">>> bg149: ${bg149}"
        println ">>> bg150: ${bg150}"
        println ">>> bg151: ${bg151}"
        println ">>> bg152: ${bg152}"
        println ">>> bg153: ${bg153}"
        println ">>> bg154: ${bg154}"
        println ">>> bg155: ${bg155}"
        println ">>> bg156: ${bg156}"
        println ">>> bg157: ${bg157}"
        println ">>> bg158: ${bg158}"
        println ">>> bg159: ${bg159}"

        // BG152: Densidad Kg/Mᶟ
        // BrixDensidadWp (BG149)

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
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles", "mfBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles", "mfSac")
        
        println "\n>>> Magmas"
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Magmas", "mbBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Magmas", "mbSac")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Magmas", "mcBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Magmas", "mcSac")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Magmas", "mrBri2")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Magmas", "mrSac")
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
