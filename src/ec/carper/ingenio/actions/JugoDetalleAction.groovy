package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class JugoDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null) 
        
        BigDecimal jeBri = (BigDecimal)getView().getValue("jeBri")
        BigDecimal jePol = (BigDecimal)getView().getValue("jePol")
        getView().setValue("jeSac", (jeBri && jePol) ? Calculo.instance.getSac(jeBri, jePol,1,2): null)
        BigDecimal jeSac = (BigDecimal)getView().getValue("jeSac")
        getView().setValue("jePur", (jeSac && jeBri) ? Calculo.instance.getPorc(jeSac, jeBri,2): null)

        BigDecimal jdBri = (BigDecimal)getView().getValue("jdBri")
        BigDecimal jdPol = (BigDecimal)getView().getValue("jdPol")
        getView().setValue("jdSac", (jdBri && jdPol) ? Calculo.instance.getSac(jdBri, jdPol,1,2): null)
        BigDecimal jdSac = (BigDecimal)getView().getValue("jdSac")
        getView().setValue("jdPur", (jdSac && jdBri) ? Calculo.instance.getPorc(jdSac, jdBri,2): null)

        BigDecimal jcBri = (BigDecimal)getView().getValue("jcBri")
        BigDecimal jcPol = (BigDecimal)getView().getValue("jcPol")
        getView().setValue("jcSac", (jcBri && jcPol) ? Calculo.instance.getSac(jcBri, jcPol,1,2): null)
        BigDecimal jcSac = (BigDecimal)getView().getValue("jcSac")
        getView().setValue("jcPur", (jcSac && jcBri) ? Calculo.instance.getPorc(jcSac, jcBri,2): null)

        BigDecimal jnBri = (BigDecimal)getView().getValue("jnBri")
        BigDecimal jnPol = (BigDecimal)getView().getValue("jnPol")
        getView().setValue("jnSac", (jnBri && jnPol) ? Calculo.instance.getSac(jnBri, jnPol,1,2): null)
        BigDecimal jnSac = (BigDecimal)getView().getValue("jnSac")
        getView().setValue("jnPur", (jnSac && jnBri) ? Calculo.instance.getPorc(jnSac, jnBri,2): null)

        BigDecimal jrBri = (BigDecimal)getView().getValue("jrBri")
        BigDecimal jrPol = (BigDecimal)getView().getValue("jrPol")
        getView().setValue("jrSac", (jrBri && jrPol) ? Calculo.instance.getSac(jrBri, jrPol,1,2): null)
        BigDecimal jrSac = (BigDecimal)getView().getValue("jrSac")
        getView().setValue("jrPur", (jrSac && jrBri) ? Calculo.instance.getPorc(jrSac, jrBri,2): null)

        BigDecimal jfBri = (BigDecimal)getView().getValue("jfBri")
        BigDecimal jfPol = (BigDecimal)getView().getValue("jfPol")
        getView().setValue("jfSac", (jfBri && jfPol) ? Calculo.instance.getSac(jfBri, jfPol,1,2): null)
        BigDecimal jfSac = (BigDecimal)getView().getValue("jfSac")
        getView().setValue("jfPur", (jfSac && jfBri) ? Calculo.instance.getPorc(jfSac, jfBri,2): null)

        // getView().setValue("jfPur", (jfSac>=0 && jfBri>0) ? Calculo.instance.getPorc(jfSac, jfBri,2): 0)
        // getView().setValue("jfPur", (jfSac>=0 && jfBri>0) ? Calculo.instance.getPorc(jfSac, jfBri,2): 0)
        // getView().setValue("jfPur", (jfSac==null || jfBri==null) ? null : (jfBri>0 ? Calculo.instance.getPorc(jfSac, jfBri,2): 0 ) )

    }

}
