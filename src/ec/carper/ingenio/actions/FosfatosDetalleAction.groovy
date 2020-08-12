package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*

class FosfatosDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (horaS && diaTrabajoId) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null)

        BigDecimal cteN1         = (BigDecimal)getView().getRoot().getValue("cteN1")
        BigDecimal cteN2         = (BigDecimal)getView().getRoot().getValue("cteN2")
        
        BigDecimal jdAbsorbancia = (BigDecimal)getView().getValue("jdAbsorbancia")
        BigDecimal jdMlMuestra   = (BigDecimal)getView().getValue("jdMlMuestra")

        // =+C21*$K$3-$K$4
        getView().setValue("jdMgP", (jdAbsorbancia && cteN1 && cteN2) ? Calculo.instance.redondear(jdAbsorbancia * cteN1 - cteN2, 3): null)
        
        // =+E21*(1000/D21)
        BigDecimal jdMgP = (BigDecimal)getView().getValue("jdMgP")
        getView().setValue("jdFosfatos", (jdMgP && jdMlMuestra) ? Calculo.instance.redondear(jdMgP*(1000/jdMlMuestra) ,3): null)
        
        BigDecimal jcAbsorbancia = (BigDecimal)getView().getValue("jcAbsorbancia")
        BigDecimal jcMlMuestra   = (BigDecimal)getView().getValue("jcMlMuestra")

        // =+C21*$K$3-$K$4
        getView().setValue("jcMgP", (jcAbsorbancia && cteN1 && cteN2) ? Calculo.instance.redondear(jcAbsorbancia * cteN1 - cteN2, 3): null)
        
        // =+E21*(1000/D21)
        BigDecimal jcMgP = (BigDecimal)getView().getValue("jcMgP")
        getView().setValue("jcFosfatos", (jcMgP && jcMlMuestra) ? Calculo.instance.redondear(jcMgP*(1000/jcMlMuestra), 3): null)
    }
    
}
