package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*

class TrashDetalleAction extends OnChangePropertyBaseAction{

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
        if (cogollos==null)      { getView().setValue("cogollos"      , 0); getView().setValue("calPorcCogollos"      , 0) }
        if (hojas==null)         { getView().setValue("hojas"         , 0); getView().setValue("calPorcHojas"         , 0) }
        if (cepa==null)          { getView().setValue("cepa"          , 0); getView().setValue("calPorcCepa"          , 0) }
        if (canaSeca==null)      { getView().setValue("canaSeca"      , 0); getView().setValue("calPorcCanaSeca"      , 0) }
        if (suelo==null)         { getView().setValue("suelo"         , 0); getView().setValue("calPorcSuelo"         , 0) }
        if (otros==null)         { getView().setValue("otros"         , 0); getView().setValue("calPorcOtros"         , 0) }
        if (canaInfectada==null) { getView().setValue("canaInfectada" , 0); getView().setValue("calPorcCanaInfectada" , 0) }

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
