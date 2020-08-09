package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class MielesDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        if (horaS)
            getView().setValue("hora", SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId)) 
        
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

        BigDecimal mfBri  = (BigDecimal)getView().getValue("mfBri")
        BigDecimal mfBri2 = (BigDecimal)getView().getValue("mfBri2")
        BigDecimal mfPol  = (BigDecimal)getView().getValue("mfPol")
        if (mfBri && mfPol)
            getView().setValue("mfSac", Calculo.instance.redondear(mfPol*6, 2))
        
        BigDecimal mfSac = (BigDecimal)getView().getValue("mfSac")
        if (mfSac && mfBri2)
            getView().setValue("mfPur", Calculo.instance.getPorc(mfSac, mfBri2, 2))

        if (mfBri)
            getView().setValue("mfBri2", (mfBri*6).setScale(2, BigDecimal.ROUND_HALF_UP))

        BigDecimal mrBri  = (BigDecimal)getView().getValue("mrBri")
        BigDecimal mrBri2 = (BigDecimal)getView().getValue("mrBri2")
        BigDecimal mrPol  = (BigDecimal)getView().getValue("mrPol")
        if (mrBri && mrPol)
            getView().setValue("mrSac", Calculo.instance.redondear(mrPol*6, 2))
        
        BigDecimal mrSac = (BigDecimal)getView().getValue("mrSac")
        if (mrSac && mrBri2)
            getView().setValue("mrPur", Calculo.instance.getPorc(mrSac, mrBri2, 2))

        if (mrBri)
            getView().setValue("mrBri2", (mrBri*6).setScale(2, BigDecimal.ROUND_HALF_UP))

        BigDecimal mpBri  = (BigDecimal)getView().getValue("mpBri")
        BigDecimal mpBri2 = (BigDecimal)getView().getValue("mpBri2")
        BigDecimal mpPol  = (BigDecimal)getView().getValue("mpPol")
        if (mpBri && mpPol)
            getView().setValue("mpSac", Calculo.instance.getSac(mpBri, mpPol, 6, 2))
        
        BigDecimal mpSac = (BigDecimal)getView().getValue("mpSac")
        if (mpSac && mpBri2)
            getView().setValue("mpPur", Calculo.instance.getPorc(mpSac, mpBri2, 2))

        if (mpBri)
            getView().setValue("mpBri2", (mpBri*6).setScale(2, BigDecimal.ROUND_HALF_UP))

    }
    
}
