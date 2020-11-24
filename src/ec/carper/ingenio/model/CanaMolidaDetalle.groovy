package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""#
    horaS, hora; 
    ticket1, guia1, horaBanda1;
    ticket2, guia2, horaBanda2;
    ticket3, guia3, horaBanda3;
""")
class CanaMolidaDetalle extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    CanaMolida canaMolida
    
    @Stereotype("TIME") @Column(length=5) @OnChange(CanaMolidaDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora

    @DisplaySize(6)
    Integer ticket1

    @DisplaySize(6)
    Integer guia1
    
    @Stereotype("TIME") @Column(length=5)
    String horaBanda1

    @DisplaySize(6)
    Integer ticket2

    @DisplaySize(6)
    Integer guia2
    
    @Stereotype("TIME") @Column(length=5)
    String horaBanda2

    @DisplaySize(6)
    Integer ticket3

    @DisplaySize(6)
    Integer guia3
    
    @Stereotype("TIME") @Column(length=5)
    String horaBanda3

    // @Depends("horaS")
    // String getModulo(){
    //     def d = SqlUtil.instance.getDetallePorHora(canaMolida.diaTrabajo.id, "trashCana", "TrashCanaDetalle1", horaS)
    //     return (d ? d.modulo.descripcion: "")
    // }

}
