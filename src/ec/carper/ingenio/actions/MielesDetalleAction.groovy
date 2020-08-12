package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class MielesDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null) 
        
        BigDecimal maBri  = (BigDecimal)getView().getValue("maBri")
        BigDecimal maBri2 = (BigDecimal)getView().getValue("maBri2")
        BigDecimal maPol  = (BigDecimal)getView().getValue("maPol")
        getView().setValue("maSac", (maBri && maPol) ? Calculo.instance.getSac(maBri, maPol, 6, 2): null)
        
        BigDecimal maSac = (BigDecimal)getView().getValue("maSac")
        getView().setValue("maPur", (maSac && maBri2) ? Calculo.instance.getPorc(maSac, maBri2, 2): null)
        getView().setValue("maBri2", maBri ? Calculo.instance.redondear(maBri*6, 2): null)

        BigDecimal mbBri  = (BigDecimal)getView().getValue("mbBri")
        BigDecimal mbBri2 = (BigDecimal)getView().getValue("mbBri2")
        BigDecimal mbPol  = (BigDecimal)getView().getValue("mbPol")
        getView().setValue("mbSac", (mbBri && mbPol) ? Calculo.instance.getSac(mbBri, mbPol, 6, 2): null)
        
        BigDecimal mbSac = (BigDecimal)getView().getValue("mbSac")
        getView().setValue("mbPur", (mbSac && mbBri2) ? Calculo.instance.getPorc(mbSac, mbBri2, 2): null)

        getView().setValue("mbBri2", mbBri ? Calculo.instance.redondear(mbBri*6, 2): null)

        BigDecimal mfBri  = (BigDecimal)getView().getValue("mfBri")
        BigDecimal mfBri2 = (BigDecimal)getView().getValue("mfBri2")
        BigDecimal mfPol  = (BigDecimal)getView().getValue("mfPol")
            
        getView().setValue("mfSac", (mfBri && mfPol) ? Calculo.instance.redondear(mfPol*6, 2): null)
        
        BigDecimal mfSac = (BigDecimal)getView().getValue("mfSac")
        getView().setValue("mfPur", (mfSac && mfBri2) ? Calculo.instance.getPorc(mfSac, mfBri2, 2): null)
        getView().setValue("mfBri2", mfBri ? Calculo.instance.redondear(mfBri*6, 2): null)

        BigDecimal mrBri  = (BigDecimal)getView().getValue("mrBri")
        BigDecimal mrBri2 = (BigDecimal)getView().getValue("mrBri2")
        BigDecimal mrPol  = (BigDecimal)getView().getValue("mrPol")
            
        getView().setValue("mrSac", (mrBri && mrPol) ? Calculo.instance.redondear(mrPol*6, 2): null)
        
        BigDecimal mrSac = (BigDecimal)getView().getValue("mrSac")
        getView().setValue("mrPur", (mrSac && mrBri2) ? Calculo.instance.getPorc(mrSac, mrBri2, 2): null)

        getView().setValue("mrBri2", mrBri ? Calculo.instance.redondear(mrBri*6, 2): null)

        BigDecimal mpBri  = (BigDecimal)getView().getValue("mpBri")
        BigDecimal mpBri2 = (BigDecimal)getView().getValue("mpBri2")
        BigDecimal mpPol  = (BigDecimal)getView().getValue("mpPol")
        getView().setValue("mpSac", (mpBri && mpPol) ? Calculo.instance.getSac(mpBri, mpPol, 6, 2): null)
        
        BigDecimal mpSac = (BigDecimal)getView().getValue("mpSac")
        getView().setValue("mpPur", (mpSac && mpBri2) ? Calculo.instance.getPorc(mpSac, mpBri2, 2): null)
        getView().setValue("mpBri2", mpBri ? Calculo.instance.redondear(mpBri*6, 2): null)

    }
    
}
