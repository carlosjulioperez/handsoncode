package ec.carper.ingenio.calculators
 
import ec.carper.ingenio.model.*

import org.openxava.calculators.*
import static org.openxava.jpa.XPersistence.*

class BlcParoTotalCalculator implements ICalculator {

    int diaTrabajoId

    @Override
    Object calculate() throws Exception {
        def paroTotal = new ArrayList()
        def lista = getManager().createQuery("FROM Paro where diaTrabajo.id = :id")
                                .setParameter("id", diaTrabajoId).resultList
        lista.each{
            it.total.each{
                paroTotal.add( new ParoTotal (area: it.area, totalParo: it.totalParo) )
            }
        }
        return paroTotal
    }
}
