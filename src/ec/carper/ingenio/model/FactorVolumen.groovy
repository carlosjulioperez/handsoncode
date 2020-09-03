package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*

import static org.openxava.jpa.XPersistence.*

@Entity
class FactorVolumen extends Identifiable {

    @Column(length=3) @ReadOnly
    int temp
    
    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col02

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col03

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col04

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col05

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col06

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col07

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col08

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col09

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col10

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col11

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col12

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col13

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col14

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col15

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col16
  
    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col17

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col18

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col19

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal col20
    
    BigDecimal getValor (int temp, int numColumna){
        def num = numColumna.toString().padLeft(2,'0')
        def valor = 0
        if (numColumna >=2 && numColumna <=20){
            valor = getManager().createQuery(
                "SELECT col${num} FROM FactorVolumen WHERE temp = :temp"
            ).setParameter("temp", temp).resultList[0]?: 0
        }
        return valor
    }
}
