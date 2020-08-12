package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class MeladuraDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null)
        
        BigDecimal mcrBri  = (BigDecimal)getView().getValue("mcrBri")
        BigDecimal mcrBri2 = (BigDecimal)getView().getValue("mcrBri2")
        BigDecimal mcrPol  = (BigDecimal)getView().getValue("mcrPol")
        getView().setValue("mcrSac", (mcrBri && mcrPol) ? Calculo.instance.getSac(mcrBri, mcrPol, 4, 2): null)
        
        BigDecimal mcrSac = (BigDecimal)getView().getValue("mcrSac")
        getView().setValue("mcrPur", (mcrSac && mcrBri2) ? Calculo.instance.getPorc(mcrSac, mcrBri2, 2): null)
        getView().setValue("mcrBri2", mcrBri ? Calculo.instance.redondear(mcrBri*4, 2): null)

        BigDecimal mclBri  = (BigDecimal)getView().getValue("mclBri")
        BigDecimal mclBri2 = (BigDecimal)getView().getValue("mclBri2")
        BigDecimal mclPol  = (BigDecimal)getView().getValue("mclPol")
        getView().setValue("mclSac", (mclBri && mclPol) ? Calculo.instance.getSac(mclBri, mclPol, 4, 2): null)
        
        BigDecimal mclSac = (BigDecimal)getView().getValue("mclSac")
        getView().setValue("mclPur", (mclSac && mclBri2) ? Calculo.instance.getPorc(mclSac, mclBri2, 2): null)

        getView().setValue("mclBri2", mclBri ? Calculo.instance.redondear(mclBri*4, 2): null)
    }
    
}
