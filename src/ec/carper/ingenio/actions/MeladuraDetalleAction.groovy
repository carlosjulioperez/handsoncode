package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import java.sql.Timestamp
import org.openxava.actions.*

class MeladuraDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        BigDecimal mcrBri  = (BigDecimal)getView().getValue("mcrBri")
        BigDecimal mcrBri2 = (BigDecimal)getView().getValue("mcrBri2")
        BigDecimal mcrPol  = (BigDecimal)getView().getValue("mcrPol")
        if (mcrBri && mcrPol)
            getView().setValue("mcrSac", getSac(mcrBri, mcrPol))
        
        BigDecimal mcrSac = (BigDecimal)getView().getValue("mcrSac")
        if (mcrSac && mcrBri2)
            getView().setValue("mcrPur", getPur(mcrSac, mcrBri2))
        
        if (mcrBri)
            getView().setValue("mcrBri2", (mcrBri*4).setScale(2, BigDecimal.ROUND_HALF_UP))

        BigDecimal mclBri  = (BigDecimal)getView().getValue("mclBri")
        BigDecimal mclBri2 = (BigDecimal)getView().getValue("mclBri2")
        BigDecimal mclPol  = (BigDecimal)getView().getValue("mclPol")
        if (mclBri && mclPol)
            getView().setValue("mclSac", getSac(mclBri, mclPol))
        
        BigDecimal mclSac = (BigDecimal)getView().getValue("mclSac")
        if (mclSac && mclBri2)
            getView().setValue("mclPur", getPur(mclSac, mclBri2))

        if (mclBri)
            getView().setValue("mclBri2", (mclBri*4).setScale(2, BigDecimal.ROUND_HALF_UP))
    }
    
    private BigDecimal getSac(BigDecimal bri, BigDecimal pol){
        // =((D7*0,26)/(0,9971883+0,00385310413*C7+0,0000132218495*C7*C7+0,00000004655189*C7*C7*C7))*4
        return (((pol*0.26)/(0.9971883+0.00385310413*bri+0.0000132218495*bri*bri+0.00000004655189*bri*bri*bri))*4).setScale(2, BigDecimal.ROUND_HALF_UP)
    }

    private BigDecimal getPur(BigDecimal sac, BigDecimal bri){
        return ((sac/bri)*100).setScale(2, BigDecimal.ROUND_HALF_UP) 
    }

}
