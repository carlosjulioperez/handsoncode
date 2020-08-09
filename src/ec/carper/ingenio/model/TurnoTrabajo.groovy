package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@Tab(properties="horaDesde,horaHasta", defaultOrder="horaDesde")
class TurnoTrabajo extends Identifiable{

    @Stereotype("TIME") @Column(length=5)
    @OnChange(TurnoTrabajoAction.class) @Required
    String horaDesde

    @Stereotype("TIME") @Column(length=5) @Required @ReadOnly
    String horaHasta
    
    @Hidden
    String getDescripcion(){
        return "Desde las ${horaDesde} hasta las ${horaHasta}"
    }
}
