package ec.carper.ingenio.model

import ec.carper.ingenio.model.InicioZafra
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class Preferencias extends Identifiable{

    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @DescriptionsList
    InicioZafra inicioZafra

}
