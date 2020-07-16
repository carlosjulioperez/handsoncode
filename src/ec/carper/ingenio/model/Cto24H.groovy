package ec.carper.ingenio.model 

import ec.carper.ingenio.util.Calculo

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="""diaTrabajo.descripcion""")
@View(members="""#
    diaTrabajo, fFelining;
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
    }
    Cto24H_cs {
    }
    Cto24H_ip {
    }
    Cto24H_av {
        fr;detalle
    }   
    Cto24H_ce {
    }
""")
class Cto24H extends DiaTrabajoEditable {
    
    boolean detallesCargados
    
    @Digits(integer=3, fraction=3) @DisplaySize(6) @Required
    BigDecimal fFelining
    
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
    Collection<Cto24HDetalle1>detalle1
    
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
    
    @OneToMany (mappedBy="cto24H", cascade=CascadeType.ALL) //@EditOnly
    @ListProperties(""" masa1, masa2, masa3, porcInso """)
    Collection<Cto24HDetalle2>detalle2

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

    @OneToMany (mappedBy="cto24H", cascade=CascadeType.ALL) //@EditOnly
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
    Collection<Cto24HDetalle3>detalle3

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
    // ACIDEZ VOLATIL
    // ==================================================
    @Digits(integer=6,fraction=3) @DisplaySize(6)
    BigDecimal fr
    
    BigDecimal mlTitu
    BigDecimal fd
    BigDecimal ppm
    
    @OneToMany (mappedBy="cto24H", cascade=CascadeType.ALL)
    @ListProperties("""
        modulo.descripcion,turno.descripcion,tipo,hora,
        mlTitu [cto24H.promMlTitu],
        fd     [cto24H.promFd],
        ppm    [cto24H.promPpm]
    """)
    Collection<Cto24HDetalle>detalle

    BigDecimal getPromMlTitu() { return super.getPromedio(detalle, "mlTitu", 2) }
    BigDecimal getPromFd    () { return super.getPromedio(detalle, "fd",     2) }
    BigDecimal getPromPpm   () { return super.getPromedio(detalle, "ppm",    2) }

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

            // ACIDEZ VOLATIL
            this.mlTitu     = promMlTitu
            this.fd         = promFd
            this.ppm        = promPpm
            
            XPersistence.getManager().persist(this)

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
            // d1.cana       = 1
            // d1.j1Extracto = 1
            // d1.jDiluido   = 1
            // d1.jClaro     = 1
            // d1.jFiltrado  = 1
            // d1.mClara     = 1
            // d1.mielA      = 1
            // d1.mielB      = 1
            // d1.mielF      = 1
            getManager().persist(d1)
            
        }catch(Exception ex){
            throw new SystemException("detalles_no_cargados", ex)
        }
    }
    
    // Métodos de cálculos
    def calcJugo (def atributo){
        def valor = 0.0
        if (detalle){
            detalle1.each {
                def v = (BigDecimal)Eval.x(it, "x."+atributo)
                valor = Calculo.instance.redondear((5.127 / (v * fFelining * 0.02)), 2)
            }
        }
        println "calcJugo para ${atributo}: ${valor}"
        return valor 
    }

    def calcMiel (def atributo){
        def valor = 0.0
        if (detalle){
            detalle1.each {
                def v = (BigDecimal)Eval.x(it, "x."+atributo)
                valor = Calculo.instance.redondear((5.127 / (v * fFelining * 0.005)), 2)
            }
        }
        println "calcMiel para ${atributo}: ${valor}"
        return valor
    }
}
