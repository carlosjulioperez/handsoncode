package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo

import java.sql.Timestamp
import org.openxava.actions.*

//TODO: Renombrar
class Cto24HDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        BigDecimal fr     = (BigDecimal)getView().getRoot().getValue("fr")
        
        BigDecimal mlTitu = (BigDecimal)getView().getValue("mlTitu")
        BigDecimal fd     = (BigDecimal)getView().getValue("fd")

        // =+(Q27*0,1*60000)/($T$37*100)*R27
        if (fr && mlTitu && fd)
            getView().setValue("ppm", Calculo.instance.redondear((mlTitu*0.1*60000)/(fr*100)*fd, 2) )

    }
}
