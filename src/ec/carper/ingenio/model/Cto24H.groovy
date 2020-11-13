package ec.carper.ingenio.model 

import ec.carper.ingenio.util.*

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="diaTrabajo.fecha, descripcion")
@View(members="""
    diaTrabajo, descripcion;

    Cto24H_atr {
        detalle1
    }
    Cto24H_psi {
        detalle2
    }
    Cto24H_ar {
        detalle3
    }
    Cto24H_cc {
        detalle41;
        detalle42;
    }
    Cto24H_cs {
        detalle5
    }
    Cto24H_ip {
        detalle6
    }
    Cto24H_av {
        detalle7
    }   
    Cto24H_ce {
        detalle8
    }
""")
class Cto24H extends Formulario {
    
    boolean detallesCargados
    
    // Usado para pruebas solamente
    @Column(length=10)
    String descripcion 

    // @Digits(integer=3, fraction=3) @DisplaySize(6) @ReadOnly
    // BigDecimal fFelining

    // ==================================================
    // AZÚCARES TOTALES REDUCTORES A.T.R.
    // ==================================================
    BigDecimal cana
    BigDecimal j1Extracto
    BigDecimal jDiluido
    BigDecimal jClaro
    BigDecimal jFiltrado
    BigDecimal mClara
    BigDecimal mielA
    BigDecimal mielB
    BigDecimal mielF

    @OneToMany (mappedBy="cto24H", cascade=CascadeType.ALL) @EditOnly
    @ListProperties("""
        cana       [ cto24H.pd11 ],
        j1Extracto [ cto24H.pd12 ],
        jDiluido   [ cto24H.pd13 ],
        jClaro     [ cto24H.pd14 ],
        jFiltrado  [ cto24H.pd15 ],
        mClara     [ cto24H.pd16 ],
        mielA      [ cto24H.pd17 ],
        mielB      [ cto24H.pd18 ],
        mielF      [ cto24H.pd19 ]
    """)
    Collection<Cto24HDetalle1> detalle1
    
    //BigDecimal getPd11() { return Calculo.instance.redondear((calcJugo("cana")*3), 2) }
    BigDecimal getPd11() { return detalle1[0] ? detalle1[0].pd11: 0 }
    BigDecimal getPd12() { return detalle1[0] ? detalle1[0].pd12: 0 }
    BigDecimal getPd13() { return detalle1[0] ? detalle1[0].pd13: 0 }
    BigDecimal getPd14() { return detalle1[0] ? detalle1[0].pd14: 0 }
    BigDecimal getPd15() { return detalle1[0] ? detalle1[0].pd15: 0 }
    
    BigDecimal getPd16() { return detalle1[0] ? detalle1[0].pd16: 0 }
    BigDecimal getPd17() { return detalle1[0] ? detalle1[0].pd17: 0 }
    BigDecimal getPd18() { return detalle1[0] ? detalle1[0].pd18: 0 }
    BigDecimal getPd19() { return detalle1[0] ? detalle1[0].pd19: 0 }

    // ==================================================
    // % SOLIDOS INSOLUBLES (CTO 24 HORAS)
    // ==================================================
    BigDecimal masa1
    BigDecimal masa2
    BigDecimal masa3
    BigDecimal porcInso
    
    @OneToMany (mappedBy="cto24H", cascade=CascadeType.ALL) @EditOnly
    @ListProperties(""" masa1, masa2, masa3, porcInso """)
    Collection<Cto24HDetalle2> detalle2

    // ==================================================
    // AZUCARES REDUCTORES - Lane Eynon
    // ==================================================
    BigDecimal cana1
    BigDecimal j1Extracto1
    BigDecimal jDiluido1
    BigDecimal jClaro1
    BigDecimal jFiltrado1
    BigDecimal mClara1
    BigDecimal mielA1
    BigDecimal mielB1
    BigDecimal mielF1

