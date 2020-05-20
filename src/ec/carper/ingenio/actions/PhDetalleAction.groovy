package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import java.sql.Timestamp
import org.openxava.actions.*

class PhDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{
        String diaTrabajoId   = (String)getView().getRoot().getValue("diaTrabajo.id")
        Timestamp horaTJClaro = (Timestamp)getView().getValue("horaTJClaro")
        
        if (diaTrabajoId && horaTJClaro ){
            getView().setValue("tJClaro", (
                new TurbiedadDetalle1().getValorTurJClaro(diaTrabajoId, horaTJClaro) 
            ).setScale(2, BigDecimal.ROUND_HALF_UP))
        }
    }

}
