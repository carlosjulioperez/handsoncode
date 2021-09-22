package ec.carper.oms.data.payloads.request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ec.carper.oms.data.model.ShippingAddress;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the Request (DTO Pojo) for Customer Entity class.
 */
@Getter @Setter @ToString
public class CustomerRequest {
    @NotBlank @NotNull
    private String name;

    @NotBlank @NotNull
    private String phone;

    @Email
    private String email;
	
    private List<ShippingAddress> shippingAddresses;
    
}