    BigDecimal cana2
    BigDecimal j1Extracto2
    BigDecimal jDiluido2
    BigDecimal jClaro2
    BigDecimal jFiltrado2
    BigDecimal mClara2
    BigDecimal mielA2
    BigDecimal mielB2
    BigDecimal mielF2

    @OneToMany (mappedBy="cto24H", cascade=CascadeType.ALL) @EditOnly
    @ListProperties("""
        cana       [ cto24H.pd311, cto24H.pd321 ],
        j1Extracto [ cto24H.pd312, cto24H.pd322 ],
        jDiluido   [ cto24H.pd313, cto24H.pd323 ],
        jClaro     [ cto24H.pd314, cto24H.pd324 ],
        jFiltrado  [ cto24H.pd315, cto24H.pd325 ],
        mClara     [ cto24H.pd316, cto24H.pd326 ],
        mielA      [ cto24H.pd317, cto24H.pd327 ],
        mielB      [ cto24H.pd318, cto24H.pd328 ],
        mielF      [ cto24H.pd319, cto24H.pd329 ]
    """)
    Collection<Cto24HDetalle3> detalle3

    BigDecimal getPd311() { return detalle3[0] ? detalle3[0].pd311: 0 }
    BigDecimal getPd312() { return detalle3[0] ? detalle3[0].pd312: 0 }
    BigDecimal getPd313() { return detalle3[0] ? detalle3[0].pd313: 0 }
    BigDecimal getPd314() { return detalle3[0] ? detalle3[0].pd314: 0 }
    BigDecimal getPd315() { return detalle3[0] ? detalle3[0].pd315: 0 }
    BigDecimal getPd316() { return detalle3[0] ? detalle3[0].pd316: 0 }
    BigDecimal getPd317() { return detalle3[0] ? detalle3[0].pd317: 0 }
    BigDecimal getPd318() { return detalle3[0] ? detalle3[0].pd318: 0 }
    BigDecimal getPd319() { return detalle3[0] ? detalle3[0].pd319: 0 }
    
    BigDecimal getPd321() { return detalle3[0] ? detalle3[0].pd321: 0 }
    BigDecimal getPd322() { return detalle3[0] ? detalle3[0].pd322: 0 }
    BigDecimal getPd323() { return detalle3[0] ? detalle3[0].pd323: 0 }
    BigDecimal getPd324() { return detalle3[0] ? detalle3[0].pd324: 0 }
    BigDecimal getPd325() { return detalle3[0] ? detalle3[0].pd325: 0 }
    BigDecimal getPd326() { return detalle3[0] ? detalle3[0].pd326: 0 }
    BigDecimal getPd327() { return detalle3[0] ? detalle3[0].pd327: 0 }
    BigDecimal getPd328() { return detalle3[0] ? detalle3[0].pd328: 0 }
    BigDecimal getPd329() { return detalle3[0] ? detalle3[0].pd329: 0 }

    // ==================================================
    // CENIZAS CONDUCTIMETRICAS EN MATERIALES DE PROCESO
    // ==================================================
    // Consulta de Brix Mtra
    @ElementCollection @ReadOnly
    @ListProperties("""
        descripcion, j1Extracto, jDiluido, jClaro, jFiltrado, mClara, mielA, mielB, mielF
    """)
    Collection<Cto24HDetalle4> detalle41
        
    BigDecimal j1Extracto3
    BigDecimal jDiluido3
    BigDecimal jClaro3
    BigDecimal jFiltrado3
    BigDecimal mClara3
    BigDecimal mielA3
    BigDecimal mielB3
    BigDecimal mielF3
    
