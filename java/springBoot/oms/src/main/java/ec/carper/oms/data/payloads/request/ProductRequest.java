package ec.carper.oms.data.payloads.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ec.carper.oms.data.model.OrdersLine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class ProductRequest {
  private String description;

  private BigDecimal price;

  private BigDecimal weight;
  
  private List<OrdersLine> ordersLines = new ArrayList<>();
    
}
