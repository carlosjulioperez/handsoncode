package ec.carper.oms.data.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class Product {

  @Id
  @GeneratedValue
  @Column(name = "apiKey", updatable = false, nullable = false, unique=true, columnDefinition = "BINARY(16)")
  private UUID id;

  @NotBlank @NotNull
  private String description;

  @NotNull
  private BigDecimal price;

  private BigDecimal weight;
  
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrdersLine> ordersLines = new ArrayList<>();

}