    @ElementCollection @EditOnly
    @ListProperties("""
        descripcion,
        j1Extracto[ cto24H.pd4211, cto24H.pd4212, cto24H.pd4213, cto24H.pd4214, cto24H.pd4215 ],
        jDiluido  [ cto24H.pd4221, cto24H.pd4222, cto24H.pd4223, cto24H.pd4224, cto24H.pd4225 ],
        jClaro    [ cto24H.pd4231, cto24H.pd4232, cto24H.pd4233, cto24H.pd4234, cto24H.pd4235 ],
        jFiltrado [ cto24H.pd4241, cto24H.pd4242, cto24H.pd4243, cto24H.pd4244, cto24H.pd4245 ],
        mClara    [ cto24H.pd4251, cto24H.pd4252, cto24H.pd4253, cto24H.pd4254, cto24H.pd4255 ],
        mielA     [ cto24H.pd4261, cto24H.pd4262, cto24H.pd4263, cto24H.pd4264, cto24H.pd4265 ],
        mielB     [ cto24H.pd4271, cto24H.pd4272, cto24H.pd4273, cto24H.pd4274, cto24H.pd4275 ],
        mielF     [ cto24H.pd4281, cto24H.pd4282, cto24H.pd4283, cto24H.pd4284, cto24H.pd4285 ]
    """)
    Collection<Cto24HDetalle4> detalle42
    
    BigDecimal getPd4211() { return getPd4Valor (1 , "j1Extracto") }
    BigDecimal getPd4212() { return getPd4Valor (2 , "j1Extracto" , "getPd4211") }
    BigDecimal getPd4213() { return getPd4Valor (3 , "j1Extracto" ) }
    BigDecimal getPd4214() { return getPd4Valor (4 , "j1Extracto" ) }
    BigDecimal getPd4215() { return getPd4Valor (5 , "j1Extracto", "getPd4213", "getPd4211", "getPd4214" ) }

    BigDecimal getPd4221() { return getPd4Valor (1 , "jDiluido" ) }
    BigDecimal getPd4222() { return getPd4Valor (2 , "jDiluido" , "getPd4221") }
    BigDecimal getPd4223() { return getPd4Valor (3 , "jDiluido" ) }
    BigDecimal getPd4224() { return getPd4Valor (4 , "jDiluido" ) }
    BigDecimal getPd4225() { return getPd4Valor (5 , "jDiluido", "getPd4223", "getPd4221", "getPd4224" ) }

    BigDecimal getPd4231() { return getPd4Valor (1 , "jClaro" ) }
    BigDecimal getPd4232() { return getPd4Valor (2 , "jClaro" , "getPd4231") }
    BigDecimal getPd4233() { return getPd4Valor (3 , "jClaro" ) }
    BigDecimal getPd4234() { return getPd4Valor (4 , "jClaro" ) }
    BigDecimal getPd4235() { return getPd4Valor (5 , "jClaro", "getPd4233", "getPd4231", "getPd4234" ) }

    BigDecimal getPd4241() { return getPd4Valor (1 , "jFiltrado" ) }
    BigDecimal getPd4242() { return getPd4Valor (2 , "jFiltrado" , "getPd4241") }
    BigDecimal getPd4243() { return getPd4Valor (3 , "jFiltrado" ) }
    BigDecimal getPd4244() { return getPd4Valor (4 , "jFiltrado" ) }
    BigDecimal getPd4245() { return getPd4Valor (5 , "jFiltrado", "getPd4243", "getPd4241", "getPd4244" ) }

    BigDecimal getPd4251() { return getPd4Valor (1 , "mClara" ) }
    BigDecimal getPd4252() { return getPd4Valor (2 , "mClara" , "getPd4251") }
    BigDecimal getPd4253() { return getPd4Valor (3 , "mClara" ) }
    BigDecimal getPd4254() { return getPd4Valor (4 , "mClara" ) }
    BigDecimal getPd4255() { return getPd4Valor (5 , "mClara", "getPd4253", "getPd4251", "getPd4254" ) }

    BigDecimal getPd4261() { return getPd4Valor (1 , "mielA" ) }
    BigDecimal getPd4262() { return getPd4Valor (2 , "mielA" , "getPd4261") }
    BigDecimal getPd4263() { return getPd4Valor (3 , "mielA" ) }
    BigDecimal getPd4264() { return getPd4Valor (4 , "mielA" ) }
    BigDecimal getPd4265() { return getPd4Valor (5 , "mielA", "getPd4263", "getPd4261", "getPd4264" ) }

