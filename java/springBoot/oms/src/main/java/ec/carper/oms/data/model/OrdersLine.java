package ec.carper.oms.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="orders_line") 
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class OrdersLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @JoinColumn(name = "FK_PRODUCT", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Product product;
    
    @NotNull
    private Integer quantity;
    
    @ManyToOne
    @JoinColumn(name = "FK_ORDERS", nullable = false, updatable = false)
    private Orders orders;

}