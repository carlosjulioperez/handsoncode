package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="""
    diaTrabajo.descripcion,
    jeBri, jePol, jeSac, jePur,
    jdBri, jdPol, jdSac, jdPur,
    jcBri, jcPol, jcSac, jcPur,
    jnBri, jnPol, jnSac, jnPur,
    jrBri, jrPol, jrSac, jrPur,
    jfBri, jfPol, jfSac, jfPur
""")
@View(members=  """diaTrabajo;detalle""")
class Jugo extends DiaTrabajoEditable {

    BigDecimal jeBri
    BigDecimal jePol
    BigDecimal jeSac
    BigDecimal jePur
    BigDecimal jdBri
    BigDecimal jdPol
    BigDecimal jdSac
    BigDecimal jdPur
    BigDecimal jcBri
    BigDecimal jcPol
    BigDecimal jcSac
    BigDecimal jcPur
    BigDecimal jnBri
    BigDecimal jnPol
    BigDecimal jnSac
    BigDecimal jnPur
    BigDecimal jrBri
    BigDecimal jrPol
    BigDecimal jrSac
    BigDecimal jrPur
    BigDecimal jfBri
    BigDecimal jfPol
    BigDecimal jfSac
    BigDecimal jfPur

    @OneToMany (mappedBy="jugo", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,
        jeBri [jugo.promJeBri],
        jePol [jugo.promJePol],
        jeSac [jugo.promJeSac],
        jePur [jugo.promJePur],
        jdBri [jugo.promJdBri],
        jdPol [jugo.promJdPol],
        jdSac [jugo.promJdSac],
        jdPur [jugo.promJdPur],
        jcBri [jugo.promJcBri],
        jcPol [jugo.promJcPol],
        jcSac [jugo.promJcSac],
        jcPur [jugo.promJcPur],
        jnBri [jugo.promJnBri],
        jnPol [jugo.promJnPol],
        jnSac [jugo.promJnSac],
        jnPur [jugo.promJnPur],
        jrBri [jugo.promJrBri],
        jrPol [jugo.promJrPol],
        jrSac [jugo.promJrSac],
        jrPur [jugo.promJrPur],
        jfBri [jugo.promJfBri],
        jfPol [jugo.promJfPol],
        jfSac [jugo.promJfSac],
        jfPur [jugo.promJfPur]
    """)
    Collection<JugoDetalle>detalle
    
    BigDecimal getPromJeBri(){ return super.getPromedio(detalle, "jeBri", 2) }
    BigDecimal getPromJePol(){ return super.getPromedio(detalle, "jePol", 2) }
    BigDecimal getPromJeSac(){ return super.getPromedio(detalle, "jeSac", 2) }
    BigDecimal getPromJePur(){ return super.getPromedio(detalle, "jePur", 2) }
    BigDecimal getPromJdBri(){ return super.getPromedio(detalle, "jdBri", 2) }
    BigDecimal getPromJdPol(){ return super.getPromedio(detalle, "jdPol", 2) }
    BigDecimal getPromJdSac(){ return super.getPromedio(detalle, "jdSac", 2) }
    BigDecimal getPromJdPur(){ return super.getPromedio(detalle, "jdPur", 2) }
    BigDecimal getPromJcBri(){ return super.getPromedio(detalle, "jcBri", 2) }
    BigDecimal getPromJcPol(){ return super.getPromedio(detalle, "jcPol", 2) }
    BigDecimal getPromJcSac(){ return super.getPromedio(detalle, "jcSac", 2) }
    BigDecimal getPromJcPur(){ return super.getPromedio(detalle, "jcPur", 2) }
    BigDecimal getPromJnBri(){ return super.getPromedio(detalle, "jnBri", 2) }
    BigDecimal getPromJnPol(){ return super.getPromedio(detalle, "jnPol", 2) }
    BigDecimal getPromJnSac(){ return super.getPromedio(detalle, "jnSac", 2) }
    BigDecimal getPromJnPur(){ return super.getPromedio(detalle, "jnPur", 2) }
    BigDecimal getPromJrBri(){ return super.getPromedio(detalle, "jrBri", 2) }
    BigDecimal getPromJrPol(){ return super.getPromedio(detalle, "jrPol", 2) }
    BigDecimal getPromJrSac(){ return super.getPromedio(detalle, "jrSac", 2) }
    BigDecimal getPromJrPur(){ return super.getPromedio(detalle, "jrPur", 2) }
    BigDecimal getPromJfBri(){ return super.getPromedio(detalle, "jfBri", 2) }
    BigDecimal getPromJfPol(){ return super.getPromedio(detalle, "jfPol", 2) }
    BigDecimal getPromJfSac(){ return super.getPromedio(detalle, "jfSac", 2) }
    BigDecimal getPromJfPur(){ return super.getPromedio(detalle, "jfPur", 2) }
    
    void actualizar() throws ValidationException{
        try{

            this.jeBri = promJeBri
            this.jePol = promJePol
            this.jeSac = promJeSac
            this.jePur = promJePur
            this.jdBri = promJdBri
            this.jdPol = promJdPol
            this.jdSac = promJdSac
            this.jdPur = promJdPur
            this.jcBri = promJcBri
            this.jcPol = promJcPol
            this.jcSac = promJcSac
            this.jcPur = promJcPur
            this.jnBri = promJnBri
            this.jnPol = promJnPol
            this.jnSac = promJnSac
            this.jnPur = promJnPur
            this.jrBri = promJrBri
            this.jrPol = promJrPol
            this.jrSac = promJrSac
            this.jrPur = promJrPur
            this.jfBri = promJfBri
            this.jfPol = promJfPol
            this.jfSac = promJfSac
            this.jfPur = promJfPur

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

    //TODO Ajustar en StockFabrica
    BigDecimal getAvgField (String diaTrabajoId, String campo){
        Query query = getManager().createQuery("select ${campo} from Jugo o where diaTrabajo.id = :diaTrabajoId")
        query.setParameter("diaTrabajoId", diaTrabajoId)

        def lista = query.resultList
        return lista ? lista[0]: 0
    }

}
