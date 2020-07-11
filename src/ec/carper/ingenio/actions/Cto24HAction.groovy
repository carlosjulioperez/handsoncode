package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo
import ec.carper.ingenio.util.SqlUtil

import java.sql.Timestamp
import org.openxava.actions.*

class Cto24HAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        String diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")

        BigDecimal ff = (BigDecimal)getView().getValue("fFelining")
    }

}
