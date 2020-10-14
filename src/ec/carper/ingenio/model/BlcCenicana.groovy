package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*
import ec.carper.ingenio.util.*

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="diaTrabajo.descripcion, descripcion")
@View(members="""
    diaTrabajo, descripcion;
    detalle { detalle }
""")
class BlcCenicana extends Formulario {
    
    @OnChange(BlcCenicanaShowHideCargarItemsAction.class)
    boolean itemsCargados
    
    @Column(length=30) @Required
    String descripcion 

    @OneToMany (mappedBy="blcCenicana", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcCenicanaDetalle> detalle

    void cargarItems() throws ValidationException{
        try{
            this.itemsCargados = true
            getManager().persist(this)
            cargarDetalles(this)
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

    void cargarDetalles(BlcCenicana blcCenicana){
        try{
            def lista = getManager().createQuery("FROM BlcCenicanaPDetalle WHERE blcCenicanaP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcCenicanaDetalle(blcCenicana: blcCenicana, orden: it.orden, peso: it.peso, porcC: it.porcC, descripcion: it.descripcion)
                getManager().persist(d)
            }
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }
    
    void consultarDatos() throws ValidationException{
        try{ 
            consultar()
        }catch(Exception ex){
            throw new SystemException("datos_no_consultados", ex)
        }
    }
    
    def consultar(){
        def diaFin = diaTrabajo.numeroDia - 1
        def mfSac     = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"       , "mfSac")
        def pol       = SqlUtil.instance.getValorCampo(diaTrabajo.id , "AzucarGranel" , "pol")
        def hum       = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Grasshoper"   , "humedad")
        def mfBri2    = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"       , "mfBri2")
        def polReproc = SqlUtil.instance.getValorCampo(diaTrabajo.id , "AzucarGranel" , "polReproc")
        def solInsol  = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Cto24H"       , "porcInso")
        def jrPur     = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Jugo"         , "jrPur")
        def porcSac   = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Bagazo"       , "porcSacarosa")
        def porcHum   = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Bagazo"       , "porcHumedad")
        def porcFib   = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Bagazo"       , "porcFibra")
        def jdSac     = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Jugo"         , "jdSac")
        def brixJDil  = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Jugo"         , "jdBri")
        def polCac    = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Turbiedad"    , "polCachaza")
        def tonSac    = SqlUtil.instance.getValorCampo(diaTrabajo.id , "StockProceso" , "tonSac")  // ='SP'!L67
        def pureza    = SqlUtil.instance.getValorCampo(diaTrabajo.id , "StockProceso" , "pureza")  // ='SP'!M67
        
        def d4  = getValorBlc("canaNeta", 1)
        def d5  = getValorBlc("jDiluidoBr", 2)
        def d6  = getValorBlc("cachaza", 1)
        
        def d   = SqlUtil.instance.getDetallePorDTM(diaTrabajo.id, "stockProceso", "StockProcesoDetalle2", "aguaM")
        def d10 = d ? (d.volumen1?:0): 0

        def d7  = d4 + d10 - d5
        def d8  = getValorBlc("mielFM", 1)
        def d9  = getValorBlc("azucarB", 2)
        
        def h25 = Calculo.instance.redondear(d8*mfSac/100, 2)
        def h27 = d4 ? Calculo.instance.redondear((h25/d4)*100, 2): 0
        def d11 = h27
        def h4  = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacRec" , diaFin)
        
        d = SqlUtil.instance.getDetallePorIndicador(diaTrabajo.id, "StockFabricaDetalle73", "stockFabrica.diaTrabajo.id", "tonAzuDis")
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
        
        // println ">> num: ${num}"
        // println ">> den: ${den}"
        // println ">> d57: ${d57}"
        // println ">> h57: ${h57}"
        // println ">> h58: ${h58}"

        def d59 = den!=0 ? Calculo.instance.redondear( (num/den)*100, 2): 0
        def h62 = Calculo.instance.redondear(d58*d59/100,2)
        def d66 = Calculo.instance.redondear(d9*d55/100,2)
        def d65 = d66 + h62 - h4
        // def blcH50 = SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle5" , "pzaJDil")
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

        detalle.each{
            switch (it.orden){
                case   3: it.valor = d4; break
                case   4: it.valor = d5; break
                case   5: it.valor = d6; break
                case   6: it.valor = d7; break
                case   7: it.valor = d8; break
                case   8: it.valor = d9; break
                case   9: it.valor = d10; break
                case  10: it.valor = d11; break
                case  11: it.valor = h4; break
                case  12: it.valor = h5; break
                case  13: it.valor = h6; break
                
                case  15: it.valor = d16; break
                case  16: it.valor = d17; break
                case  17: it.valor = d18; break
                case  18: it.valor = d19; break
                case  19: it.valor = d20; break
                case  20: it.valor = d21; break
                case  21: it.valor = d22; break
                case  22: it.valor = d23; break
                case  23: it.valor = d24; break
                case  24: it.valor = d25; break
                case  25: it.valor = d26; break
                case  26: it.valor = d27; break
                case  27: it.valor = d28; break
                case  28: it.valor = d29; break
                case  29: it.valor = d30; break
                case  30: it.valor = d31; break
                case  31: it.valor = d32; break
                case  32: it.valor = d33; break
                case  33: it.valor = d34; break
                case  34: it.valor = d35; break
                case  35: it.valor = d36; break
                case  36: it.valor = d37; break
                case  37: it.valor = d38; break
                case  38: it.valor = d39; break
                case  39: it.valor = h16; break
                case  40: it.valor = h17; break
                case  41: it.valor = h18; break
                case  42: it.valor = h19; break
                case  43: it.valor = h20; break
                case  44: it.valor = h21; break
                case  45: it.valor = h22; break
                case  46: it.valor = h23; break
                case  47: it.valor = h24; break
                case  48: it.valor = h25; break
                case  49: it.valor = h26; break
                case  50: it.valor = h27; break
                case  51: it.valor = h28; break

                case  53: it.valor = d42; break
                case  54: it.valor = d43; break
                case  55: it.valor = d44; break
                case  56: it.valor = d45; break
                case  57: it.valor = h42; break
                case  58: it.valor = h43; break

                case  60: it.valor = d48; break
                case  61: it.valor = d49; break
                case  62: it.valor = d50; break
                case  63: it.valor = d51; break
                case  64: it.valor = d52; break
                case  65: it.valor = h48; break
                case  66: it.valor = h49; break
                case  67: it.valor = h51; break

                case  69: it.valor = d55; break
                case  70: it.valor = d56; break
                case  71: it.valor = d57; break
                case  72: it.valor = d58; break
                case  73: it.valor = d59; break
                case  74: it.valor = d60; break
                case  75: it.valor = d61; break
                case  76: it.valor = d62; break
                case  77: it.valor = h55; break
                case  78: it.valor = h56; break
                case  79: it.valor = h57; break
                case  80: it.valor = h58; break
                case  81: it.valor = h59; break
                case  82: it.valor = h60; break
                case  83: it.valor = h61; break
                case  84: it.valor = h62; break

                case  86: it.valor = d65; break
                case  87: it.valor = d66; break
                case  88: it.valor = d67; break
                case  89: it.valor = d68; break
                case  90: it.valor = d69; break
                case  91: it.valor = h67; break
                case  92: it.valor = h68; break
                case  93: it.valor = h69; break
                case  94: it.valor = d70; break
                case  95: it.valor = d71; break
                case  96: it.valor = h70; break
                case  97: it.valor = h71; break
                case  98: it.valor = h72; break
                case  99: it.valor = h65; break
                case 100: it.valor = h66; break
                case 101: it.valor = d74; break
                case 102: it.valor = d75; break
                case 103: it.valor = d76; break
                case 104: it.valor = d77; break
                case 105: it.valor = h75; break
                case 106: it.valor = h76; break
                case 107: it.valor = d79; break
            }
            getManager().persist(it)
        }
    }
    
    def getValorBlc(def campo, def col){
        def d = SqlUtil.instance.getDetallePorDTM(diaTrabajo.id, "blc", "BlcDetalle1", campo)
        return col == 1 ? (d.valor?:0): (d.cantidad?:0)
    }
}

