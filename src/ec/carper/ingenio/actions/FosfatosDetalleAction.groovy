package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*

class FosfatosDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        if (horaS)
            getView().setValue("hora", SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId))

        BigDecimal cteN1         = (BigDecimal)getView().getRoot().getValue("cteN1")
        BigDecimal cteN2         = (BigDecimal)getView().getRoot().getValue("cteN2")
        
        BigDecimal jdAbsorbancia = (BigDecimal)getView().getValue("jdAbsorbancia")
        BigDecimal jdMlMuestra   = (BigDecimal)getView().getValue("jdMlMuestra")

        // =+C21*$K$3-$K$4
        if (jdAbsorbancia && cteN1 && cteN2)
            getView().setValue("jdMgP", (jdAbsorbancia * cteN1 - cteN2).setScale(3, BigDecimal.ROUND_HALF_UP))
        
        // =+E21*(1000/D21)
        BigDecimal jdMgP = (BigDecimal)getView().getValue("jdMgP")
        if (jdMgP && jdMlMuestra)
            getView().setValue("jdFosfatos", (jdMgP*(1000/jdMlMuestra)).setScale(3, BigDecimal.ROUND_HALF_UP))
        
        BigDecimal jcAbsorbancia = (BigDecimal)getView().getValue("jcAbsorbancia")
        BigDecimal jcMlMuestra   = (BigDecimal)getView().getValue("jcMlMuestra")

        // =+C21*$K$3-$K$4
        if (jcAbsorbancia && cteN1 && cteN2)
            getView().setValue("jcMgP", (jcAbsorbancia * cteN1 - cteN2).setScale(3, BigDecimal.ROUND_HALF_UP))
        
        // =+E21*(1000/D21)
        BigDecimal jcMgP = (BigDecimal)getView().getValue("jcMgP")
        if (jcMgP && jcMlMuestra)
            getView().setValue("jcFosfatos", (jcMgP*(1000/jcMlMuestra)).setScale(3, BigDecimal.ROUND_HALF_UP))
    }
    
}
