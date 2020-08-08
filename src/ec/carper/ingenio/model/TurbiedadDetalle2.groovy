package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@Entity
@View(members="""
    horaS, hora;
    polCachaza
""")
class TurbiedadDetalle2 extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Turbiedad turbiedad

    @Stereotype("TIME") @Column(length=5) @OnChange(TurbiedadDetalle2Action.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @DisplaySize(6)    
    BigDecimal polCachaza
    
} 
