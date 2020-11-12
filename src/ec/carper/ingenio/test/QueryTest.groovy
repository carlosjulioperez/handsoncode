package ec.carper.ingenio.test
 
import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import groovy.time.TimeCategory
import java.text.SimpleDateFormat
import java.time.format.*
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
        // getPrueba()
        // crearItemsPorHora(Aux.instance.diaTrabajoId)
        getValorCampo()
        // getValoresBlc()
        //getValoresServiciosInsumosFabrica()
        //getValoresAnalisisRutinariosEspecialesFabrica()
        //getValoresBlcCenicana()

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
        // getDiaTrabajo()
        //isDiaTrabajoCerrado()
        //getTrashCanaDiaTrabajoCerrado()
        //getTrashCanaDetalle2()
        //getNativo()
    }

    def crearItemsPorHora(def diaTrabajoId){
        def d     = SqlUtil.instance.getDiaTrabajo(diaTrabajoId)
        // def hora1 = d.turnoTrabajo.horaDesde
        // def hora2 = d.turnoTrabajo.horaHasta

        def hora  = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaDesde, diaTrabajoId)
        def horaF = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaHasta, diaTrabajoId)

        while(hora < horaF ) {
            
            // // https://stackoverflow.com/questions/3504986/extract-time-from-date-string
            // Date date = new Date();
            // date.setTime(hora.getTime());
            // def strHora = new SimpleDateFormat("HH:mm").format(date);
            
            def strHora = Util.instance.getHoraS(hora)
            println ">>> strHora: ${strHora}"
            println ">>> Hora   : ${hora}"

            // Incremento de hora
            hora = Util.instance.agregarHora(hora)
        }
        println horaF
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
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Turbiedad", "briCachaza")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Turbiedad", "humCachaza")
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H",    "porcConTotLinEva")
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
        
        def diaFin = 2-1
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
        def h50 = SqlUtil.instance.getValorCampoBlc(Aux.instance.diaTrabajoId, "BlcDetalle5" , "pzaJDil")
        def d11 = bcV
        def h44 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Bagazo" , "brix")
        def d42 = d16 ? Calculo.instance.redondear((d17*h48 + d11*h44)/d16, 2): 0 //brix cana
        def l14 = getValorBlc("mielFM", 3)

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

        // SECCION MIEL FINA MELAZA
        d = SqlUtil.instance.getDetallePorDTI(Aux.instance.diaTrabajoId, "blc", "BlcDetalle12", "KilMieTonCan")
        def k822 = d ? (d.totalZafra?:0): 0

        def mfBri2 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Mieles"       , "mfBri2")
        def l41 = mfBri2
        // =(K82/(BUSCAR(L41,'Brix y Densidad'!A3:B953)))*(1000/3.785)
        def p   = new BrixDensidadWp().getP(l41)
        def l46 = p ? Calculo.instance.redondear( (k822/p)*(1000/3.785), 2): 0

        println "\n"
        println ">>> l41: ${l41}"
        println ">>> p  : ${p}"
        println "Gal/TCM            |" + l46

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
        def k81A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacRec"    , diaFin)

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
        def k77 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "StockProceso" , "tonSac")  // ='Stock Proceso'!L67
        def k78 = SqlUtil.instance.getValorBlcCenicana(Aux.instance.diaTrabajoId, 73)   // ='BLC Cenicaña'!D59
        def k79 = k78 ? Calculo.instance.redondear(k77*k78/100, 2): 0
    
        // k81 = 'Stock Fabrica'!AP152
        
        // =K79+(F15*L55/100)-K81
        def k80  = k79 + Calculo.instance.redondear(f15*l55/100, 2 ) - k81A
        def k84  = l14
        d        = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle73", "stockFabrica.diaTrabajo.id", "tonMelProTotDiaAnt")
        def k85  = d.valor?:0
        def k86  = k84-k85
        def k83  = Calculo.instance.redondear(k86*l42/100, 2)
        def k82  = (d16*(l42/100))!= 0 ? Calculo.instance.redondear((k83*1000)/(d16*(l42/100)), 2): 0 
        //       = (100*'BLC Cenicaña'!D57*(H50-L43))/(H50*('BLC Cenicaña'!D57-L43))
        def k87  = SqlUtil.instance.getValorBlcCenicana(Aux.instance.diaTrabajoId, 60)   //                                                                        = 'BLC Cenicaña'!D59
        def k88  = h50 ? Calculo.instance.redondear((1.4-(40/h50))*100 ,2): 0
        def k89  = k87 ? Calculo.instance.redondear(k80/k87, 2): 0
        def k100 = Calculo.instance.redondear((d16*d43/100)*20, 2)
        def k101 = k100 ? Calculo.instance.redondear( ((k80*20)/k100)*100, 2): 0
        def k90  = k88 ? Calculo.instance.redondear(k101/k88, 2): 0
        def k91  = l55 ? Calculo.instance.redondear((d17*h49/100)*k87*k89*(1/l55), 2): 0
        def k92  = d16 ? Calculo.instance.redondear(k91/d16*100, 2): 0
        def k93  = d16 ? Calculo.instance.redondear(k80/d16*100, 2): 0
        def k94  = d43 - k93
        def k95  = Calculo.instance.redondear(d16*k94/100, 2)
        def i96  = h68
        def l96  = d16 ? Calculo.instance.redondear(i96/d16*100, 2): 0
        def i97  = h69 + h70
        def l97  = d16 ? Calculo.instance.redondear(i97/d16*100, 2): 0
        def l98  = k94 - l97 - l96
        def i98  = Calculo.instance.redondear(d16*l98/100, 2)
        def i99  = i98 + i97
        def l99  = l98 + l97
        def d15  = getValorBlc("azucarB", 1)
        def k102 = k100 ? Calculo.instance.redondear(d15*100/k100, 2): 0
        def k103 = 0 //Temporalmente ingresado desde BlcAdmin
        def k104 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "StockProceso" , "sacarosaSilos")  // ='Stock Proceso'!L68
        def k105 = SqlUtil.instance.getValorBlcCenicana(Aux.instance.diaTrabajoId, 98)   // ='BLC Cenicaña'!H72

        // Calculo.instance.redondear(, 2 ): 0
        println "Toneladas Sacarosa Jugo Diluido              | " + getCadena(h63, h63A, h63+h63A)
        println "Toneladas Sacarosa Caña                      | " + getCadena(h64, h64A, h64+h64A)
        println "Toneladas Solidos Insolubles                 | " + getCadena(h65, h65A, h65+h65A)
        println "Toneladas Fibra en Bagazo                    | " + getCadena(h66, h66A, h66+h66A)
        println "Toneladas Fibra en Caña                      | " + getCadena(h67, h67A, h67+h67A)
        println "Toneladas Sacarosa Bagazo                    | " + getCadena(h68, h68A, h68+h68A)
        println "Toneladas Sacarosa Cachaza                   | " + getCadena(h69, h69A, h69+h69A)
        println "Toneladas Sacarosa Miel Final - Melaza       | " + getCadena(h70, h70A, h70+h70A)
        println "Toneladas Sacarosa Azúcar Hecha              | " + getCadena(h71, h71A, h71+h71A)
        println "Extraccion Pol                               | " + getCadena(0, k72, 0)
        println "Extraccion Reducida                          | " + getCadena(0, k73, 0)
        println "Maceración % Fibra                           | " + getCadena(0, k74, 0)
        println "Maceración % Caña                            | " + getCadena(0, k75, 0)
        println "Temperatura agua de Maceracion               | " + getCadena(0, k76, 0)
        println "Ton Estimadas - Stock de Fabrica             | " + getCadena(0, 0, k77)
        println "Recuperación Teorica SJM Material de Proceso | " + getCadena(0, k78, 0)
        println "Toneladas de sac Recuperable en azucar       | " + getCadena(0, 0, k79)
        println "Ton Azúcar Hecha y Estimada                  | " + getCadena(0, k80, 0)
        println "Stock de Día Anterior                        | " + getCadena(0, 0, k81A)
        println "Kilos Miel/Ton Caña                          | " + getCadena(0, 0, k82)
        println "Ton Sacaroa en Miel Final H y E              | " + getCadena(0, 0, k83)
        println "Ton Tot Estimada Miel Final Hoy              | " + getCadena(0, 0, k84)
        println "Stock Tot Día Anterior                       | " + getCadena(0, 0, k85)
        println "Miel Estimada                                | " + getCadena(0, 0, k86)
        println "Recuperación Teórica S.J.M                   | " + getCadena(0, k87, 0)
        println "Recuperación Teórica Winter                  | " + getCadena(0, k88, 0)
        println "Eficiencia S.J.M                             | " + getCadena(0, k89, 0)
        println "Eficiencia Winter                            | " + getCadena(0, k90, 0)
        println "Toneladas Azúcar Recuperable                 | " + getCadena(0, k91, 0)
        println "Rendimiento Teórico                          | " + getCadena(0, k92, 0)
        println "RENDIMIENTO                                  | " + getCadena(0, k93, 0)
        println "Pérdidas Totales % Caña                      | " + getCadena(0, k94, 0)
        println "Ton Perdidas Totales                         | " + getCadena(0, k95, 0)
        println "Pérdidas Molinos                             | " + getCadena(i96, 0, l96)
        println "Pérdidas Cachaza y Miel Final                | " + getCadena(i97, 0, l97)
        println "Pérdidas Indeterminadas Elaboración y Molinos| " + getCadena(i98, 0, l98)
        println "Pérdidas Elaboración y Molinos               | " + getCadena(i99, 0, l99)
        println "QQ Esperados Teóricos                        | " + getCadena(0, k100, 0)
        println "Recuperación Tot Sacarosa % Caña             | " + getCadena(0, k101, 0)
        println "Recuperación Comercial                       | " + getCadena(0, k102, 0)
        println "Sacarosa en Silos Día Anterior               | " + getCadena(0, k103, 0)
        println "Sacarosa en Silos Hoy                        | " + getCadena(0, k104, 0)
        println "Eficiencia de Elaboración                    | " + getCadena(0, k105, 0)
    }

    void getValoresServiciosInsumosFabrica(){
        println "\nCONSUMOS - SERVICIOS E INSUMOS FABRICA\n"
    
        println " orden | id  |             descripcion              |    campo     |  unidades   | acumulado   | totalZafra  | "
        println "-------+-----+--------------------------------------+--------------+-------------+-------------+-------------+"
        println "     1 | 133 | Consumo Diesel (Gal)                 | ConDieGal    | "
        println "     2 | 134 | Consumo de Energía                   | ConEne       | "
        println "     3 | 135 | Generación de Energía                | GenEne       | "
        println "     4 | 136 | Generación Agua de Alimentación      | GenAguAli    | "
        println "     5 | 138 | Generación de Vapor Ton              | GenVapTon    | "
        println "     6 | 139 | CALDERA                              | CAL          | "
        println "     7 | 140 | INTER-417                            | Int147       | "
        println "     8 | 141 | INTER-421                            | Int421       | "
        println "     9 | 142 | INTER-506                            | Int506       | "
        println "    10 | 143 | BOILER CONTROL                       | BoiCon       | "
        println "    11 | 144 | Soda                                 | Sod          | "
        println "    12 | 145 | OR DM PLANT                          | OrDmPla      | "
        println "    13 | 146 | INTER-413                            | Int413       | "
        println "    14 | 147 | INTER-753                            | Int753       | "
        println "    15 | 148 | Hipoclorito de Sodio                 | HipSod       | "
        println "    16 | 149 | Filtro de Cartuchos 5 mm             | FilCar5MM    | "
        println "    17 | 150 | Filtro de Cartuchos 30 mm            | FilCar30MM   | "
        println "    18 | 151 | CLARIFICADOR                         | CLA          | "
        println "    19 | 152 | Inter Quipac                         | IntQui       | "
        println "    20 | 153 | Floculante C                         | FloC         | "
        println "    21 | 148 | Hipoclorito de Sodio                 | HipSod       | "
        println "    22 | 154 | Cal                                  | Cal          | "
        println "    23 | 155 | REGENERACION DE RESINAS              | REGRES       | "
        println "    24 | 156 | Carbonato de Calcio                  | CarCal       | "
        println "    25 | 157 | Soda Cáustica                        | SodCau       | "
        println "    26 | 158 | LIMPIEZA Y REGENERACION DM PLANTA    | LIMREGDMPLA  | "
        println "    27 | 159 | INTER-875                            | Int875       | "
        println "    28 | 160 | INTER-886                            | Int886       | "
        println "    29 | 161 | INTER-846                            | Int846       | "
        println "    30 | 162 | TORRE ENFRIAMIENTO TURBO             | TORENFTUR    | "
        println "    31 | 163 | INTER-746                            | Int746       | "
        println "    32 | 164 | INTER-822                            | Int822       | "
        println "    33 | 165 | EVAPORADORES                         | EVA          | "
        println "    34 | 144 | Soda                                 | Sod          | "
        println "    35 | 166 | CLARIFICADOR DE JUGO                 | CLAJUG       | "
        println "    36 | 167 | Floculante                           | Flo          | "
        println "    37 | 168 | Azufre                               | Azu          | "
        println "    38 | 154 | Cal                                  | Cal          | "
        println "    39 | 169 | CLARIFICACIÓN DE MELADURA            | CLAMEL       | "
        println "    40 | 170 | PROFLOC DI                           | ProDi        | "
        println "    41 | 171 | Ácido Fosfórico                      | AciFos       | "
        println "    42 | 172 | Proquat SC 970                       | ProSc970     | "
        println "    43 | 167 | Floculante                           | Flo          | "
        println "    44 | 173 | TACHOS                               | TAC          | "
        println "    45 | 172 | Proquat SC 970                       | ProSc970     | "
        println "    46 | 174 | Alcohol                              | Alc          | "
        println "    47 | 175 | Semilla Fondant                      | SemFon       | "
        println "    48 | 176 | Propeg 606                           | Pro606       | "
        println "    49 | 177 | MOLINOS                              | MOL          | "
        println "    50 | 178 | Proquat BC 50                        | ProBC50      | "
        println "    51 | 179 | Peocide BC 800                       | PeoBC800     | "
        println "    52 | 180 | JUGO DILUIDO                         | JUGDIL       | "
        println "    53 | 172 | Proquat SC 970                       | ProSc970     | "
        println "    54 | 181 | Profloc DI                           | ProDI        | "
        println "    55 | 182 | Profosf 550                          | Pro550       | "
        println "    56 | 183 | CONSUMO DE AGUA CALIENTE ELABORACION | CONAGUCALELA | "
        println "    57 | 184 | Tachos                               | Tac          | "
        println "    58 | 185 | Filtro de Cachaza                    | FilCac       | "
        println "    59 | 186 | Fundido                              | Fun          | "
        println "    60 | 187 | Centrífuga Bach 1                    | CenBac1      | "
        println "    61 | 188 | Centrífuga Bach 2                    | CenBac2      | "
        println "    62 | 189 | Centrífuga Continua 1 de B           | CenCon1B     | "
        println "    63 | 190 | Centrífuga Continua 2 de B           | CenCon2B     | "
        println "    64 | 191 | Centrífuga Continua 3 de C           | CenCon3C     | "
        println "    65 | 192 | Centrífuga Continua 4 de C           | CenCon4C     | "
    
    }
    
    def getSacTeoRed(def g, i){
        return Calculo.instance.redondear((i-g)*0.95, 2)
    }

    def getValoresAnalisisRutinariosEspecialesFabrica(){
        println "\nANALISIS RUTINARIOS Y ESPECIALES FABRICA\n"

        def g176 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "TrashCana" , "avgPorcAzuRed")
        def g177 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "j1Extracto2")
        def g178 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "jDiluido2")
        def g179 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "jClaro2")
        def g180 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "jFiltrado2")
        def g181 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "mClara2")
        def g182 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "mielA2")
        def g183 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "mielB2")
        def g184 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "mielF2")

        def i176 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "cana")
        def i177 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "j1Extracto")
        def i178 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "jDiluido")
        def i179 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "jClaro")
        def i180 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "jFiltrado")
        def i181 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "mClara")
        def i182 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "mielA")
        def i183 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "mielB")
        def i184 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "mielF")

        def g187 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "j1Extracto3")
        def g188 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "jDiluido3")
        def g191 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "jClaro3")
        def g193 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "jFiltrado3")
        def g195 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "mClara3")
        def g196 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Cto24H" , "mielA3")

        def i187 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Ph" , "j1Extracto")
        def i188 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Ph" , "jDiluido")
        def i190 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Ph" , "jEncalado")
        def i191 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Ph" , "jClaro")
        def i193 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Ph" , "jFiltrado")
        def i194 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Ph" , "mCruda")
        def i195 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Ph" , "mClarificada")

        def k188 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "ColorMat" , "avgColor1")
        def l188 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "ColorMat" , "avgTurb1")
        def k191 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "ColorMat" , "avgColor2")
        def l191 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "ColorMat" , "avgTurb2")
        def k194 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "ColorMat" , "avgColor3")
        def l194 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "ColorMat" , "avgTurb3")
        def k195 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "ColorMat" , "avgColor4")
        def l195 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "ColorMat" , "avgTurb4")
        def k196 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "ColorMat" , "avgColor5")
        def l196 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "ColorMat" , "avgTurb5")

        def proCol = k194 ? Calculo.instance.redondear( ((k194-k195)/k194)*100, 2): 0
        def proMat = l194 ? Calculo.instance.redondear( ((l194-l195)/l194)*100, 2): 0

        println " orden | id  |    descripcion    | campo  | "
        println "-------+-----+-------------------+--------+ "
        println "     1 | 125 | CAÑA              | can    | " + getCadena (g176, i176, getSacTeoRed(g176, i176) )
        println "     2 | 126 | J. 1 EXTRAC       | j1Ext  | " + getCadena (g177, i177, getSacTeoRed(g177, i177) ) 
        println "     3 | 127 | J. DILUIDO        | jDil   | " + getCadena (g178, i178, getSacTeoRed(g178, i178) ) 
        println "     4 | 128 | J. CLARO          | jCla   | " + getCadena (g179, i179, getSacTeoRed(g179, i179) ) 
        println "     5 | 129 | J. FILTRADO       | jFil   | " + getCadena (g180, i180, getSacTeoRed(g180, i180) ) 
        println "     6 | 130 | M. CLARA          | mCla   | " + getCadena (g181, i181, getSacTeoRed(g181, i181) ) 
        println "     7 | 131 | MIEL A            | mieA   | " + getCadena (g182, i182, getSacTeoRed(g182, i182) ) 
        println "     8 | 132 | MIEL B            | mieB   | " + getCadena (g183, i183, getSacTeoRed(g183, i183) ) 
        println "     9 | 007 | MIEL FINAL MELAZA | mielFM | " + getCadena (g184, i184, getSacTeoRed(g184, i184) ) 
    
        println " orden | id  |     descripcion      |  campo  | " 
        println "-------+-----+----------------------+---------+ "
        println "     1 | 126 | J. 1 EXTRAC          | j1Ext   | " + getCadena (g187, i187, 0 )
        println "     2 | 133 | JUGO DILUIDO DIA     | jDilDia | " + getCadena (g188, i188, k188+' '+l188 )
        println "     3 | 134 | JUGO ENCALADO        | jEnc    | " + getCadena (0, i190, 0 )
        println "     4 | 135 | JUGO CLARO DIA       | jClaDia | " + getCadena (g191, i191, k191+' '+l191 )
        println "     5 | 129 | J. FILTRADO          | jFil    | " + getCadena (g193, i193, 0)
        println "     6 | 136 | MELADURA CRUDA       | melCru  | " + getCadena (0, i194, k194+' '+l194 )
        println "     7 | 137 | MELADURA CLARIFICADA | melCla  | " + getCadena (g195, i195, k195+' '+l195 )
        println "     8 | 131 | MIEL A               | mieA    | " + getCadena (g196, 0, k196+' '+l196 )
        println "REMOCION ESTACION DE CLARIFICACION DE MELADURA| " + getCadena (0, 0, proCol+' '+proMat)    

        def g199 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Jugo"         , "jfBri"   )
        def i199 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Jugo"         , "jfSac"   )
        def l199 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Jugo"         , "jfPur"   )

        def g200 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Meladura"     , "mcrBri2" )
        def i200 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Meladura"     , "mcrSac" )
        def l200 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Meladura"     , "mcrPur" )

        def g201 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Meladura"     , "mclBri2" )
        def i201 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Meladura"     , "mclSac" )
        def l201 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Meladura"     , "mclPur" )

        def g202 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Masas"        , "maBri2"  )
        def i202 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Masas"        , "maSac"  )
        def l202 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Masas"        , "maPur"  )

        def g203 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Masas"        , "mbBri2"  )
        def i203 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Masas"        , "mbSac"  )
        def l203 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Masas"        , "mbPur"  )

        def g204 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Masas"        , "mcBri2"  )
        def i204 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Masas"        , "mcSac"  )
        def l204 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Masas"        , "mcPur"  )

        def g205 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Mieles"       , "maBri2"  )
        def i205 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Mieles"       , "maSac"  )
        def l205 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Mieles"       , "maPur"  )

        def g206 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Mieles"       , "mbBri2"  )
        def i206 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Mieles"       , "mbSac"  )
        def l206 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Mieles"       , "mbPur"  )

        def g207 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Mieles"       , "mrBri2"  )
        def i207 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Mieles"       , "mrSac"  )
        def l207 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Mieles"       , "mrPur"  )

        def g208 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "MielesNutsch" , "maBri2"  )
        def i208 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "MielesNutsch" , "maSac"  )
        def l208 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "MielesNutsch" , "maPur"  )

        def g209 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "MielesNutsch" , "mbBri2"  )
        def i209 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "MielesNutsch" , "mbSac"  )
        def l209 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "MielesNutsch" , "mbPur"  )

        def g210 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "MielesNutsch" , "mcBri2"  )
        def i210 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "MielesNutsch" , "mcSac"  )
        def l210 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "MielesNutsch" , "mcPur"  )

        def g211 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Magmas"       , "mbBri2"  )
        def i211 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Magmas"       , "mbSac"  )
        def l211 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Magmas"       , "mbPur"  )

        def g212 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Magmas"       , "mcBri2"  )
        def i212 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Magmas"       , "mcSac"  )
        def l212 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Magmas"       , "mcPur"  )

        def g213 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Magmas"       , "mrBri2"  )
        def i213 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Magmas"       , "mrSac"  )
        def l213 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Magmas"       , "mrPur"  )

        def g214 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "TqFundidor"   , "bri2"    )
        def i214 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "TqFundidor"   , "sac"    )
        def l214 = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "TqFundidor"   , "pur"    )

        println ""
        println " orden | id  |     descripcion      |  campo  | " 
        println "-------+-----+----------------------+---------+ "
        println "     1 | 129 | J. FILTRADO          | jFil    | " + getCadena(g199, i199, l199)
        println "     2 | 136 | MELADURA CRUDA       | melCru  | " + getCadena(g200, i200, l200) 
        println "     3 | 137 | MELADURA CLARIFICADA | melCla  | " + getCadena(g201, i201, l201) 
        println "     4 | 138 | MASA A               | masA    | " + getCadena(g202, i202, l202) 
        println "     5 | 139 | MASA B               | masB    | " + getCadena(g203, i203, l203) 
        println "     6 | 140 | MASA C               | masC    | " + getCadena(g204, i204, l204) 
        println "     7 | 131 | MIEL A               | mieA    | " + getCadena(g205, i205, l205) 
        println "     8 | 132 | MIEL B               | mieB    | " + getCadena(g206, i206, l206) 
        println "     9 | 141 | MIEL RE-PURGA        | mieRP   | " + getCadena(g207, i207, l207) 
        println "    10 | 142 | MIEL NUTSCH A        | mieNutA | " + getCadena(g208, i208, l208) 
        println "    11 | 143 | MIEL NUTSCH B        | mieNutB | " + getCadena(g209, i209, l209) 
        println "    12 | 144 | MIEL NUTSCH C        | mieNutC | " + getCadena(g210, i210, l210) 
        println "    13 | 145 | MAGMA B              | magB    | " + getCadena(g211, i211, l211) 
        println "    14 | 146 | MAGMA C              | magC    | " + getCadena(g212, i212, l212) 
        println "    15 | 147 | MAGMA RE-PURGA       | magRP   | " + getCadena(g213, i213, l213) 
        println "    16 | 148 | FUNDIDO              | fun     | " + getCadena(g214, i214, l214) 

    }

    def getValorBlc(def campo, def col){
        def d = SqlUtil.instance.getDetallePorDTM(Aux.instance.diaTrabajoId, "blc", "BlcDetalle1", campo)
        return col==1 ? (d.valor?:0): ( col==2 ? (d.cantidad?:0) : (d.acumulado?:0) )
    }
    
    void getValoresBlcCenicana(){
        println "\nVALORES BLC CENICANA\n"
        
        def diaFin    = 6
        def mfSac     = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Mieles"       , "mfSac")
        def pol       = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "AzucarGranel" , "pol")
        def hum       = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Grasshoper"   , "humedad")
        def mfBri2    = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Mieles"       , "mfBri2")
        def polReproc = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "AzucarGranel" , "polReproc")
        def solInsol  = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Cto24H"       , "porcInso")
        def jrPur     = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Jugo"         , "jrPur")
        def porcSac   = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Bagazo"       , "porcSacarosa")
        def porcHum   = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Bagazo"       , "porcHumedad")
        def porcFib   = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Bagazo"       , "porcFibra")
        def jdSac     = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Jugo"         , "jdSac")
        def brixJDil  = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Jugo"         , "jdBri")
        def polCac    = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "Turbiedad"    , "polCachaza")
        def tonSac    = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "StockProceso" , "tonSac")  // ='SP'!L67
        def pureza    = SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId , "StockProceso" , "pureza")  // ='SP'!M67
        
        // def d16 = SqlUtil.instance.getDetallePorDTM(Aux.instance.diaTrabajoId, "blc", "BlcDetalle1", "canaNeta")
        def d4  = getValorBlc("canaNeta", 1)
        def d5  = getValorBlc("jDiluidoBr", 2)
        def d6  = getValorBlc("cachaza", 1)
        
        def d   = SqlUtil.instance.getDetallePorDTM(Aux.instance.diaTrabajoId, "stockProceso", "StockProcesoDetalle2", "aguaM")
        def d10 = d ? (d.volumen1?:0): 0

        def d7  = d4 + d10 - d5
        def d8  = getValorBlc("mielFM", 1)
        def d9  = getValorBlc("azucarB", 2)
        
        def h25 = Calculo.instance.redondear(d8*mfSac/100, 2)
        def h27 = d4 ? Calculo.instance.redondear((h25/d4)*100, 2): 0
        def d11 = h27
        def h4  = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacRec" , diaFin)
        
        d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle73", "stockFabrica.diaTrabajo.id", "tonAzuDis")
        def h5  = d ? (d.valor?:0): 0
        def d55 = pol
        def d56 = 100 - hum
        def d57 = d56 ? Calculo.instance.redondear(d55/d56*100, 2): 0
        def h55 = mfSac
        def h56 = mfBri2
        def h57 = h56 ? Calculo.instance.redondear(h55/h56*100, 2): 0
        def h75 = polReproc
        def h76 = (h75*(d57-h57)) ? Calculo.instance.redondear( (d57*(h75-h57))/(h75*(d57-h57))*100, 2): 0
        def h6  = Calculo.instance.redondear(h5*h76/100, 2)
    
        def d16 = Calculo.instance.redondear(d5 * (100 - solInsol)/100, 2)
        def d17 = jrPur ? Calculo.instance.redondear(100*(porcSac/jrPur),2): 0
        def d18 = 100-(d17+porcHum)
        def d19 = (d4+d10)-d5
        def d20 = d4 ? Calculo.instance.redondear(100*(d19/d4),2): 0
        def d21 = Calculo.instance.redondear((d20*d18)/100, 2)
        def d22 = Calculo.instance.redondear((d19*d18)/100, 2)
        def d23 = Calculo.instance.redondear(d5*(solInsol/100), 2)
        def d24 = Calculo.instance.redondear(((d4*d21)/100)+d23, 2)
        def d25 = d4 ? Calculo.instance.redondear(100*(d24/d4),2): 0
        def d26 = d4 ? Calculo.instance.redondear(100*(d10/d4),2): 0
        def d27 = d24 ? Calculo.instance.redondear(100*(d10/d24),2): 0
        def d28 = Calculo.instance.redondear(d19*porcSac/100, 2)
        def d29 = Calculo.instance.redondear(d19*d17/100, 2)
        def d30 = Calculo.instance.redondear(d16*jdSac/100, 2)
        // Q' * Sac R. JD/100
        def d31 = d30 // Calculo.instance.redondear(d16*jdSac/100, 2) // Misma fórmula que d30
        def d32 = Calculo.instance.redondear(d16*brixJDil/100, 2)
        def d33 = d30 + d28
        def d34 = d31 + d28
        def d35 = d4 - d24
        def d36 = d35 ? Calculo.instance.redondear(100*(d33/d35), 2): 0
        def d37 = d35 ? Calculo.instance.redondear((100*d34)/d35, 2): 0
        def d38 = d32 + d29
        
        // =100*((D32+D29)/D35)
        def d39 = d35 ? Calculo.instance.redondear( 100*((d32+d29)/d35), 2): 0
        def h16 = d39 ? Calculo.instance.redondear((100*d36)/d39, 2): 0
        def h17 = d39 ? Calculo.instance.redondear(100*(d32/d39), 2): 0
        def h18 = d4 ? Calculo.instance.redondear(100*(h17/d4), 2): 0
        def h19 = d4 ? Calculo.instance.redondear(100*(d16/d4), 2): 0
        def h20 = d4 ? Calculo.instance.redondear(100*(d33/d4), 2): 0
        def h21 = d4 ? Calculo.instance.redondear((100*d34)/d4, 2): 0
        def h22 = d4 ? Calculo.instance.redondear(100*(d6/d4), 2): 0
        def h23 = Calculo.instance.redondear(d6*polCac/100, 2)
        def h24 = d4 ? Calculo.instance.redondear((h23/d4)*100, 2): 0

        // Y* Sac R. MF/100
        def h26 = h25
        def h28 = d4 ? Calculo.instance.redondear((h26/d4)*100, 2): 0
        
        def d42 = d4 ? Calculo.instance.redondear(100*(d30/d4), 2): 0
        def d43 = d4 ? Calculo.instance.redondear(100*(d28/d4), 2): 0
        def d44 = d4 ? Calculo.instance.redondear(100*(d30/d33), 2): 0
        def d45 = d18 ? Calculo.instance.redondear(100*porcSac/d18, 2): 0
        def h42 = d25 ? Calculo.instance.redondear(((100-d44)*(100-d25))/d25, 2): 0
        def h43 = 100 - Calculo.instance.redondear(h42/7, 2)
        
        def d58 = tonSac
        def h58 = pureza

        // =((D57*(H58-H57))/(H58*(D57-H57)))*100        
        def num = d57 * (h58 - h57) //numerador
        def den = h58 * (d57 - h57) //denominador
        
        println ">> num: ${num}"
        println ">> den: ${den}"
        println ">> d57: ${d57}"
        println ">> h57: ${h57}"
        println ">> h58: ${h58}"

        def d59 = den!=0 ? Calculo.instance.redondear( (num/den)*100, 2): 0
        def h62 = Calculo.instance.redondear(d58*d59/100,2)
        def d66 = Calculo.instance.redondear(d9*d55/100,2)
        def d65 = d66 + h62 - h4
        // def blcH50 = SqlUtil.instance.getValorCampoBlc(Aux.instance.diaTrabajoId, "BlcDetalle5" , "pzaJDil")
        def blcH50 = brixJDil ? Calculo.instance.redondear(jdSac*100/brixJDil,2): 0
        def blcL43 = mfBri2 ? Calculo.instance.redondear(mfSac/mfBri2*100,2): 0

        // =BLC!K87
        // =(100*'BLC Cenicaña'!D57*(H50-L43))/(H50*('BLC Cenicaña'!D57-L43))
        def d48 = (blcH50*(d57-blcL43)) ? Calculo.instance.redondear((100*d57*(blcH50-blcL43))/(blcH50*(d57-blcL43)), 2): 0

        def d49 = blcH50 ? Calculo.instance.redondear(100*(1.4-(40/blcH50)), 2): 0
        def d50 = d4 ? Calculo.instance.redondear(((d16-h17)/d4)*100, 2): 0
        def d51 = d48 ? Calculo.instance.redondear(d65/d48, 2): 0
        def d52 = d55 ? Calculo.instance.redondear(d30*d48*d51*(1/d55), 2): 0

        def h48 = d4 ? Calculo.instance.redondear(d52/d4*100, 2): 0
        def h49 = d4 ? Calculo.instance.redondear(d66/d4*100, 2): 0
        def h51 = d33 ? Calculo.instance.redondear(d66/d33*100, 2): 0
        def d60 = d57 ? Calculo.instance.redondear(h62/d57*100, 2): 0
        def d61 = d58 - d60
        def d62 = h55 ? Calculo.instance.redondear(d61/h55*100, 2): 0
        def h59 = jdSac
        def h60 = blcH50
        def h61 = h60 ? Calculo.instance.redondear(h59/h60*100, 2): 0
        def d67 = h55 ? Calculo.instance.redondear(d8*h55/100, 2): 0
        def d68 = Calculo.instance.redondear((d4*h20/100)-d65, 2)
        def h65 = Calculo.instance.redondear(d7*porcSac/100, 2)
        def h66 = Calculo.instance.redondear(d6*polCac/100, 2)
        def d69 = d68 - h65 - h66 - d67
        def d70 = d4 ? Calculo.instance.redondear(d9/d4*100, 2): 0
        def h67 = d4 ? ( 
            Calculo.instance.redondear( (d30+d28)/d4*100, 2) - 
            Calculo.instance.redondear( (d65/d4)*100, 2)
        ): 0

        def h68 = d4 ? ( h67 - 
            Calculo.instance.redondear(d67/d4*100, 2) - 
            Calculo.instance.redondear(h66/d4*100, 2) -
            Calculo.instance.redondear(h65/d4*100, 2)
        ): 0

        def h69 = d33 ? Calculo.instance.redondear( d65/d33*100, 2): 0
        def h70 = ( 
            Calculo.instance.redondear(d66/0.997 , 2) +
            Calculo.instance.redondear(h62/0.997 , 2) -
            Calculo.instance.redondear(h4/0.997  , 2)
        )
        def d71 = d4 ? Calculo.instance.redondear(h70/d4*100, 2): 0
        def h71 = d30 ? Calculo.instance.redondear(d65/d30*100, 2): 0
        def h72 = d49 ? Calculo.instance.redondear(h71/d49*100, 2): 0
        def d74 = d4 ? Calculo.instance.redondear(h65/d4*100, 2): 0
        def d75 = d4 ? Calculo.instance.redondear(h66/d4*100, 2): 0
        def d76 = d4 ? Calculo.instance.redondear(d67/d4*100, 2): 0
        def d77 = d4 ? Calculo.instance.redondear(d69/d4*100, 2): 0
        def d79 = h65 + h66 + d67 + d69

        // 1,DATOS OBTENIDOS EN FÁBRICA
        println "  3 | TONELADAS CAÑA                          | " + d4
        println "  4 | TONELADAS JUGO DILUIDO                  | " + d5
        println "  5 | TONELADAS CACHAZA                       | " + d6
        println "  6 | TONELADAS DE BAGAZO                     | " + d7
        println "  7 | TONELADAS MIEL FINAL                    | " + d8
        println "  8 | TONELADAS AZUCAR                        | " + d9
        println "  9 | TONELADAS AGUA IMBIBICION               | " + d10
        println " 10 | SACAROSA PERDIDA MIEL FINAL % CAÑA      | " + d11
        println " 11 | Stock Sacarosa Proceso día anterior     | " + h4
        println " 12 | Ton Azúcar Reproceso                    | " + h5
        println " 13 | Ton Azúcar Reproceso Recuperable        | " + h6

        // 14,CALCULOS BÁSICOS
        println " 15 | Ton Jugo Diluido Neto                   | " + d16
        println " 16 | Brix % Bagazo                           | " + d17
        println " 17 | Fibra % Bagazo                          | " + d18
        println " 18 | Ton Bagazo                              | " + d19
        println " 19 | Bagazo % Caña                           | " + d20
        println " 20 | Fibra en Bagazo % Caña                  | " + d21
        println " 21 | Toneladas de Fibra en Bagazo            | " + d22
        println " 22 | Ton Sólidos Ins Jugo Diluido            | " + d23
        println " 23 | Ton Fibra en Caña                       | " + d24
        println " 24 | Fibra % Caña                            | " + d25
        println " 25 | Maceración % caña                       | " + d26
        println " 26 | Maceracion % Fibra                      | " + d27
        println " 27 | Ton Sacarosa Apt Bagazo                 | " + d28
        println " 28 | Ton Sólidos Bagazo                      | " + d29
        println " 29 | Ton Sac Apte Jugo Diluido               | " + d30
        println " 30 | Ton Sac Real Jugo Diluido Cromatografía | " + d31
        println " 31 | Ton Sólidos Jugo Diluido                | " + d32
        println " 32 | Ton Sac Apte Caña                       | " + d33
        println " 33 | Ton Sac Real Caña                       | " + d34
        println " 34 | Ton Jugo Absoluto                       | " + d35
        println " 35 | Sac Apte % Jugo Abs                     | " + d36
        println " 36 | Sac Real  % Jugo Abs                    | " + d37
        println " 37 | Ton sólidos en Caña                     | " + d38
        println " 38 | Brix % Jugo Absoluto                    | " + d39
        println " 39 | Pureza Jugo Absoluto                    | " + h16
        println " 40 | Ton Jugo Abs Extraídas                  | " + h17
        println " 41 | Extracción de jugo abs % Caña           | " + h18
        println " 42 | Extracción Jugo Dil Net % caña          | " + h19
        println " 43 | Sac Apte % Caña                         | " + h20
        println " 44 | Sac Real % Caña                         | " + h21
        println " 45 | Cachaza % Caña                          | " + h22
        println " 46 | Ton Sac Apte en Cachaza                 | " + h23
        println " 47 | Sac Apte Cachaza % caña                 | " + h24
        println " 48 | Ton Sac Apte Miel Final                 | " + h25
        println " 49 | Ton Sac Real Miel Final Cromatografía   | " + h26
        println " 50 | RSac Apte Miel Final % Caña             | " + h27
        println " 51 | Sac Real Miel Final % Caña              | " + h28

        // 52,CÁLCULO MOLINOS
        println " 53 | Extracción de Sac % Caña                | " + d42
        println " 54 | Sac Apte en Bagazo % Caña               | " + d43
        println " 55 | Ext Sac apte % sac apte caña            | " + d44
        println " 56 | Sac Apte perdida en bag % Fibra en bag  | " + d45
        println " 57 | Jugo abs Perdido % Fibra Caña           | " + h42
        println " 58 | Ext Sac Reducida 12,5 % Fibra           | " + h43

        // 59,CÁLCULO ELABORACIÓN
        println " 60 | Recuperación Teórica SJM                | " + d48
        println " 61 | Recuperación Teórica Winter             | " + d49
        println " 62 | Dilución % Caña                         | " + d50
        println " 63 | Eficiencia SJM                          | " + d51
        println " 64 | Toneladas de azúcar recuperable         | " + d52
        println " 65 | Rendimiento Teórico                     | " + h48
        println " 66 | Rendimiento Comercial                   | " + h49
        println " 67 | Rendimiento Base Sacarosa               | " + h51
        
        // MATERIAL EN PROCESO
        println " 69 | Pol Azúcar                              | " + d55
        println " 70 | Brix Azúcar                             | " + d56
        println " 71 | Pureza Azúcar                           | " + d57
        println " 72 | Toneladas en Proceso                    | " + d58
        println " 73 | Recuperación Teórica SJM Material de Pro| " + d59
        println " 74 | Ton Azúcar Recuperable                  | " + d60
        println " 75 | Ton Sacarosa Miel Final                 | " + d61
        println " 76 | Ton Miel Final Recuperable              | " + d62
        println " 77 | Sacarosa Miel Final                     | " + h55
        println " 78 | Brix Miel Final                         | " + h56
        println " 79 | Pureza Miel Final                       | " + h57
        println " 80 | Pureza Material en Proceso              | " + h58
        println " 81 | Sacarosa Jugo Diluido                   | " + h59
        println " 82 | Brix Jugo Diluido                       | " + h60
        println " 83 | Pureza Jugo Diluido                     | " + h61
        println " 84 | Toneladas de sac Recuperable en Azúcar  | " + h62

        // 85,CÁLCULOS Y BALANCE DE SACAROSA
        println " 86 | Toneladas de Sacarosa Recobrada         | " + d65
        println " 87 | Ton Sacarosa Azúcar Producida           | " + d66
        println " 88 | Ton Sacarosa neta Miel final - Melaza   | " + d67
        println " 89 | Pérdidas totales                        | " + d68
        println " 90 | Pérdidas Indeterminadas (Ton)           | " + d69
        println " 91 | Pérdidas totales Sacarosa % Caña        | " + h67
        println " 92 | Pérdidas Indeterminadas % Caña          | " + h68
        println " 93 | Sacarosa en Azúcar %Sacarosa en Caña    | " + h69
        println " 94 | Rendimiento Comercial Telquel           | " + d70
        println " 95 | Rendimiento Real (99,7 %)               | " + d71
        println " 96 | Toneladas Azúcar Neto                   | " + h70
        println " 97 | Sac Azúcar % Sac Aparente Jugo Diluido  | " + h71
        println " 98 | Eficiencia Elaboración (%)              | " + h72
        println " 99 | Ton Sacarosa Bagazo                     | " + h65
        println "100 | Ton Sacarosa Cachaza                    | " + h66
        println "101 | Pérdidas  Sacarosa Bagazo % Caña        | " + d74
        println "102 | Pérdidas  Sacarosa Cachaza % Caña       | " + d75
        println "103 | Pérdidas  Sacarosa Miel Final % Caña    | " + d76
        println "104 | Pérdidas Indeterminadas % Caña          | " + d77
        println "105 | Azúcar Reproceso (Pol)                  | " + h75
        println "106 | Recuperación SJM Azúcar Reproceso       | " + h76
        println "107 | Ton Pérdida Total                       | " + d79

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

    void getPrueba(){
        /*
        println "\n*** StockProcesoDetalle1"
        def d = SqlUtil.instance.getDetallePorDTM(Aux.instance.diaTrabajoId, "stockProceso", "StockProcesoDetalle1", "recMag1")
        if (d){
            println ">>> volumen2 = ${d.volumen1}"
            println ">>> peso     = ${d.peso}"
            println ">>> porcBrix = ${d.porcBrix}"
            println ">>> eq       = ${d.eq}"
            println ">>> porcSac  = ${d.porcSac}"
            println ">>> densidad = ${d.densidad}"
        }
        d = SqlUtil.instance.getDetallePorIndicador(Aux.instance.diaTrabajoId, "StockFabricaDetalle73", "stockFabrica.diaTrabajo.id", "tonAzuDis")
        def sdC = d.valor?:0
        println ">>> sf.bg142: ${sdC}"
        */

        def diaTrabajo = SqlUtil.instance.getDiaTrabajo(Aux.instance.diaTrabajoId)
        def diaFin = diaTrabajo.numeroDia - 1
        def l8 = SqlUtil.instance.getValMatBlcAcu("canaDia", diaFin)
        println "Blc.L8: ${l8}"

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
