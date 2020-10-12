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
        getValoresBlc()

        //getTotalesStockFabrica()
        //getSum()
        //getDiaTrabajoCerrado()
        //getValorDetalleCampoXHora()
        //getValoresStockProceso()
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

    def getSum(){
        // https://www.w3resource.com/sql/aggregate-functions/sum-with-group-by.php
        // https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/jpql-group-by-having.html
        def diaFin = 6
        Query query = getManager().createQuery("""
            SELECT  d.material.id, SUM(d.valor)
            FROM    BlcDetalle1 d, Blc c, Parametro p, Zafra z, DiaTrabajo dT
            WHERE   d.blc.id            = c.id
                    AND p.valor         = z.codigo
                    AND p.nombre        = 'ZAFRA_VIGENTE'
                    AND c.diaTrabajo.id = dT.id
                    AND dT.numeroDia BETWEEN z.diaTrabajoInicio.numeroDia AND :diaFin
            GROUP BY d.material.id ORDER BY d.material.id
        """)
        query.setParameter("diaFin", diaFin)
        println "\nBLC valores acumulados:"
        query.resultList.each{
            println ">>> material: ${it[0]}, suma: ${it[1]}"
        }

    }

    def getDiaTrabajoCerrado(){
        println "\n>>> ¿DiaTrabajoCerrado?"
        println "Cto24H       : " + SqlUtil.instance.isCerrado("Cto24H"       , "ff80808173e8b2c00173e8b80cab0007")
        println "Blc          : " + SqlUtil.instance.isCerrado("Blc"          , "ff80808174c721790174c72a10c90000")
        println "StockFabrica : " + SqlUtil.instance.isCerrado("StockFabrica" , "ff808081745219640174521b44410000")
        println "StockProceso : " + SqlUtil.instance.isCerrado("StockProceso" , "ff80808174bd90450174bd9ee9310000")

    }

    def getValorDetalleCampoXHora(){
        String hora      = "2019-08-07 08:00:00"
        //def porcSacJR = new JugoDetalle().getPorcSacJR(Aux.instance.diaTrabajoId, Util.instance.toTimestamp(hora))

        println SqlUtil.instance.getValorDetalleCampoXHora(Aux.instance.diaTrabajoId, Util.instance.toTimestamp(hora), "jugo", "JugoDetalle", "jdBri")

    }

    def suma (desde, hasta, objPadre, txtDet, indicador){
        def suma = 0
        (desde..hasta).each{
            suma += SqlUtil.instance.getDetValorPorDTI(Aux.instance.diaTrabajoId, objPadre, "${txtDet}${it}", indicador)
        }
        return suma
    }
    
    def getValoresStockProceso(){
        //getDetalleValorPorDiaTrabajoEIndicador

        // Lectura temp=30 y eq=4
        def calculos = { campo, temp, eq ->

            def (num, volumen1) = [0, 0]
            switch (campo){
                case "tanJugDil" : num=1  ; break ;
                case "tanJugCla" : num=2  ; break ;
                case "tanJugEnc" : num=3  ; break ;
                case "tanJugFil" : num=4  ; break ;
                case "claJug"    : num=5  ; break ;
                case "torSulJug" : num=6  ; break ;
                case "calJugCla" : num=8  ; break ;
                case "calJug"    : num=9  ; break ;
                case "eva1"      : num=13 ; break ;
                case "eva2"      : num=14 ; break ;
                case "eva3"      : num=15 ; break ;
                case "eva4"      : num=16 ; break ;
                case "tanMelCru" : num=18 ; break ;
                case "calMel"    : num=19 ; break ;
                case "claMel"    : num=21 ; break ;
                case "vasRea"    : num=22 ; break ;
                case "tac1"      : num=23 ; break ;
                case "tac2"      : num=24 ; break ;
                case "tac3"      : num=26 ; break ;
                case "tac4"      : num=28 ; break ;
                case "tanMel1"   : num=30 ; break ;
                case "tanMel2"   : num=31 ; break ;
                case "tanMel3"   : num=29 ; break ;
                case "tanFun1"   : num=32 ; break ;
                case "tanFun2"   : num=33 ; break ;
                case "tanMie1"   : num=34 ; break ;
                case "tanMie2"   : num=35 ; break ;
                case "tanMie3"   : num=36 ; break ;
                case "tanMie4"   : num=37 ; break ;
                case "tanPreMie" : num=38 ; break ;
                case "tanMieClM" : num=39 ; break ;
                case "sem1"      : num=40 ; break ;
                case "sem2"      : num=41 ; break ;
                case "sem3"      : num=42 ; break ;
                case "sem4"      : num=43 ; break ;
                case "recMag2"   : num=44 ; break ;
                case "recMas1"   : num=45 ; break ;
                case "recMas2"   : num=46 ; break ;
                case "recMas3"   : num=47 ; break ;
                case "recMas4"   : num=48 ; break ;
                case "recMas5"   : num=49 ; break ;
                case "recMas6"   : num=50 ; break ;
                case "aliCen1"   : num=51 ; break ;
                case "aliCen2"   : num=52 ; break ;
                case "aliCen3"   : num=53 ; break ;
                case "aliCen4"   : num=54 ; break ;
                case "recMag1"   : num=55 ; break ;
                case "recMag3"   : num=56 ; break ;
                case "recMag4"   : num=57 ; break ;
                case "recMie1"   : num=59 ; break ;
                case "recMie2"   : num=60 ; break ;
                case "recMie3"   : num=61 ; break ;
                case "recMie4"   : num=61 ; break ; //Duplicado de recMie3
                case "funVie1"   : num=63 ; break ;
                case "funNue2"   : num=64 ; break ;
                case "criVer"    : num=65 ; break ;
                case "tol50K1"   : num=66 ; break ;
                case "tol50K2"   : num=67 ; break ;
                case "tolFam"    : num=68 ; break ;
            }

            def l = [5, 6, 13,14,15,16, 18,19,21,22, 29,30,31,32,33,34,35,36,37,38,39, 51,52,53,54,55,56,57, 59,60,61, 63,64] // Para no usar los engorrosos "if"
            def ind1 = l.find {it==num} ? "VTot" : "Vt"

            def objPadre = "stockFabrica"
            def txtDet   = "StockFabricaDetalle"
            def detalle  = "${txtDet}${num}"

            def factor   = new FactorVolumen().getValor(temp, eq+1)

            switch(num){
                case 9:
                    volumen1 = suma(9, 12, objPadre, txtDet, ind1); break;
                case 19:
                    volumen1 = suma(19, 20, objPadre, txtDet, ind1); break;
                case 24:
                    volumen1 = suma(24, 25, objPadre, txtDet, ind1); break;
                case 26:
                    volumen1 = suma(26, 27, objPadre, txtDet, ind1); break;
                case 28:
                    volumen1 = suma(28, 29, objPadre, txtDet, ind1); break;
                default:
                    volumen1 = SqlUtil.instance.getDetValorPorDTI(Aux.instance.diaTrabajoId, objPadre, detalle, ind1); break;
            }
            

            def (porcBrix, densidad) = [0, 0]
            if (campo=="tol50K1" || campo=="tol50K2" || campo=="tolFam"){
                porcBrix = 100 - SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "AzucarGranel", "humedad")
                densidad = 906.15199 
                factor   = 1
            }else{
                porcBrix = SqlUtil.instance.getDetValorPorDTI(Aux.instance.diaTrabajoId, objPadre, detalle, "Brix")
                densidad = new BrixDensidadWp().getP(porcBrix)
            }
            
            def volumen2 = factor ? Calculo.instance.redondear(volumen1/factor, 3): 0
            def peso     = Calculo.instance.redondear(densidad*volumen2/1000, 3)
            def tonBrix  = Calculo.instance.redondear(peso*porcBrix/100, 3)
            
            // Caso especial (E60)
            if (campo=="recMie4")
                detalle  = "${txtDet}${num+1}"
            
            def porcSac = 0
            if (campo=="tol50K1" || campo=="tol50K2" || campo=="tolFam")
                porcSac  = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "AzucarGranel", "pol")
            else    
                porcSac  = SqlUtil.instance.getDetValorPorDTI(Aux.instance.diaTrabajoId, objPadre, detalle, "Sac")
            
            def tonSac   = Calculo.instance.redondear(peso*porcSac/100, 3)
            def pureza   = porcBrix ? Calculo.instance.redondear(porcSac/porcBrix*100, 3): 0
            
            // Caso especial (J61, K61, L61)
            if (campo=="funVie1"){
                def d = SqlUtil.instance.getDetallePorDTM(Aux.instance.diaTrabajoId, "stockProceso", "StockProcesoDetalle1", "recMie4")
                if (d){
                    tonBrix = d.tonBrix ?:0
                    porcSac = d.porcSac ?:0
                    tonSac  = d.tonSac ?:0
                    // println tonBrix
                    // println porcSac
                    // println tonSac
                    pureza  = (porcSac && porcBrix) ? Calculo.instance.redondear(porcSac/porcBrix*100, 3): 0
                }
            }

            // println ""
            // println "temp     : ${temp}"
            // println "volumen1 : ${volumen1}"
            // println "volumen2 : ${volumen2}"
            // println "peso     : ${peso}"
            // println "porcBrix : ${porcBrix}"
            // println "eq       : ${eq}"
            // println "tonBrix  : ${tonBrix}"
            // println "porcSac  : ${porcSac}"
            // println "tonSac   : ${tonSac}"
            // println "pureza   : ${pureza}"
            // println "densidad : ${densidad}"
            // println "factor   : ${factor}"

            println "| ${temp} | ${volumen1} | ${volumen2} | ${peso} | ${porcBrix} | ${eq} | ${tonBrix} | ${porcSac} | ${tonSac} | ${pureza} | ${densidad} | ${factor}"
        }
       
        println "\n"
        calculos("tanJugDil" , 30  , 4)
        calculos("tanJugCla" , 95  , 4)
        calculos("tanJugEnc" , 60  , 4)
        calculos("tanJugFil" , 45  , 4)
        calculos("claJug"    , 95  , 4)
        calculos("torSulJug" , 70  , 4)
        calculos("calJugCla" , 100 , 4)
        calculos("calJug"    , 85  , 4)
        calculos("eva1"      , 100 , 4)
        calculos("eva2"      , 100 , 6)
        calculos("eva3"      , 90  , 8)
        calculos("eva4"      , 65  , 14)
        calculos("tanMelCru" , 65  , 13)
        calculos("calMel"    , 75  , 13)
        calculos("claMel"    , 70  , 13)
        calculos("vasRea"    , 75  , 13)
        calculos("tac1"      , 55  , 19)
        calculos("tac2"      , 55  , 19)
        calculos("tac3"      , 55  , 19)
        calculos("tac4"      , 55  , 19)
        calculos("tanMel1"   , 55  , 13)
        calculos("tanMel2"   , 55  , 13)
        calculos("tanMel3"   , 55  , 13)
        calculos("tanFun1"   , 70  , 13)
        calculos("tanFun2"   , 70  , 13)
        calculos("tanMie1"   , 35  , 17)
        calculos("tanMie2"   , 35  , 17)
        calculos("tanMie3"   , 45  , 18)
        calculos("tanMie4"   , 25  , 0 )
        calculos("tanPreMie" , 25  , 18)
        calculos("tanMieClM" , 25  , 0)
        calculos("sem1"      , 50  , 0)
        calculos("sem2"      , 40  , 0)
        calculos("sem3"      , 40  , 19)
        calculos("sem4"      , 40  , 0)
        calculos("recMag2"   , 35  , 19)
        calculos("recMas1"   , 55  , 19)
        calculos("recMas2"   , 55  , 19)
        calculos("recMas3"   , 50  , 19)
        calculos("recMas4"   , 40  , 19)
        calculos("recMas5"   , 50  , 19)
        calculos("recMas6"   , 50  , 19)
        calculos("aliCen1"   , 50  , 19)
        calculos("aliCen2"   , 50  , 19)
        calculos("aliCen3"   , 40  , 19)
        calculos("aliCen4"   , 45  , 19)
        calculos("recMag1"   , 35  , 19)
        calculos("recMag3"   , 35  , 19)
        calculos("recMag4"   , 40  , 19)
        calculos("recMie1"   , 40  , 17)
        calculos("recMie2"   , 40  , 17)
        calculos("recMie3"   , 35  , 18)
        calculos("recMie4"   , 35  , 18)
        calculos("funVie1"   , 80  , 18)
        calculos("funNue2"   , 85  , 0)
        calculos("criVer"    , 45  , 19)
        calculos("tol50K1"   , 35  , 0)
        calculos("tol50K2"   , 35  , 0)
        calculos("tolFam"    , 35  , 0)

    }

    def getSumaValores(def desde, def hasta, String indicador){
        // def padreId = "ff808081745219640174521b44410000"
        // def campoFk = "stockFabrica.id"
        def campoFk = "stockFabrica.diaTrabajo.id"
        def (suma, i) = [0, 0]
        (desde..hasta).each{
            //def d = SqlUtil.instance.getDetallePorIndicador(padreId, "StockFabricaDetalle${it}", campoFk, indicador)
            def d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle${it}", campoFk, indicador)
            suma += d ? d.valor: 0
            // if (indicador=="Vt" || indicador=="VTot")
            //     println "${it}, ${d.valor}"
        }
        return suma
    }
    
    def getTotalValor(def num, def campo){
        def campoFk = "stockFabrica.diaTrabajo.id"
        def d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle${num}", campoFk, campo)
        return d.valor?:0
    }
    
    void getTotalesStockFabrica(){
        def tonAzuDis = getTotalValor(73, "tonAzuDis")

        def bg144 = 
            getTotalValor(72, "tonSacTraJug") +
            getTotalValor(72, "tonSacTorSul") +
            getTotalValor(72, "tonSacCal") +
            getTotalValor(72, "tonSacEva") +
            getTotalValor(72, "tonSacClaMel") +
            getTotalValor(72, "tonSacCri") +
            getTotalValor(72, "tonSacTqAlm") +
            getTotalValor(72, "tonSacCriVac") +
            getTotalValor(72, "tonSacRecMas") +
            getTotalValor(72, "tonSacRecMat") +
            getTotalValor(72, "tonSacRecCen") +
            getTotalValor(72, "tonSacRecMieCen") +
            getTotalValor(72, "tonSacFunCriVer") +
            getTotalValor(72, "tonSacSilAzu") - tonAzuDis
        
        // Cambiar id en getSumaValores()

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

        // println ">>> bg151: ${bg151}"
        // println ">>> q133 : ${q133}"
        // println ">>> u150 : ${u150}"

        def bg155 = Calculo.instance.redondear((q133*(bg151-u150))/(bg151*(q133-u150))*100 , 3)
        
        def bg156 = Calculo.instance.redondear(bg154*bg155/100, 3)
        
        def bg157 = Calculo.instance.redondear((bg156/q133)*100, 3)
        def bg158 = Calculo.instance.redondear(bg154-bg156, 3)
        
        def u146 = getSumaValores(70 , 70  , "Sac")
        def bg159 = Calculo.instance.redondear(bg158/u146*100 , 3)
        
        def campoFk = "stockFabrica.diaTrabajo.id"

        def d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle73", campoFk, "tonAzuDis")
        def bg142 = d.valor?:0

        // ------------------------------------
        d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle70", campoFk, "Vt")
        def u144 = d.valor?:0

        d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle70", campoFk, "p")
        def t149 = d.valor?:0
        
        d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle71", campoFk, "Vt")
        def u155 = d.valor?:0

        d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle71", campoFk, "p")
        def t160 = d.valor?:0

        // =((U144*T149)/1000)+(U155*T160)/1000
        def ax132 = Calculo.instance.redondear(((u144*t149)/1000)+(u155*t160)/1000, 3)
        
        def diaTrabajo = SqlUtil.instance.getDiaTrabajo(Aux.instance.diaTrabajoId)
        def diaFin = diaTrabajo.numeroDia - 1
        def ax135 = SqlUtil.instance.getValMatBlcAcu("mielFM", diaFin)

        def ax129 = 7.86
        def ax140 = ax132 -ax135 + ax129

        println ""
        println ">>> ax129: ${ax129}"
        println ">>> ax132: ${ax132}"
        println ">>> ax135: ${ax135}"
        println ">>> ax140: ${ax140}"
        println ">>> bg142: ${bg142}"
        println ">>> bg144: ${bg144}"
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
        def campoFk = "stockFabrica.diaTrabajo.id"
        def d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle1", campoFk, "TonSacJDil")
        if (d)
            println ">>> Indicador: ${d.indicador.descripcion}, valor: ${d.valor}"
    }

    void getDetallePorIndicador(){
        def modulo  = "StockFabricaDetalle2"
        def campoFk = "stockFabrica.diaTrabajo.id"
        def campo   = "porcN"
        def d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, modulo, campoFk, campo)

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
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jrBri")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jrSac")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jrPur")

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

        println SqlUtil.instance.getValorDetalleCampo(Aux.instance.diaTrabajoId, "stockProceso", "StockProcesoDetalle1" , "1")

        def d = SqlUtil.instance.getDetallePorDTM(Aux.instance.diaTrabajoId, "stockProceso", "StockProcesoDetalle1", "tanJugDil")
        if (d) println ">>> ${d.factor}"
    }

    def getCadena(def s1, def s2, def s3){
        // https://dzone.com/articles/java-string-format-examples
        return String.format("%12s|"*3, s1, s2, s3)
    }
    void getValoresBlc(){
        def (cdV, cdA, amV, amA, jdV, jdC, jdA, bcV, bcC, bcA, bdV, bdC, cV, cC, cA)     = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
        def (mfV, mfC, mfA, abV, abC, abA, cnV, cnA, jnV, jnC, jnA, mV , mC, mA, hV, hA) = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
        def (sdV, sdC, sdA) = [0,0,0]

        println "\n>>> BLC"
        
        def diaFin = 6
        cdA = SqlUtil.instance.getValMatBlcAcu("canaDia"    , diaFin)
        amA = SqlUtil.instance.getValMatBlcAcu("aguaM"      , diaFin)
        jdA = SqlUtil.instance.getValMatBlcAcu("jDiluidoBr" , diaFin)
        bcA = SqlUtil.instance.getValMatBlcAcu("bagazoC"    , diaFin)
        cA  = SqlUtil.instance.getValMatBlcAcu("cachaza"    , diaFin)
        mfA = SqlUtil.instance.getValMatBlcAcu("mielFM"     , diaFin)
        abA = SqlUtil.instance.getValMatBlcAcu("azucarB"    , diaFin)
        cnA = SqlUtil.instance.getValMatBlcAcu("canaNeta"   , diaFin)
        jnA = SqlUtil.instance.getValMatBlcAcu("jugoNeto"   , diaFin)
        mA  = SqlUtil.instance.getValMatBlcAcu("meladura"   , diaFin)
        hA  = SqlUtil.instance.getValMatBlcAcu("hojaCana"   , diaFin)
        sdA = SqlUtil.instance.getValMatBlcAcu("sacosD"     , diaFin)
       
        // Cálculos específicos
        cdV = 1259.19

        def d = SqlUtil.instance.getDetallePorDTM(Aux.instance.diaTrabajoId, "stockProceso", "StockProcesoDetalle2", "aguaM")
        if (d) amV = d.peso

        d = SqlUtil.instance.getDetallePorDTM(Aux.instance.diaTrabajoId, "stockProceso", "StockProcesoDetalle2", "jDiluidoBr")
        if (d) {
            jdV = d.volumen2
            jdC = d.peso
        }

        cnV = cdV - hV
        
        bcV = cnV + amV - jdC
        bcC = cdV ? Calculo.instance.redondear(bcV/cdV*100, 2): 0

        bdV = 292
        bdC = cdV ? Calculo.instance.redondear(bdV*100/cdV, 2): 0

        cV  = 35.93
        cC  = cdV ? Calculo.instance.redondear(cV*100/cdV, 2): 0
                    
        def solInsol = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H", "porcInso")
        jnV = Calculo.instance.redondear(jdC - (jdC*solInsol/100), 2)
        jnC = cdV ? Calculo.instance.redondear(jnV*100/cdV, 2): 0
        
        // =(F10-(F10*D49/100))-((F10-(F10*D49/100))*(1-(H53/G200)))
        def jcBri   = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jcBri")
        def mcrBri2 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Meladura", "mcrBri2")
        mV          = mcrBri2 ? Calculo.instance.redondear( (jdC-(jdC*solInsol/100))-((jdC-(jdC*solInsol/100))*(1-(jcBri/mcrBri2))) , 2): 0
        mC          = cdV ? Calculo.instance.redondear(mV*100/cdV, 2): 0
        
        d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle73", "stockFabrica.diaTrabajo.id", "tonAzuDis")
        sdC = d.valor?:0
        sdV = Calculo.instance.redondear(sdC*20,2)
        
        d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle73", "stockFabrica.diaTrabajo.id", "tonMelProTotDia")
        mfV = d.valor?:0
        mfC = cdV ? Calculo.instance.redondear(mfV*100/cdV, 2): 0
        
        abV = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Blc" , "qqTotalesDia")
        abC = Calculo.instance.redondear(abV/20, 2)

        println "CAÑA/DIA           |" + getCadena(cdV , 0   , cdA)
        println "AGUA MACERACION    |" + getCadena(amV , 0   , amA)
        println "JUGO DILUIDO (BR)  |" + getCadena(jdV , jdC , jdA)
        println "BAGAZO (CALCULADO) |" + getCadena(bcV , bcC , bcA)
        println "BAGAZO (DIRECTO)   |" + getCadena(bdV , bdC , 0)
        println "CACHAZA            |" + getCadena(cV  , cC  , cA)
        println "MIEL FINAL MELAZA  |" + getCadena(mfV , mfC , mfA)
        println "AZUCAR BLANCA      |" + getCadena(abV , abC , abA)
        println "CAÑA NETA          |" + getCadena(cnV , 0   , cnA)
        println "JUGO NETO          |" + getCadena(jnV , jnC , jnA)
        println "MELADURA           |" + getCadena(mV  , mC  , mA)
        println "HOJA DE CAÑA       |" + getCadena(hV  , 0   , hA)
        println "SACOS DISUELTOS    |" + getCadena(sdV , sdC , sdA)

        // setValores("canaDia", cdV, 0, cdV + cdA)

        println "\nVARIABLES PRIMARIAS:\n"
        //Método Balance
        // =+($D$17*H48+$D$11*H44)/$D$16
        
        def d16 = cnV
        def d17 = jnV
        def h48 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jdBri")
        def d11 = bcV
        def h44 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "brix")
        def d42 = d16 ? Calculo.instance.redondear((d17*h48 + d11*h44)/d16, 2): 0 //brix cana

        def h49 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jdSac")
        // =+($D$17*H49+$D$11*H41)/$D$16
        def h41 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "porcSacarosa")
        def d43 = d16 ? Calculo.instance.redondear((d17*h49 + d11*h41)/d16, 2): 0 //sac cana

        def d44 = d42 ? Calculo.instance.redondear(d43/d42*100, 2): 0

        // =+D11*H42/D16
        def h42 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "porcFibra")
        def d45 = d16 ? Calculo.instance.redondear(d11*h42/d16, 2): 0

        // =+H49*F10/D8
        def d8  = cdV
        def d9  = amV 
        def f10 = jdC
        def d46 = d8 ? Calculo.instance.redondear(h49*f10/d8, 2): 0

        println "BRIX CAÑA          |" + d42
        println "SAC CAÑA           |" + d43
        println "PZA CAÑA           |" + d44
        println "FIBRA CAÑA         |" + d45
        println "SAC JUG BR CANA    |" + d46
        
        def d51 = d8 ? Calculo.instance.redondear(f10*100/d8, 2): 0
        println "JUGO DILUIDO (BR)  |" + d51

        println "\nCALCULOS DE FABRICA\n"

        def d49  = solInsol
        def h60  = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jrPur")
        def h43  = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "porcHumedad")
        def s16  = Calculo.instance.redondear((h41/h60)*100, 2)
        def s17  = 100-h43-s16
        def d13  = cV
        def l48  = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Turbiedad", "polCachaza")
        def d14  = mfV
        def l42  = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Mieles" , "mfSac")
        def f15  = abC
        def l55  = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "AzucarGranel", "pol")
        
        def h63A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacJDil"   , diaFin)
        def h64A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacCan"    , diaFin)
        def h65A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSolIns"    , diaFin)
        def h66A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonFibBag"    , diaFin)
        def h67A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonFibCan"    , diaFin)
        def h68A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacBag"    , diaFin)
        def h69A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacCac"    , diaFin)
        def h70A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacMieFin" , diaFin)
        def h71A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacAzuHec" , diaFin)

        def h63 = Calculo.instance.redondear(d17*(h49/100), 2)
        def h68 = Calculo.instance.redondear(d11*(h41/100), 2)
        def h64 = h63 + h68
        def h65 = Calculo.instance.redondear(f10*(d49/100), 2)
        def h66 = Calculo.instance.redondear(d11*(s17/100), 2)
        def h67 = h65 + h66
        def h69 = Calculo.instance.redondear(d13*l48/100, 2)
        def h70 = Calculo.instance.redondear(d14*l42/100, 2)
        def h71 = Calculo.instance.redondear(f15*l55/100, 2)

        def k72 = Calculo.instance.redondear((h63/h64)*100, 2)

        def s24 = Calculo.instance.redondear(h67/d16*100, 2)
        def s49 = s24 ? Calculo.instance.redondear( (100-k72)*(100-s24)/s24, 2): 0
        def k73 = 100 - Calculo.instance.redondear(s49/7, 2)
        def k74 = h67 ? Calculo.instance.redondear(d9/h67*100, 2): 0
        def k75 = d16 ? Calculo.instance.redondear(d9/d16*100, 2): 0

        def k76 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "gradosAguaMac") // ='Stock Proceso'!H69 BAGAZO!P30
        def k77 = SqlUtil.instance.getCampo(Aux.instance.diaTrabajoId, "StockProceso" , "tonSac")  // ='Stock Proceso'!L67
        def k78 = 0 // ='BLC Cenicaña'!D59

        println "Toneladas Sacarosa Jugo Diluido        | " + getCadena(h63, h63A, h63+h63A)
        println "Toneladas Sacarosa Caña                | " + getCadena(h64, h64A, h64+h64A)
        println "Toneladas Solidos Insolubles           | " + getCadena(h65, h65A, h65+h65A)
        println "Toneladas Fibra en Bagazo              | " + getCadena(h66, h66A, h66+h66A)
        println "Toneladas Fibra en Caña                | " + getCadena(h67, h67A, h67+h67A)
        println "Toneladas Sacarosa Bagazo              | " + getCadena(h68, h68A, h68+h68A)
        println "Toneladas Sacarosa Cachaza             | " + getCadena(h69, h69A, h69+h69A)
        println "Toneladas Sacarosa Miel Final - Melaza | " + getCadena(h70, h70A, h70+h70A)
        println "Toneladas Sacarosa Azúcar Hecha        | " + getCadena(h71, h71A, h71+h71A)
        println "Extraccion Pol                         | " + getCadena(0, k72, 0)
        println "Extraccion Reducida                    | " + getCadena(0, k73, 0)
        println "Maceración % Fibra                     | " + getCadena(0, k74, 0)
        println "Maceración % Caña                      | " + getCadena(0, k75, 0)
        println "Temperatura agua de Maceracion         | " + getCadena(0, k76, 0)
        println "Ton  Estimadas - Stock de Fabrica      | " + getCadena(0, 0, k77)
        println "Recuperación Teorica SJM Material de Proceso | " + getCadena(0, 0, k77)
    }
    
    void setValores(String campo, def val, def can, def acu){
        def d = SqlUtil.instance.getDetallePorDTM(Aux.instance.diaTrabajoId, "blc", "BlcDetalle1", campo)
        d.setValor(val)
        d.setCantidad(can)
        d.setAcumulado(acu)
        getManager().persist(d)
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
