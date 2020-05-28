package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo

import java.sql.Timestamp
import org.openxava.actions.*

class MeladuraDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        BigDecimal mcrBri  = (BigDecimal)getView().getValue("mcrBri")
        BigDecimal mcrBri2 = (BigDecimal)getView().getValue("mcrBri2")
        BigDecimal mcrPol  = (BigDecimal)getView().getValue("mcrPol")
        if (mcrBri && mcrPol)
            getView().setValue("mcrSac", Calculo.instance.getSac(mcrBri, mcrPol, 4, 2))
        
        BigDecimal mcrSac = (BigDecimal)getView().getValue("mcrSac")
        if (mcrSac && mcrBri2)
            getView().setValue("mcrPur", Calculo.instance.getPorc(mcrSac, mcrBri2, 2))
        
        if (mcrBri)
            getView().setValue("mcrBri2", (mcrBri*4).setScale(2, BigDecimal.ROUND_HALF_UP))

        BigDecimal mclBri  = (BigDecimal)getView().getValue("mclBri")
        BigDecimal mclBri2 = (BigDecimal)getView().getValue("mclBri2")
        BigDecimal mclPol  = (BigDecimal)getView().getValue("mclPol")
        if (mclBri && mclPol)
            getView().setValue("mclSac", Calculo.instance.getSac(mclBri, mclPol, 4, 2))
        
        BigDecimal mclSac = (BigDecimal)getView().getValue("mclSac")
        if (mclSac && mclBri2)
            getView().setValue("mclPur", Calculo.instance.getPorc(mclSac, mclBri2, 2))

        if (mclBri)
            getView().setValue("mclBri2", (mclBri*4).setScale(2, BigDecimal.ROUND_HALF_UP))
    }
    
}
