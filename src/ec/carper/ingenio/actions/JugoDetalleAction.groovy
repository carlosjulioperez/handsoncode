package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class JugoDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajo = SqlUtil.instance.getDiaTrabajo(getView().getRoot().getValue("diaTrabajo.id"))
        String horaS = (String)getView().getValue("horaS")
        if (horaS)
            getView().setValue("hora", Util.instance.toTimestamp(horaS, diaTrabajo.fecha)) 
        BigDecimal jeBri = (BigDecimal)getView().getValue("jeBri")
        BigDecimal jePol = (BigDecimal)getView().getValue("jePol")
        if (jeBri && jePol)
            getView().setValue("jeSac", Calculo.instance.getSac(jeBri, jePol,1,2))
        BigDecimal jeSac = (BigDecimal)getView().getValue("jeSac")
        if (jeSac && jeBri)
            getView().setValue("jePur", Calculo.instance.getPorc(jeSac, jeBri,2))

        BigDecimal jdBri = (BigDecimal)getView().getValue("jdBri")
        BigDecimal jdPol = (BigDecimal)getView().getValue("jdPol")
        if (jdBri && jdPol)
            getView().setValue("jdSac", Calculo.instance.getSac(jdBri, jdPol,1,2))
        BigDecimal jdSac = (BigDecimal)getView().getValue("jdSac")
        if (jdSac && jdBri)
            getView().setValue("jdPur", Calculo.instance.getPorc(jdSac, jdBri,2))

        BigDecimal jcBri = (BigDecimal)getView().getValue("jcBri")
        BigDecimal jcPol = (BigDecimal)getView().getValue("jcPol")
        if (jcBri && jcPol)
            getView().setValue("jcSac", Calculo.instance.getSac(jcBri, jcPol,1,2))
        BigDecimal jcSac = (BigDecimal)getView().getValue("jcSac")
        if (jcSac && jcBri)
            getView().setValue("jcPur", Calculo.instance.getPorc(jcSac, jcBri,2))

        BigDecimal jnBri = (BigDecimal)getView().getValue("jnBri")
        BigDecimal jnPol = (BigDecimal)getView().getValue("jnPol")
        if (jnBri && jnPol)
            getView().setValue("jnSac", Calculo.instance.getSac(jnBri, jnPol,1,2))
        BigDecimal jnSac = (BigDecimal)getView().getValue("jnSac")
        if (jnSac && jnBri)
            getView().setValue("jnPur", Calculo.instance.getPorc(jnSac, jnBri,2))

        BigDecimal jrBri = (BigDecimal)getView().getValue("jrBri")
        BigDecimal jrPol = (BigDecimal)getView().getValue("jrPol")
        if (jrBri && jrPol)
            getView().setValue("jrSac", Calculo.instance.getSac(jrBri, jrPol,1,2))
        BigDecimal jrSac = (BigDecimal)getView().getValue("jrSac")
        if (jrSac && jrBri)
            getView().setValue("jrPur", Calculo.instance.getPorc(jrSac, jrBri,2))

        BigDecimal jfBri = (BigDecimal)getView().getValue("jfBri")
        BigDecimal jfPol = (BigDecimal)getView().getValue("jfPol")
        if (jfBri && jfPol)
            getView().setValue("jfSac", Calculo.instance.getSac(jfBri, jfPol,1,2))
        BigDecimal jfSac = (BigDecimal)getView().getValue("jfSac")
        if (jfSac && jfBri)
            getView().setValue("jfPur", Calculo.instance.getPorc(jfSac, jfBri,2))

    }

}
