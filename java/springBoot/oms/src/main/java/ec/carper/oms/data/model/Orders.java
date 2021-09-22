package ec.carper.oms.data.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
// To avoid conflict with "order" reserved word
@Table(name="orders") 
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false, unique=true)
  private Long orderNumber;
  
  @NotNull
  private LocalDate date;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FK_CUSTOMER")
  private Customer customer;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FK_SHIPPING_ADDRESS")
  private ShippingAddress shippingAddresses;
  
  @Enumerated(EnumType.STRING)
  private PaymentType paymentType;
  
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
  private List<OrdersLine> lines;
  
  @NotNull
  private BigDecimal total;
}