    BigDecimal getPd4271() { return getPd4Valor (1 , "mielB" ) }
    BigDecimal getPd4272() { return getPd4Valor (2 , "mielB" , "getPd4271") }
    BigDecimal getPd4273() { return getPd4Valor (3 , "mielB" ) }
    BigDecimal getPd4274() { return getPd4Valor (4 , "mielB" ) }
    BigDecimal getPd4275() { return getPd4Valor (5 , "mielB", "getPd4273", "getPd4271", "getPd4274" ) }

    BigDecimal getPd4281() { return getPd4Valor (1 , "mielF" ) }
    BigDecimal getPd4282() { return getPd4Valor (2 , "mielF" , "getPd4281") }
    BigDecimal getPd4283() { return getPd4Valor (3 , "mielF" ) }
    BigDecimal getPd4284() { return getPd4Valor (4 , "mielF" ) }
    BigDecimal getPd4285() { return getPd4Valor (5 , "mielF", "getPd4283", "getPd4281", "getPd4284" ) }

    def getPd4Valor (int totalFilaNo, String propiedad, String... args){

        def valor = 0
        def o0 = detalle42[0]
        def o1 = detalle42[1]
        def o2 = detalle42[2]
        def o3 = detalle42[3]
        
        def o4 = detalle41[0] //Brix
                
        def v0 = o0 ? ( (BigDecimal)Eval.x(o0, "x."+propiedad)?:0 ) : 0
        def v1 = o1 ? ( (BigDecimal)Eval.x(o1, "x."+propiedad)?:0 ) : 0
        def v2 = o2 ? ( (BigDecimal)Eval.x(o2, "x."+propiedad)?:0 ) : 0
        def v3 = o3 ? ( (BigDecimal)Eval.x(o3, "x."+propiedad)?:0 ) : 0

        def v4 = o4 ? ( (BigDecimal)Eval.x(o4, "x."+propiedad)?:0 ) : 0

        switch (totalFilaNo){
            case 1: 
                valor = (o1 && o2) ? calcC20 (v1, v2) : 0
                break
            
            case 2:
                def metodo1 = args.length>0 ? args[0] : ""
                def v = this."${metodo1}"() ?:0
                valor = o0 ? ( v - v0 ) : 0
                break
            
            case 3:
                valor = Calculo.instance.getPorc2(v4, v3, 2)
                break
            
            case 4:
                valor = v3>0 ? Calculo.instance.redondear(5/v3, 2): 0
                break
            
            case 5:
                def metodo1 = args.length>0 ? args[0] : ""
                def metodo2 = args.length>0 ? args[1] : ""
                def metodo3 = args.length>0 ? args[2] : ""
                
                def m1 = this."${metodo1}"() ?:0
                def m2 = this."${metodo2}"() ?:0
                def m3 = this."${metodo3}"() ?:0
                
                valor = porcCenizas(m1, m2, m3)
                break
        }

        return valor
    }

    def calcC20 (def valor1, def valor2){
        return Calculo.instance.redondear(valor1/(1+0.023*(valor2-20)),8)
    }

    def porcCenizas (def valor1, def valor2, def valor3){
        return Calculo.instance.redondear( (16.2+0.36*valor1)*0.0001*valor2*valor3, 2)
    }

    // ==================================================
    // CENIZAS SULAFATADAS MIEL FINAL O MELAZA (CTO 24 HORAS)
    // ==================================================
    @OneToMany (mappedBy="cto24H", cascade=CascadeType.ALL) @EditOnly
    Collection<Cto24HDetalle5> detalle5

    // ==================================================
    // INDICE DE PREPARACION
    // ==================================================
    @OneToMany (mappedBy="cto24H", cascade=CascadeType.ALL) @EditOnly
    Collection<Cto24HDetalle6> detalle6

