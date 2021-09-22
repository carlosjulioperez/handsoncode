package ec.carper.oms.data.payloads.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import ec.carper.oms.data.model.Customer;
import ec.carper.oms.data.model.OrdersLine;
import ec.carper.oms.data.model.PaymentType;
import ec.carper.oms.data.model.ShippingAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class OrdersRequest {
    
    @NotNull
    private Long orderNumber;
    @NotNull
    private LocalDate date;
    private Customer customer;
    private ShippingAddress shippingAddresses;
    private PaymentType paymentType;
    private List<OrdersLine> lines;
    private BigDecimal total;
}
