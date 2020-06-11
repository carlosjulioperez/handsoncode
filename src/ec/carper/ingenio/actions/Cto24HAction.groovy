package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo
import ec.carper.ingenio.util.SqlUtil

import java.sql.Timestamp
import org.openxava.actions.*

class Cto24HAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{
        /*
        atM1,atC1,atJ11,atJd1,atJc1,atJf1,atMc1,atMa1,atMb1,atMf1;
        atM2,atC2,atJ12,atJd2,atJc2,atJf2,atMc2,atMa2,atMb2,atMf2;
        BigDecimal = (BigDecimal)getView().getValue("")
        getView().setValue("", new BrixDensidadWp().getP())
        if ()
            getView().setValue("", calcJugo(, ff) )
        */

        String diaTrabajoId      = (String)getView().getRoot().getValue("diaTrabajo.id")

        BigDecimal ff = (BigDecimal)getView().getValue("fFelining")

        BigDecimal atC1  = (BigDecimal)getView().getValue("atC1")
        BigDecimal atJ11 = (BigDecimal)getView().getValue("atJ11")
        BigDecimal atJd1 = (BigDecimal)getView().getValue("atJd1")
        BigDecimal atJc1 = (BigDecimal)getView().getValue("atJc1")
        BigDecimal atJf1 = (BigDecimal)getView().getValue("atJf1")
        BigDecimal atMc1 = (BigDecimal)getView().getValue("atMc1")
        BigDecimal atMa1 = (BigDecimal)getView().getValue("atMa1")
        BigDecimal atMb1 = (BigDecimal)getView().getValue("atMb1")
        BigDecimal atMf1 = (BigDecimal)getView().getValue("atMf1")
        
        //println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        //println("values=" + getView().getValues());
        
        if (atC1 && ff)
            getView().setValue("atC2", Calculo.instance.redondear((calcJugo(atC1, ff)*3), 2))

        if (atJ11 && ff)
            getView().setValue("atJ12", calcJugo(atJ11, ff) )

        if (atJd1 && ff)
            getView().setValue("atJd2", calcJugo(atJd1, ff) )

        if (atJc1 && ff)
            getView().setValue("atJc2", calcJugo(atJc1, ff) )

        if (atJf1 && ff)
            getView().setValue("atJf2", calcJugo(atJf1, ff) )

        if (atMc1 && ff)
            getView().setValue("atMc2", calcMiel(atMc1, ff) )

        if (atMa1 && ff)
            getView().setValue("atMa2", calcMiel(atMa1, ff) )

        if (atMb1 && ff)
            getView().setValue("atMb2", calcMiel(atMb1, ff) )

        if (atMf1 && ff)
            getView().setValue("atMf2", calcMiel(atMf1, ff) )

        BigDecimal siC1 = (BigDecimal)getView().getValue("siC1")
        BigDecimal siC2 = (BigDecimal)getView().getValue("siC2")
        BigDecimal siC3 = (BigDecimal)getView().getValue("siC3")
        BigDecimal siC4 = (BigDecimal)getView().getValue("siC4")
        BigDecimal siC5 = (BigDecimal)getView().getValue("siC5")
        BigDecimal siC6 = (BigDecimal)getView().getValue("siC6")
        BigDecimal siC7 = (BigDecimal)getView().getValue("siC7")
        BigDecimal siC8 = (BigDecimal)getView().getValue("siC8")
        
        if (siC1 && siC2 && siC3 && siC4 && siC5)
            getView().setValue("siM1", siC1 + siC2 + siC3 + siC4 + siC5)
        
        BigDecimal siM1 = (BigDecimal)getView().getValue("siM1")
        if (siM1 && siC6)
            getView().setValue("siM2", siM1 + siC6)

        if (siC7 && siC8)
            getView().setValue("siM3", siC7 + siC8)

        // =((P8-N8)/(O8-N8))*100
        BigDecimal siM2 = (BigDecimal)getView().getValue("siM2")
        BigDecimal siM3 = (BigDecimal)getView().getValue("siM3")
        if (siM1 && siM2 && siM3)
            getView().setValue("siPorc", Calculo.instance.redondear((((siM3-siM1)/(siM2-siM1))*100), 2))

