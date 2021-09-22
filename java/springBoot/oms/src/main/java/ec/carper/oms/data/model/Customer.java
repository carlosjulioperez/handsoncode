package ec.carper.oms.data.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * In this Customer Entity.
 * Additional validations are in CustomerRequest (DTO Pojo)
 */
@Entity
@NoArgsConstructor
@Getter @Setter @ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique=true)
    private String phone;

    @Column(unique=true)
    private String email;
	
    @OneToMany(mappedBy = "customer")
    private List<ShippingAddress> shippingAddresses;
    
}
