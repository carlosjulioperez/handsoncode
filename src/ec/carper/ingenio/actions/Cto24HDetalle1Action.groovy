package ec.carper.ingenio.actions

import ec.carper.ingenio.util.Calculo
import ec.carper.ingenio.model.Parametro

import org.openxava.actions.*

class Cto24HDetalle1Action extends OnChangePropertyBaseAction{
    
    BigDecimal fFelining

    void execute() throws Exception{

        def parametro = new Parametro()
        fFelining = new BigDecimal(parametro.obtenerValor("CTO24H_FACTOR_FELINING"))

        def cana       = (BigDecimal)getView().getValue("cana")
        def j1Extracto = (BigDecimal)getView().getValue("j1Extracto")
        def jDiluido   = (BigDecimal)getView().getValue("jDiluido")
        def jClaro     = (BigDecimal)getView().getValue("jClaro")
        def jFiltrado  = (BigDecimal)getView().getValue("jFiltrado")
        def mClara     = (BigDecimal)getView().getValue("mClara")
        def mielA      = (BigDecimal)getView().getValue("mielA")
        def mielB      = (BigDecimal)getView().getValue("mielB")
        def mielF      = (BigDecimal)getView().getValue("mielF")

        getView().setValue("pd11", (fFelining && cana) ? Calculo.instance.redondear((calcJugo(cana)*3), 2) : 0 )
        
        getView().setValue("pd12", (fFelining && j1Extracto) ? calcJugo(j1Extracto) : 0 )
        getView().setValue("pd13", (fFelining && jDiluido)   ? calcJugo(jDiluido) : 0 )
        getView().setValue("pd14", (fFelining && jClaro)     ? calcJugo(jClaro) : 0 )
        getView().setValue("pd15", (fFelining && jFiltrado)  ? calcJugo(jFiltrado) : 0 )
        
        getView().setValue("pd16", (fFelining && mClara) ? calcMiel(mClara) : 0 )
        getView().setValue("pd17", (fFelining && mielA)  ? calcMiel(mielA) : 0 )
        getView().setValue("pd18", (fFelining && mielB)  ? calcMiel(mielB) : 0 )
        getView().setValue("pd19", (fFelining && mielF)  ? calcMiel(mielF) : 0 )

    }
    
    def calcJugo (def atributo){
        return Calculo.instance.redondear((5.127 / (atributo * fFelining * 0.02)), 2)
    }

    def calcMiel (def atributo){
        return Calculo.instance.redondear((5.127 / (atributo * fFelining * 0.005)), 2)
    }
    
}
