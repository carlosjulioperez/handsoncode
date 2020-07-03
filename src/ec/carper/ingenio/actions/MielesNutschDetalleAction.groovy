package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class MielesNutschDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        def diaTrabajo = SqlUtil.instance.getDiaTrabajo(diaTrabajoId)
        String horaS = (String)getView().getValue("horaS")
        if (horaS)
            getView().setValue("hora", Util.instance.toTimestamp(horaS, diaTrabajo.fecha)) 
        
        BigDecimal maBri  = (BigDecimal)getView().getValue("maBri")
        BigDecimal maBri2 = (BigDecimal)getView().getValue("maBri2")
        BigDecimal maPol  = (BigDecimal)getView().getValue("maPol")
        if (maBri && maPol)
            getView().setValue("maSac", Calculo.instance.getSac(maBri, maPol, 6, 2))
        
        BigDecimal maSac = (BigDecimal)getView().getValue("maSac")
        if (maSac && maBri2)
            getView().setValue("maPur", Calculo.instance.getPorc(maSac, maBri2, 2))
        
        if (maBri)
            getView().setValue("maBri2", (maBri*6).setScale(2, BigDecimal.ROUND_HALF_UP))

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

    }
    
}
