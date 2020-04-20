package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="""
    fecha,
    avgJeBri, avgJePol, avgJeSac, avgJePur,
    avgJdBri, avgJdPol, avgJdSac, avgJdPur,
    avgJcBri, avgJcPol, avgJcSac, avgJcPur,
    avgJnBri, avgJnPol, avgJnSac, avgJnPur,
    avgJrBri, avgJrPol, avgJrSac, avgJrPur,
    avgJfBri, avgJfPol, avgJfSac, avgJfPur
""")
@View(members=  """fecha;detalles""")
class Jugo extends Identifiable{

    @Version
    private Integer version;

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fecha

    @OneToMany (mappedBy="jugo", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,
        jeBri,jePol,calJeSac,calJePur,
        jdBri,jdPol,calJdSac,calJdPur,
        jcBri,jcPol,calJcSac,calJcPur,
        jnBri,jnPol,calJnSac,calJnPur,
        jrBri,jrPol,calJrSac,calJrPur,
        jfBri,jfPol,calJfSac,calJfPur
    """)
    Collection<JugoDetalle>detalles

    BigDecimal avgJeBri
    BigDecimal avgJePol
    BigDecimal avgJeSac
    BigDecimal avgJePur

    BigDecimal avgJdBri
    BigDecimal avgJdPol
    BigDecimal avgJdSac
    BigDecimal avgJdPur

    BigDecimal avgJcBri
    BigDecimal avgJcPol
    BigDecimal avgJcSac
    BigDecimal avgJcPur

    BigDecimal avgJnBri
    BigDecimal avgJnPol
    BigDecimal avgJnSac
    BigDecimal avgJnPur

    BigDecimal avgJrBri
    BigDecimal avgJrPol
    BigDecimal avgJrSac
    BigDecimal avgJrPur

    BigDecimal avgJfBri
    BigDecimal avgJfPol
    BigDecimal avgJfSac
    BigDecimal avgJfPur

    BigDecimal getAvg(String propiedad){
        def lista = []
        detalles.each {
            //def valor = (BigDecimal)Eval.x(it, "x.jeBri")
            def valor = (BigDecimal)Eval.x(it, "x."+propiedad)
            if (valor > 0) lista << valor
        }
        return lista.size()>0 ? ( lista.sum() / lista.size() ).setScale(2, BigDecimal.ROUND_HALF_UP) : 0
    }

    @PrePersist // Ejecutado justo antes de grabar el objeto por primera vez
    private void preGrabar() throws Exception {
        sincronizarPropiedadesPersistentes()
    }
    
    @PreUpdate
    void sincronizarPropiedadesPersistentes(){
        setAvgJeBri(getAvg("jeBri"))
        setAvgJePol(getAvg("jePol"))
        setAvgJeSac(getAvg("calJeSac"))
        setAvgJePur(getAvg("calJePur"))

        setAvgJdBri(getAvg("jdBri"))
        setAvgJdPol(getAvg("jdPol"))
        setAvgJdSac(getAvg("calJdSac"))
        setAvgJdPur(getAvg("calJdPur"))

        setAvgJcBri(getAvg("jcBri"))
        setAvgJcPol(getAvg("jcPol"))
        setAvgJcSac(getAvg("calJcSac"))
        setAvgJcPur(getAvg("calJcPur"))

        setAvgJnBri(getAvg("jnBri"))
        setAvgJnPol(getAvg("jnPol"))
        setAvgJnSac(getAvg("calJnSac"))
        setAvgJnPur(getAvg("calJnPur"))

        setAvgJrBri(getAvg("jrBri"))
        setAvgJrPol(getAvg("jrPol"))
        setAvgJrSac(getAvg("calJrSac"))
        setAvgJrPur(getAvg("calJrPur"))

        setAvgJfBri(getAvg("jfBri"))
        setAvgJfPol(getAvg("jfPol"))
        setAvgJfSac(getAvg("calJfSac"))
        setAvgJfPur(getAvg("calJfPur"))
    }

    BigDecimal getAvgField (LocalDate fecha, String campo){
        BigDecimal valor = 0
        Query query = getManager().createQuery("select ${campo} from Jugo o where fecha= :fecha ")
        query.setParameter("fecha", fecha)

        List records = query.getResultList()
        valor = records ? records[0]: 0
        //return  records.isEmpty() ? BigDecimal.ZERO : (BigDecimal) records.get(0)
        return valor
    }

    // @PrePersist // Ejecutado justo antes de grabar el objeto por primera vez
    // private void preGrabar() throws Exception {
    //     recalcularAvgJeBri()
    // }
    //
    // @PreUpdate
    // void recalcularAvgJeBri() {
    //     def val = getAvg("jeBri")
    //     println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + val
    //     //setAvgJeBri(getAvg("jeBri"))
    //     setAvgJeBri(val)
    // }
}
