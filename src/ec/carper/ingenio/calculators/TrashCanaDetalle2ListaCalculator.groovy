package ec.carper.ingenio.calculators
 
import org.openxava.calculators.*
import ec.carper.ingenio.model.BrixDensidadTitSus
 
class TrashCanaDetalle2ListaCalculator implements ICalculator {
 
    Object calculate() throws Exception {
        def lista = new BrixDensidadTitSus().getSqlToList()        
        println "##################################################" 
        println lista
        return lista
    }
}
