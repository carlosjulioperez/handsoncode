package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@Entity
@View(members="""
    hora, polCachaza
""")
class TurbiedadDetalle2 extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Turbiedad turbiedad

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora
    
    BigDecimal polCachaza
    
} 