        BigDecimal arC1  = (BigDecimal)getView().getValue("arC1")
        BigDecimal arJ11 = (BigDecimal)getView().getValue("arJ11")
        BigDecimal arJd1 = (BigDecimal)getView().getValue("arJd1")
        BigDecimal arJc1 = (BigDecimal)getView().getValue("arJc1")
        BigDecimal arJf1 = (BigDecimal)getView().getValue("arJf1")
        BigDecimal arMc1 = (BigDecimal)getView().getValue("arMc1")
        BigDecimal arMa1 = (BigDecimal)getView().getValue("arMa1")
        BigDecimal arMb1 = (BigDecimal)getView().getValue("arMb1")
        BigDecimal arMf1 = (BigDecimal)getView().getValue("arMf1")
        
        if (arC1){
            getView().setValue("arC2", Calculo.instance.redondear(arC1/4, 2))
            BigDecimal arC2 = (BigDecimal)getView().getValue("arC2")
            getView().setValue("arC3", new BrixDensidadTitSus().getSusRed(arC2))
        }

        if (arJ11){
            getView().setValue("arJ12", Calculo.instance.redondear(arJ11/2, 2))
            BigDecimal arJ12 = (BigDecimal)getView().getValue("arJ12")
            getView().setValue("arJ13", new BrixDensidadTitSus().getSusRed(arJ12))
        }

        if (arJd1){
            getView().setValue("arJd2", Calculo.instance.redondear(arJd1/2, 2))
            BigDecimal arJd2 = (BigDecimal)getView().getValue("arJd2")
            getView().setValue("arJd3", new BrixDensidadTitSus().getSusRed(arJd2))
        }

        if (arJc1){
            getView().setValue("arJc2", Calculo.instance.redondear(arJc1/2, 2))
            BigDecimal arJc2 = (BigDecimal)getView().getValue("arJc2")
            getView().setValue("arJc3", new BrixDensidadTitSus().getSusRed(arJc2))
        }

        if (arJf1){
            getView().setValue("arJf2", Calculo.instance.redondear(arJf1/2, 2))
            BigDecimal arJf2 = (BigDecimal)getView().getValue("arJf2")
            getView().setValue("arJf3", new BrixDensidadTitSus().getSusRed(arJf2))
        }

        if (arMc1){
            getView().setValue("arMc2", new BrixDensidadTitSus().getSusRed(arMc1))
            BigDecimal arMc2 = (BigDecimal)getView().getValue("arMc2")
            getView().setValue("arMc3", Calculo.instance.redondear(arMc2*4, 2))
        }

        if (arMa1){
            getView().setValue("arMa2", new BrixDensidadTitSus().getSusRed(arMa1))
            BigDecimal arMa2 = (BigDecimal)getView().getValue("arMa2")
            getView().setValue("arMa3", Calculo.instance.redondear(arMa2*4, 2))
        }

        if (arMb1){
            getView().setValue("arMb2", new BrixDensidadTitSus().getSusRed(arMb1))
            BigDecimal arMb2 = (BigDecimal)getView().getValue("arMb2")
            getView().setValue("arMb3", Calculo.instance.redondear(arMb2*20, 2))
        }

        if (arMf1){
            getView().setValue("arMf2", new BrixDensidadTitSus().getSusRed(arMf1))
            BigDecimal arMf2 = (BigDecimal)getView().getValue("arMf2")
            getView().setValue("arMf3", Calculo.instance.redondear(arMf2*25, 2))
        }
        
        // CENIZAS CONDUCTIMETRICAS EN MATERIALES DE PROCESO
        // *************************************************
        BigDecimal ccJ11 = (BigDecimal)getView().getValue("ccJ11")
        BigDecimal ccJ12 = (BigDecimal)getView().getValue("ccJ12")
        BigDecimal ccJ13 = (BigDecimal)getView().getValue("ccJ13")
        BigDecimal ccJ14 = (BigDecimal)getView().getValue("ccJ14")
        BigDecimal ccJ15 = (BigDecimal)getView().getValue("ccJ15")
        if (diaTrabajoId)
            getView().setValue("ccJ14", SqlUtil.instance.getPromedio(diaTrabajoId, "Jugo", "jeBri"))
        if (ccJ12 && ccJ13)
            getView().setValue("ccJ16", calcC20 (ccJ12, ccJ13) )
        BigDecimal ccJ16 = (BigDecimal)getView().getValue("ccJ16")
        if (ccJ16 && ccJ11)
            getView().setValue("ccJ17", ccJ16 - ccJ11)
        if (ccJ14 && ccJ15)
            getView().setValue("ccJ18", Calculo.instance.getPorc2(ccJ14,ccJ15,3))
        if (ccJ15)
            getView().setValue("ccJ19", Calculo.instance.redondear(5/ccJ15,3))
        BigDecimal ccJ18 = (BigDecimal)getView().getValue("ccJ18")
        BigDecimal ccJ19 = (BigDecimal)getView().getValue("ccJ19")
        if (ccJ18 && ccJ16 && ccJ19)
            getView().setValue("ccJ10", porcCenizas(ccJ18, ccJ16, ccJ19) )

