package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import java.sql.Timestamp
import org.openxava.actions.*

class TurbiedadDetalle1Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{
        //abs900Nm, turJClaro

        BigDecimal abs900Nm = (BigDecimal)getView().getValue("abs900Nm")
        if (abs900Nm)
            getView().setValue("turJClaro", (abs900Nm*100).setScale(2, BigDecimal.ROUND_HALF_UP) )
        
    }

}
