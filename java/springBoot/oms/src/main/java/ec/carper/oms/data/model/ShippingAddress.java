package ec.carper.oms.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank @NotNull
    String streetNumber;
    
    @NotBlank @NotNull
    String city;
    
    @NotBlank @NotNull
    String state;
    
    @NotBlank @NotNull
    String zipCode;
    
    @NotBlank @NotNull
    String country;

	@ManyToOne
    @JsonIgnore
    @JoinColumn(name="customer_id")
    private Customer customer;
}