    // ==================================================
    // ACIDEZ VOLATIL
    // ==================================================
    // @Digits(integer=6,fraction=3) @DisplaySize(6) @ReadOnly
    // BigDecimal fr 
    
    BigDecimal mlTitu
    BigDecimal fd
    BigDecimal ppm
    
    @DisplaySize(6)
    BigDecimal mlTitu2

    @DisplaySize(6)
    BigDecimal fd2

    BigDecimal ppm2
    
    @OneToMany (mappedBy="cto24H", cascade=CascadeType.ALL)
    @ListProperties("""
        modulo.descripcion,
        turno.descripcion,
        tipo,hora,
        mlTitu [cto24H.promMlTitu , cto24H.mlTitu2] ,
        fd     [cto24H.promFd     , cto24H.fd2]     ,
        ppm    [cto24H.promPpm    , cto24H.promPpm2]
    """)
    Collection<Cto24HDetalle7> detalle7

    BigDecimal getPromMlTitu() { return super.getPromedio(detalle7, "mlTitu", 2) }
    BigDecimal getPromFd    () { return super.getPromedio(detalle7, "fd",     2) }
    BigDecimal getPromPpm   () { return super.getPromedio(detalle7, "ppm",    2) }

    // =(Q35*0.1*60000)/($T$37*100)*R35
    @Depends("mlTitu2,fd2")
    BigDecimal getPromPpm2(){
        // Consultar los parámetros
        def parametro = new Parametro()
        def fr        = new BigDecimal(parametro.obtenerValor("CTO24H_FR"))

        def q35 = mlTitu2?:0
        def r35 = fd2?:0

        return (fr!=0 && r35!=0) ? Calculo.instance.redondear( (q35 * 0.1 * 60000) / (fr*100)*r35, 2) : 0
    }

    // ==================================================
    // % CONCENTRACION TOT LINEA EVAPORACION
    // ==================================================
    BigDecimal porcConTotLinEva

    @ElementCollection @EditOnly
    @ListProperties("""
        filaNo,descripcion,brixRef,brixEle,
        porc[cto24H.pd8]
    """)
    Collection<Cto24HDetalle8> detalle8

    BigDecimal getPd8(){
        
        BigDecimal v1 = detalle8[0] ? (detalle8[0].brixEle ?:0) : 0 
        BigDecimal v2 = detalle8[4] ? (detalle8[4].brixEle ?:0) : 0 
        BigDecimal v  = v2 ? Calculo.instance.porcCon(v1, v2): 0
        //println ">>> detalle8: ${detalle8}"
        // println ">>> v1: ${v1}"
        // println ">>> v2: ${v2}"
        // println ">>> v:  ${v}"

        return v
    }

