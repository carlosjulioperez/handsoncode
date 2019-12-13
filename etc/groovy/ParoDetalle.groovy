package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Embeddable
class ParoDetalle{

    // https://github.com/mariuszs/openxava/blob/master/source/src/test/java/org/openxava/test/model/Clerk.java
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp inicioParo

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp finParo

    @Depends("inicioParo, finParo") //Propiedad calculada
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp getCalTotalParo(){
        if (inicioParo!=null && finParo!=null) {
            def diferencia = finParo.getTime() - inicioParo.getTime()
            def tm = diferencia

            long hh = tm / 3600
            println(hh)

            tm %= 3600;
            long mm = tm / 60;
            println(mm)
            
            tm %= 60;
            long ss = tm;
            println(ss)

            return new java.sql.Timestamp( diferencia )
        }
        else return null
    }
    
    java.sql.Timestamp totalParo

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList
    Area area

    @Column(length=100) @Required
    String descripcion

    // java.sql.Timestamp getTiempoTotalParada(){
    //     java.sql.Timestamp total = new java.sql.Timestamp();
    // }

    @PrePersist // Ejecutado justo antes de grabar el objeto por primera vez
    private void preGrabar() throws Exception {
        recalcularTotalParo()
    }

    public void recalcularTotalParo() {
        setTotalParo(getCalTotalParo())
    }

}
