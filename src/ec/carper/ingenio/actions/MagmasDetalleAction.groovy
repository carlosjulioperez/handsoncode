package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class MagmasDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        if (horaS)
            getView().setValue("hora", SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId))
        
        BigDecimal mbBri  = (BigDecimal)getView().getValue("mbBri")
        BigDecimal mbBri2 = (BigDecimal)getView().getValue("mbBri2")
        BigDecimal mbPol  = (BigDecimal)getView().getValue("mbPol")
        if (mbBri && mbPol)
            getView().setValue("mbSac", Calculo.instance.getSac(mbBri, mbPol, 6, 2))
        
        BigDecimal mbSac = (BigDecimal)getView().getValue("mbSac")
        if (mbSac && mbBri2)
            getView().setValue("mbPur", Calculo.instance.getPorc(mbSac, mbBri2, 2))

        if (mbBri)
            getView().setValue("mbBri2", (mbBri*6).setScale(2, BigDecimal.ROUND_HALF_UP))

        BigDecimal mcBri  = (BigDecimal)getView().getValue("mcBri")
        BigDecimal mcBri2 = (BigDecimal)getView().getValue("mcBri2")
        BigDecimal mcPol  = (BigDecimal)getView().getValue("mcPol")
        if (mcBri && mcPol)
            getView().setValue("mcSac", Calculo.instance.getSac(mcBri, mcPol, 6, 2))
        
        BigDecimal mcSac = (BigDecimal)getView().getValue("mcSac")
        if (mcSac && mcBri2)
            getView().setValue("mcPur", Calculo.instance.getPorc(mcSac, mcBri2, 2))

        if (mcBri)
            getView().setValue("mcBri2", (mcBri*6).setScale(2, BigDecimal.ROUND_HALF_UP))

        BigDecimal mrBri  = (BigDecimal)getView().getValue("mrBri")
        BigDecimal mrBri2 = (BigDecimal)getView().getValue("mrBri2")
        BigDecimal mrPol  = (BigDecimal)getView().getValue("mrPol")
        if (mrBri && mrPol)
            getView().setValue("mrSac", Calculo.instance.getSac(mrBri, mrPol, 6, 2))
        
        BigDecimal mrSac = (BigDecimal)getView().getValue("mrSac")
        if (mrSac && mrBri2)
            getView().setValue("mrPur", Calculo.instance.getPorc(mrSac, mrBri2, 2))

        if (mrBri)
            getView().setValue("mrBri2", (mrBri*6).setScale(2, BigDecimal.ROUND_HALF_UP))

    }
    
}
