package ec.carper.ingenio.model 

//import ec.carper.ingenio.actions.Cto24HAction
import ec.carper.ingenio.util.Calculo

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
//import org.openxava.calculators.*
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

    @OneToMany (mappedBy="cto24H", cascade=CascadeType.ALL) //@EditOnly
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
    
    BigDecimal getPd11() { return Calculo.instance.redondear((calcJugo("cana")*3), 2) }
    BigDecimal getPd12() { return calcJugo("j1Extracto") }
    BigDecimal getPd13() { return calcJugo("jDiluido") }
    BigDecimal getPd14() { return calcJugo("jClaro") }
    BigDecimal getPd15() { return calcJugo("jFiltrado") }
    
    BigDecimal getPd16() { return calcMiel("mClara") }
    BigDecimal getPd17() { return calcMiel("mielA") }
    BigDecimal getPd18() { return calcMiel("mielB") }
    BigDecimal getPd19() { return calcMiel("mielF") }

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
        j1Extracto [ cto24H.pd312 ],
        jDiluido   [ cto24H.pd313 ],
        jClaro     [ cto24H.pd314 ],
        jFiltrado  [ cto24H.pd315 ],
        mClara     [ cto24H.pd316 ],
        mielA      [ cto24H.pd317 ],
        mielB      [ cto24H.pd318 ],
        mielF      [ cto24H.pd319 ]
    """)
    Collection<Cto24HDetalle3>detalle3

    BigDecimal getPd311() { return detalle3[0] ? detalle3[0].cana1: 0 }
    BigDecimal getPd312() { return 0 }
    BigDecimal getPd313() { return 0 } 
    BigDecimal getPd314() { return 0 } 
    BigDecimal getPd315() { return 0 } 
    BigDecimal getPd316() { return 0 } 
    BigDecimal getPd317() { return 0 } 
    BigDecimal getPd318() { return 0 } 
    BigDecimal getPd319() { return 0 } 
    
    BigDecimal getPd321() { return detalle3[0] ? detalle3[0].cana2: 0 }
    BigDecimal getPd322() { return 0 }
    BigDecimal getPd323() { return 0 } 
    BigDecimal getPd324() { return 0 } 
    BigDecimal getPd325() { return 0 } 
    BigDecimal getPd326() { return 0 } 
    BigDecimal getPd327() { return 0 } 
    BigDecimal getPd328() { return 0 } 
    BigDecimal getPd329() { return 0 } 
    
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

    BigDecimal getPromMlTitu()  { return super.getPromedio(detalle, "mlTitu",  2) }
    BigDecimal getPromFd    ()  { return super.getPromedio(detalle, "fd",      2) }
    BigDecimal getPromPpm   ()  { return super.getPromedio(detalle, "ppm",     2) }

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

    //@OnChange(Cto24HAction.class) @Required
    @Digits(integer=3, fraction=3) @DisplaySize(6) 
    BigDecimal fFelining

    // Métodos de cálculos
    def calcJugo (def atributo){
        def valor = 0.0
        detalle1.each {
            def v = (BigDecimal)Eval.x(it, "x."+atributo)
            valor = Calculo.instance.redondear((5.127 / (v * fFelining * 0.02)), 2)
        } 
        return valor 
    }

    def calcMiel (def atributo){
        def valor = 0.0
        detalle1.each {
            def v = (BigDecimal)Eval.x(it, "x."+atributo)
            valor = Calculo.instance.redondear((5.127 / (v * fFelining * 0.005)), 2)
        }
        return valor
    }
}