        BigDecimal ccJd1 = (BigDecimal)getView().getValue("ccJd1")
        BigDecimal ccJd2 = (BigDecimal)getView().getValue("ccJd2")
        BigDecimal ccJd3 = (BigDecimal)getView().getValue("ccJd3")
        BigDecimal ccJd4 = (BigDecimal)getView().getValue("ccJd4")
        BigDecimal ccJd5 = (BigDecimal)getView().getValue("ccJd5")
        if (diaTrabajoId)
            getView().setValue("ccJd4", SqlUtil.instance.getPromedio(diaTrabajoId, "Jugo", "jdBri"))
        if (ccJd2 && ccJd3)
            getView().setValue("ccJd6", calcC20 (ccJd2, ccJd3) )
        BigDecimal ccJd6 = (BigDecimal)getView().getValue("ccJd6")
        if (ccJd6 && ccJd1)
            getView().setValue("ccJd7", ccJd6 - ccJd1)
        if (ccJd4 && ccJd5)
            getView().setValue("ccJd8", Calculo.instance.getPorc2(ccJd4,ccJd5,3))
        if (ccJd5)
            getView().setValue("ccJd9", Calculo.instance.redondear(5/ccJd5,3))
        BigDecimal ccJd8 = (BigDecimal)getView().getValue("ccJd8")
        BigDecimal ccJd9 = (BigDecimal)getView().getValue("ccJd9")
        if (ccJd8 && ccJd6 && ccJd9)
            getView().setValue("ccJd0", porcCenizas(ccJd8, ccJd6, ccJd9) )

        BigDecimal ccJc1 = (BigDecimal)getView().getValue("ccJc1")
        BigDecimal ccJc2 = (BigDecimal)getView().getValue("ccJc2")
        BigDecimal ccJc3 = (BigDecimal)getView().getValue("ccJc3")
        BigDecimal ccJc4 = (BigDecimal)getView().getValue("ccJc4")
        BigDecimal ccJc5 = (BigDecimal)getView().getValue("ccJc5")
        if (diaTrabajoId)
            getView().setValue("ccJc4", SqlUtil.instance.getPromedio(diaTrabajoId, "Jugo", "jcBri"))
        if (ccJc2 && ccJc3)
            getView().setValue("ccJc6", calcC20 (ccJc2, ccJc3) )
        BigDecimal ccJc6 = (BigDecimal)getView().getValue("ccJc6")
        if (ccJc6 && ccJc1)
            getView().setValue("ccJc7", ccJc6 - ccJc1)
        if (ccJc4 && ccJc5)
            getView().setValue("ccJc8", Calculo.instance.getPorc2(ccJc4,ccJc5,3))
        if (ccJc5)
            getView().setValue("ccJc9", Calculo.instance.redondear(5/ccJc5,3))
        BigDecimal ccJc8 = (BigDecimal)getView().getValue("ccJc8")
        BigDecimal ccJc9 = (BigDecimal)getView().getValue("ccJc9")
        if (ccJc8 && ccJc6 && ccJc9)
            getView().setValue("ccJc0", porcCenizas(ccJc8, ccJc6, ccJc9) )

