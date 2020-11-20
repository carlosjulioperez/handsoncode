package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*
import org.openxava.calculators.*

class TrashDetalleAction extends OnChangePropertyBaseAction{

    private limpiar(def campo1, def campo2){
        getView().setValue(campo1, 0)
        getView().setValue(campo2, 0)
    }

    void execute() throws Exception{

        BigDecimal cogollos      = (BigDecimal)getView().getValue("cogollos")
        BigDecimal cantidadCana  = (BigDecimal)getView().getValue("cantidadCana")
        BigDecimal netaCana      = (BigDecimal)getView().getValue("netaCana")
        BigDecimal hojas         = (BigDecimal)getView().getValue("hojas")

        BigDecimal cepa          = (BigDecimal)getView().getValue("cepa")
        BigDecimal canaSeca      = (BigDecimal)getView().getValue("canaSeca")
        BigDecimal suelo         = (BigDecimal)getView().getValue("suelo")
        BigDecimal otros         = (BigDecimal)getView().getValue("otros")
        BigDecimal canaInfectada = (BigDecimal)getView().getValue("canaInfectada")

        // Valores iniciales
        if (cogollos==null)      limpiar("cogollos"     , "calPorcCogollos"      )
        if (hojas==null)         limpiar("hojas"        , "calPorcHojas"         )
        if (cepa==null)          limpiar("cepa"         , "calPorcCepa"          )
        if (canaSeca==null)      limpiar("canaSeca"     , "calPorcCanaSeca"      )
        if (suelo==null)         limpiar("suelo"        , "calPorcSuelo"         )
        if (otros==null)         limpiar("otros"        , "calPorcOtros"         )
        if (canaInfectada==null) limpiar("canaInfectada", "calPorcCanaInfectada" )

        // println ">>>" + cogollos
        // println ">>>" + cantidadCana
        // println("values=" + getView().getValues());
    
        // Ni cantidadCana ni netaCana pueden ser cero.
        if (cantidadCana){ //Groovy valida como verdadero: Non-zero numbers are true. 
            if (cogollos>=0)
               getView().setValue("calPorcCogollos", Calculo.instance.getPorc(cogollos, cantidadCana, 2))

            if (hojas>=0)
               getView().setValue("calPorcHojas", Calculo.instance.getPorc(hojas, cantidadCana, 2))
            
            if (cepa>=0)
               getView().setValue("calPorcCepa", Calculo.instance.getPorc(cepa, cantidadCana, 2))
            
            if (canaSeca>=0)
               getView().setValue("calPorcCanaSeca", Calculo.instance.getPorc(canaSeca, cantidadCana, 2))
            
            if (suelo>=0)
               getView().setValue("calPorcSuelo", Calculo.instance.getPorc(suelo, cantidadCana, 2))
            
            if (otros>=0)
               getView().setValue("calPorcOtros", Calculo.instance.getPorc(otros, cantidadCana, 2))
        }
        
        if (canaInfectada>=0 && netaCana)
           getView().setValue("calPorcCanaInfectada", Calculo.instance.getPorc(canaInfectada, netaCana, 2))
        
    }
}
