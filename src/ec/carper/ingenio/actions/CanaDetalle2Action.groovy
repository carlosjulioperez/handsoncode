package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import java.sql.Timestamp
import org.openxava.actions.*

class CanaDetalle2Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        Timestamp horaDesde     = (Timestamp)getView().getValue("horaDesde")
        Timestamp horaHasta     = (Timestamp)getView().getValue("horaHasta")
        BigDecimal brixExtracto = (BigDecimal)getView().getValue("brixExtracto")
        BigDecimal polExtracto  = (BigDecimal)getView().getValue("polExtracto")

        // =(U6*0,26)/(0,9971883+0,00385310413*T6+0,0000132218495*T6*T6+0,00000004655189*T6*T6*T6)
        if (brixExtracto && polExtracto) {
            getView().setValue("polReal", (
               (polExtracto*0.26)/(0.9971883 + 0.00385310413*brixExtracto + 0.0000132218495*brixExtracto*brixExtracto + 0.00000004655189*brixExtracto*brixExtracto*brixExtracto) 
            ).setScale(2, BigDecimal.ROUND_HALF_UP))
        }
        
        // println("values=" + getView().getRoot().getValues());
        // println("values=" + getView().getValues());
        String diaTrabajoId = getView().getRoot().getValue("diaTrabajo.id")

        if (horaDesde && horaHasta){

        }

    }
}