        BigDecimal ccJf1 = (BigDecimal)getView().getValue("ccJf1")
        BigDecimal ccJf2 = (BigDecimal)getView().getValue("ccJf2")
        BigDecimal ccJf3 = (BigDecimal)getView().getValue("ccJf3")
        BigDecimal ccJf4 = (BigDecimal)getView().getValue("ccJf4")
        BigDecimal ccJf5 = (BigDecimal)getView().getValue("ccJf5")
        if (diaTrabajoId)
            getView().setValue("ccJf4", SqlUtil.instance.getPromedio(diaTrabajoId, "Jugo", "jfBri"))
        if (ccJf2 && ccJf3)
            getView().setValue("ccJf6", calcC20 (ccJf2, ccJf3) )
        BigDecimal ccJf6 = (BigDecimal)getView().getValue("ccJf6")
        if (ccJf6 && ccJf1)
            getView().setValue("ccJf7", ccJf6 - ccJf1)
        if (ccJf4 && ccJf5)
            getView().setValue("ccJf8", Calculo.instance.getPorc2(ccJf4,ccJf5,3))
        if (ccJf5)
            getView().setValue("ccJf9", Calculo.instance.redondear(5/ccJf5,3))
        BigDecimal ccJf8 = (BigDecimal)getView().getValue("ccJf8")
        BigDecimal ccJf9 = (BigDecimal)getView().getValue("ccJf9")
        if (ccJf8 && ccJf6 && ccJf9)
            getView().setValue("ccJf0", porcCenizas(ccJf8, ccJf6, ccJf9) )

        BigDecimal ccMc1 = (BigDecimal)getView().getValue("ccMc1")
        BigDecimal ccMc2 = (BigDecimal)getView().getValue("ccMc2")
        BigDecimal ccMc3 = (BigDecimal)getView().getValue("ccMc3")
        BigDecimal ccMc4 = (BigDecimal)getView().getValue("ccMc4")
        BigDecimal ccMc5 = (BigDecimal)getView().getValue("ccMc5")
        if (diaTrabajoId)
            getView().setValue("ccMc4", SqlUtil.instance.getPromedio(diaTrabajoId, "Meladura", "mclBri2"))
        if (ccMc2 && ccMc3)
            getView().setValue("ccMc6", calcC20 (ccMc2, ccMc3) )
        BigDecimal ccMc6 = (BigDecimal)getView().getValue("ccMc6")
        if (ccMc6 && ccMc1)
            getView().setValue("ccMc7", ccMc6 - ccMc1)
        if (ccMc4 && ccMc5)
            getView().setValue("ccMc8", Calculo.instance.getPorc2(ccMc4,ccMc5,3))
        if (ccMc5)
            getView().setValue("ccMc9", Calculo.instance.redondear(5/ccMc5,3))
        BigDecimal ccMc8 = (BigDecimal)getView().getValue("ccMc8")
        BigDecimal ccMc9 = (BigDecimal)getView().getValue("ccMc9")
        if (ccMc8 && ccMc6 && ccMc9)
            getView().setValue("ccMc0", porcCenizas(ccMc8, ccMc6, ccMc9) )

        BigDecimal ccMa1 = (BigDecimal)getView().getValue("ccMa1")
        BigDecimal ccMa2 = (BigDecimal)getView().getValue("ccMa2")
        BigDecimal ccMa3 = (BigDecimal)getView().getValue("ccMa3")
        BigDecimal ccMa4 = (BigDecimal)getView().getValue("ccMa4")
        BigDecimal ccMa5 = (BigDecimal)getView().getValue("ccMa5")
        if (diaTrabajoId)
            getView().setValue("ccMa4", SqlUtil.instance.getPromedio(diaTrabajoId, "Mieles", "maBri2"))
        if (ccMa2 && ccMa3)
            getView().setValue("ccMa6", calcC20 (ccMa2, ccMa3) )
        BigDecimal ccMa6 = (BigDecimal)getView().getValue("ccMa6")
        if (ccMa6 && ccMa1)
            getView().setValue("ccMa7", ccMa6 - ccMa1)
        if (ccMa4 && ccMa5)
            getView().setValue("ccMa8", Calculo.instance.getPorc2(ccMa4,ccMa5,3))
        if (ccMa5)
            getView().setValue("ccMa9", Calculo.instance.redondear(5/ccMa5,3))
        BigDecimal ccMa8 = (BigDecimal)getView().getValue("ccMa8")
        BigDecimal ccMa9 = (BigDecimal)getView().getValue("ccMa9")
        if (ccMa8 && ccMa6 && ccMa9)
            getView().setValue("ccMa0", porcCenizas(ccMa8, ccMa6, ccMa9) )

