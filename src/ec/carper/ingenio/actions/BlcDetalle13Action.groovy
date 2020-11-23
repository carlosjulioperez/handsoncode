package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import javax.persistence.Query
import org.openxava.actions.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*

class BlcDetalle13Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        def diaTrabajo   = SqlUtil.instance.getDiaTrabajo(diaTrabajoId)
        def diaFin       = diaTrabajo.numeroDia - 1

        def orden        = getView().getValue("orden")
        def indicadorId  = (String)getView().getValue("indicador.id")
        def indicador    = SqlUtil.instance.getIndicador(indicadorId)
        def unidades     = (BigDecimal)getView().getValue("unidades")
        
        def acumulado    = SqlUtil.instance.getValIndBlcAcu("BlcDetalle13" , indicador.campo, diaFin)
        
        def acu = acumulado?:0
        def uni = unidades ?:0

        getView().setValue( "acumulado"  , acu )
        getView().setValue( "totalZafra" , acu + uni )

        // Si el usuario ingresa unidades en los subtítulos, poner nulo
        // http://groovy-lang.org/semantics.html
        switch(orden){
            case [7, 13, 19, 24, 27, 31, 34, 36, 40, 45, 50, 53, 57]:
                getView().setValue("unidades", null)
                getView().setValue("acumulado", null)
                getView().setValue("totalZafra", null)
            break
        }
        
    }
}
