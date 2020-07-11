package ec.carper.ingenio.test
 
import ec.carper.ingenio.model.CanaDetalle1

import ec.carper.ingenio.util.Util
import org.openxava.tests.*

class CanaDetalle2Test extends ModuleTestBase {

    CanaDetalle2Test(String testName) {
        super(testName, "Ingenio", "CanaDetalle2")
    }
 
    void _testGetCampos(){
        // https://stackoverflow.com/questions/573155/groovy-range-with-a-0-5-step-size
        String horaDesde  = "2019-08-03 06:00:00"
        String horaDesde2 = "2019-08-03 07:00:00"
        String horaDesde3 = "2019-08-03 08:00:00"
        String horaHasta  = "2019-08-03 11:00:00"

        //long lapso = (hora.time - o.hora.time) / ( 60 * 60 * 1000)
        long desde  = Util.instance.toTimestamp(horaDesde).time
        long desde2 = Util.instance.toTimestamp(horaDesde2).time
        long desde3 = Util.instance.toTimestamp(horaDesde3).time
        long hasta  = Util.instance.toTimestamp(horaHasta).time
        
        println desde
        println desde2
        println desde3
        println hasta

        long hora = desde
        while(hora<=hasta){
            println hora
            hora += (60*60*1000)
        }

        // (desde..hasta).each{
        //     println "$it"
        // }
    }

    void testGetPromPorcHumedad(){
        // https://stackoverflow.com/questions/567659/calculate-elapsed-time-in-java-groovy
        String diaTrabajoId = Aux.instance.diaTrabajoId

        String hora      = "2019-08-03 06:00:00"
        String horaDesde = "2019-08-03 06:00:00"
        String horaHasta = "2019-08-03 11:00:00"
        
        println new CanaDetalle1().getPromPorcHumedad(
            diaTrabajoId,
            Util.instance.toTimestamp(horaDesde),
            Util.instance.toTimestamp(horaHasta)
        )

        def lista = new CanaDetalle1().getCampos(
            diaTrabajoId,
            Util.instance.toTimestamp(hora)
        )
        println lista
    }
}