        BigDecimal ccMb1 = (BigDecimal)getView().getValue("ccMb1")
        BigDecimal ccMb2 = (BigDecimal)getView().getValue("ccMb2")
        BigDecimal ccMb3 = (BigDecimal)getView().getValue("ccMb3")
        BigDecimal ccMb4 = (BigDecimal)getView().getValue("ccMb4")
        BigDecimal ccMb5 = (BigDecimal)getView().getValue("ccMb5")
        if (diaTrabajoId)
            getView().setValue("ccMb4", SqlUtil.instance.getPromedio(diaTrabajoId, "Mieles", "mbBri2"))
        if (ccMb2 && ccMb3)
            getView().setValue("ccMb6", calcC20 (ccMb2, ccMb3) )
        BigDecimal ccMb6 = (BigDecimal)getView().getValue("ccMb6")
        if (ccMb6 && ccMb1)
            getView().setValue("ccMb7", ccMb6 - ccMb1)
        if (ccMb4 && ccMb5)
            getView().setValue("ccMb8", Calculo.instance.getPorc2(ccMb4,ccMb5,3))
        if (ccMb5)
            getView().setValue("ccMb9", Calculo.instance.redondear(5/ccMb5,3))
        BigDecimal ccMb8 = (BigDecimal)getView().getValue("ccMb8")
        BigDecimal ccMb9 = (BigDecimal)getView().getValue("ccMb9")
        if (ccMb8 && ccMb6 && ccMb9)
            getView().setValue("ccMb0", porcCenizas(ccMb8, ccMb6, ccMb9) )

        BigDecimal ccMf1 = (BigDecimal)getView().getValue("ccMf1")
        BigDecimal ccMf2 = (BigDecimal)getView().getValue("ccMf2")
        BigDecimal ccMf3 = (BigDecimal)getView().getValue("ccMf3")
        BigDecimal ccMf4 = (BigDecimal)getView().getValue("ccMf4")
        BigDecimal ccMf5 = (BigDecimal)getView().getValue("ccMf5")
        if (diaTrabajoId)
            getView().setValue("ccMf4", SqlUtil.instance.getPromedio(diaTrabajoId, "Mieles", "mfBri2"))
        if (ccMf2 && ccMf3)
            getView().setValue("ccMf6", calcC20 (ccMf2, ccMf3) )
        BigDecimal ccMf6 = (BigDecimal)getView().getValue("ccMf6")
        if (ccMf6 && ccMf1)
            getView().setValue("ccMf7", ccMf6 - ccMf1)
        if (ccMf4 && ccMf5)
            getView().setValue("ccMf8", Calculo.instance.getPorc2(ccMf4,ccMf5,3))
        if (ccMf5)
            getView().setValue("ccMf9", Calculo.instance.redondear(5/ccMf5,3))
        BigDecimal ccMf8 = (BigDecimal)getView().getValue("ccMf8")
        BigDecimal ccMf9 = (BigDecimal)getView().getValue("ccMf9")
        if (ccMf8 && ccMf6 && ccMf9)
            getView().setValue("ccMf0", porcCenizas(ccMf8, ccMf6, ccMf9) )

        // CENIZAS SULAFATADAS MIEL FINAL O MELAZA (CTO 24 HORAS)
        BigDecimal csPMtra   = (BigDecimal)getView().getValue("csPMtra")
        BigDecimal csPCrisol = (BigDecimal)getView().getValue("csPCrisol")
        BigDecimal csPCriCen = (BigDecimal)getView().getValue("csPCriCen")

        // =+((P19-O19)/N19)*100
        if (csPMtra && csPCrisol && csPCriCen )
            getView().setValue("csPorcCen", Calculo.instance.redondear( ((csPCriCen-csPCrisol)/csPMtra)*100, 2))

        // INDICE DE PREPARACION
        BigDecimal ipBXOc  = (BigDecimal)getView().getValue("ipBXOc")
        BigDecimal ipBXDig = (BigDecimal)getView().getValue("ipBXDig")
        if (ipBXOc && ipBXDig) 
            getView().setValue("ipPorc", Calculo.instance.getPorc(ipBXOc,ipBXDig, 2))

    }
    
    // =5,127/(D8*$E$5*0,02)
    def calcJugo (def valor, def ff){
        return Calculo.instance.redondear((5.127 / (valor * ff * 0.02)), 2)
    }

    // =5,127/(H8*$E$5*0,005)
    def calcMiel (def valor, def ff){
        return Calculo.instance.redondear((5.127 / (valor * ff * 0.005)), 2)
    }

    def calcC20 (def valor1, def valor2){
        return Calculo.instance.redondear(valor1/(1+0.023*(valor2-20)),8)
    }

    def porcCenizas (def valor1, def valor2, def valor3){
        return Calculo.instance.redondear( (16.2+0.36*valor1)*0.0001*valor2*valor3, 2)
    }
}
