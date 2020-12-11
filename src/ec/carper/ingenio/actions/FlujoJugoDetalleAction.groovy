package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

// import org.openxava.jpa.*
// import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

class FlujoJugoDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{
        
        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null) 
        
        String horaSBrixJDil = (String)getView().getValue("horaSBrixJDil")
        getView().setValue("horaBrixJDil", (diaTrabajoId && horaSBrixJDil) ? SqlUtil.instance.obtenerFecha(horaSBrixJDil, diaTrabajoId): null ) 
        
        def ini          = getView().getValue("ini")
        def fin          = getView().getValue("fin")
        def horaBrixJDil = getView().getValue("horaBrixJDil")

        getView().setValue("tot", (ini>=0 && fin>=0) ? fin-ini: null)
        
        // Determinar si existe un siguiente registro y asignar el valor inicial como el valor final recién ingresado
        def hora  = getView().getValue("hora")
        hora = Util.instance.agregarHora(hora) // Incremento de hora
        def horaN = Util.instance.getHoraS(hora) // String de la siguiente hora
        def d = SqlUtil.instance.getDetallePorHora(diaTrabajoId, "flujoJugo", "FlujoJugoDetalle", horaN)
        if (d){
            d.ini = fin
            getManager().persist(d)
        }

        //TODO 
        if (horaBrixJDil)
            getView().setValue("brixJDil",  SqlUtil.instance.getValorDetalleCampoXHora(diaTrabajoId, horaBrixJDil, "jugo", "JugoDetalle", "jdBri") )
        
        def brixJDil = getView().getValue("brixJDil")
        brixJDil = brixJDil?:0

        getView().setValue("p", brixJDil>=0 ? new BrixDensidadWp().getP(brixJDil): 0)

        def tot = getView().getValue("tot")
        tot = tot?:0

        def p   = getView().getValue("p")
        p = p?:0

        def vTot = (tot != null) ? Math.abs(tot): 0
        getView().setValue("tonJugo", (p>=0) ? Calculo.instance.redondear(tot*p/1000,6) : 0)
    }

}
