package ec.carper.ingenio.model.core

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class Empresa extends Identifiable{

    @Column(length=100) @Required
    String nombre

    @Column(length=13) @Required
    String cedulaRuc

    @Column(length=100) @Required
    String direccion

    @Column(length=20) @Required
    String telefono

    @Required
    Tipo tipo 
    enum Tipo { PROVEEDOR, CLIENTE }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList @NoCreate @NoModify @Required
    Ciudad ciudad
}