    void actualizar() throws ValidationException{
        try{

            // AZÚCARES TOTALES REDUCTORES A.T.R.
            this.cana       = pd11
            this.j1Extracto = pd12
            this.jDiluido   = pd13
            this.jClaro     = pd14
            this.jFiltrado  = pd15
            this.mClara     = pd16
            this.mielA      = pd17
            this.mielB      = pd18
            this.mielF      = pd19

            // % SOLIDOS INSOLUBLES (CTO 24 HORAS)
            this.masa1    = detalle2[0] ? detalle2[0].masa1: 0
            this.masa2    = detalle2[0] ? detalle2[0].masa2: 0
            this.masa3    = detalle2[0] ? detalle2[0].masa3: 0
            this.porcInso = detalle2[0] ? detalle2[0].porcInso: 0

            // AZUCARES REDUCTORES - Lane Eynon
            this.cana1       = pd311
            this.j1Extracto1 = pd312
            this.jDiluido1   = pd313
            this.jClaro1     = pd314
            this.jFiltrado1  = pd315
            this.mClara1     = pd316
            this.mielA1      = pd317
            this.mielB1      = pd318
            this.mielF1      = pd319

            this.cana2       = pd321
            this.j1Extracto2 = pd322
            this.jDiluido2   = pd323
            this.jClaro2     = pd324
            this.jFiltrado2  = pd325
            this.mClara2     = pd326
            this.mielA2      = pd327
            this.mielB2      = pd328
            this.mielF2      = pd329

            // CENIZAS CONDUCTIMETRICAS EN MATERIALES DE PROCESO
            this.j1Extracto3 = pd4215
            this.jDiluido3   = pd4225
            this.jClaro3     = pd4235
            this.jFiltrado3  = pd4245
            this.mClara3     = pd4255
            this.mielA3      = pd4265
            this.mielB3      = pd4275
            this.mielF3      = pd4285

            // ACIDEZ VOLATIL
            this.mlTitu     = promMlTitu
            this.fd         = promFd
            this.ppm        = promPpm
            this.ppm2       = promPpm2
            
            this.porcConTotLinEva = pd8
            getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }
    
    void cargarDetalles() throws ValidationException{
        try{
            this.detallesCargados = true
            getManager().persist(this)
            cargar(this)
        }catch(Exception ex){
            throw new SystemException("detalles_no_cargados", ex)
        }
    }

    void cargar(Cto24H cto24H){
        try{
            def d1        = new Cto24HDetalle1()
            d1.id         = null
            d1.cto24H     = cto24H
            getManager().persist(d1)
            
            def d2        = new Cto24HDetalle2()
            d2.id         = null
            d2.cto24H     = cto24H
            getManager().persist(d2)
            
            def d3        = new Cto24HDetalle3()
            d3.id         = null
            d3.cto24H     = cto24H
            getManager().persist(d3)
            
            // CENIZAS CONDUCTIMETRICAS EN MATERIALES DE PROCESO
            // Detalle 4 
            this.detalle41 = new ArrayList()
            this.detalle41.add(detalle4JugosBrix())

            this.detalle42 = new ArrayList()
            def d4
            (1..4).each{
                d4 = new Cto24HDetalle4()      
                
                if (it==1) d4.descripcion="Conductividad Agua"
                if (it==2) d4.descripcion="Conductividad Mtra"
                if (it==3) d4.descripcion="Temperatura"
                if (it==4) d4.descripcion="Peso de muestra"

                this.detalle42.add(d4)
            }
            getManager().persist(cto24H)
            
            def d5        = new Cto24HDetalle5()
            d5.id         = null
            d5.cto24H     = cto24H
            getManager().persist(d5)
            
            def d6        = new Cto24HDetalle6()
            d6.id         = null
            d6.cto24H     = cto24H
            getManager().persist(d6)
            
            this.detalle8 = new ArrayList()
            def d8
            (1..5).each{
                d8 = new Cto24HDetalle8()
                d8.filaNo = it

                if (it==1) d8.descripcion="BX J. CLARO"
                if (it==2) d8.descripcion="SALIDA EVAP I"
                if (it==3) d8.descripcion="SALIDA EVAP II"
                if (it==4) d8.descripcion="SALIDA EVAP III"
                if (it==5) d8.descripcion="SALIDA EVAP IV"

                this.detalle8.add(d8)
            }

            
            getManager().persist(cto24H)

        }catch(Exception ex){
            throw new SystemException("detalles_no_cargados", ex)
        }
    }

    def detalle4JugosBrix(){
        def o = new Cto24HDetalle4()

        o.descripcion = "Brix Mtra"
        o.j1Extracto  = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Jugo"     , "jeBri")
        o.jDiluido    = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Jugo"     , "jdBri")
        o.jClaro      = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Jugo"     , "jcBri")
        o.jFiltrado   = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Jugo"     , "jfBri")
        o.mClara      = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Meladura" , "mclBri2")
        o.mielA       = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"   , "maBri2")
        o.mielB       = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"   , "mbBri2")
        o.mielF       = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"   , "mfBri2")

        return o
    }
    
}
