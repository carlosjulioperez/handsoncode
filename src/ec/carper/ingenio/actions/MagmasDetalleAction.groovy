package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class MagmasDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null)
        
        BigDecimal mbBri  = (BigDecimal)getView().getValue("mbBri")
        BigDecimal mbBri2 = (BigDecimal)getView().getValue("mbBri2")
        BigDecimal mbPol  = (BigDecimal)getView().getValue("mbPol")
        getView().setValue("mbSac", (mbBri && mbPol) ? Calculo.instance.getSac(mbBri, mbPol, 6, 2): null)
        BigDecimal mbSac = (BigDecimal)getView().getValue("mbSac")
        getView().setValue("mbPur", (mbSac && mbBri2) ? Calculo.instance.getPorc(mbSac, mbBri2, 2): null)
        getView().setValue("mbBri2", mbBri ? Calculo.instance.redondear(mbBri*6, 2): null)

        BigDecimal mcBri  = (BigDecimal)getView().getValue("mcBri")
        BigDecimal mcBri2 = (BigDecimal)getView().getValue("mcBri2")
        BigDecimal mcPol  = (BigDecimal)getView().getValue("mcPol")
        getView().setValue("mcSac", (mcBri && mcPol) ? Calculo.instance.getSac(mcBri, mcPol, 6, 2): null)
        BigDecimal mcSac = (BigDecimal)getView().getValue("mcSac")
        getView().setValue("mcPur", (mcSac && mcBri2) ? Calculo.instance.getPorc(mcSac, mcBri2, 2): null)
        getView().setValue("mcBri2", mcBri ? Calculo.instance.redondear(mcBri*6, 2): null)

        BigDecimal mrBri  = (BigDecimal)getView().getValue("mrBri")
        BigDecimal mrBri2 = (BigDecimal)getView().getValue("mrBri2")
        BigDecimal mrPol  = (BigDecimal)getView().getValue("mrPol")
        getView().setValue("mrSac", (mrBri && mrPol) ? Calculo.instance.getSac(mrBri, mrPol, 6, 2): null)
        BigDecimal mrSac = (BigDecimal)getView().getValue("mrSac")
        getView().setValue("mrPur", (mrSac && mrBri2) ? Calculo.instance.getPorc(mrSac, mrBri2, 2): null)
        getView().setValue("mrBri2", mrBri ? Calculo.instance.redondear(mrBri*6, 2): null)

    }
    
}
