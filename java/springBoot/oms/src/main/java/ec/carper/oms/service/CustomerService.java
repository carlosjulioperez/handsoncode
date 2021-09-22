package ec.carper.oms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ec.carper.oms.data.model.Customer;
import ec.carper.oms.data.payloads.request.CustomerRequest;
import ec.carper.oms.data.payloads.response.MessageResponse;

/**
 * @Component annotation is a shorthand for the @Bean annotation that register this
 * as a bean in the application context and makes it accessible during classpath scanning.
 */
@Component
public interface CustomerService {
   MessageResponse createCustomer(CustomerRequest customerRequest);
   Optional <Customer> updateCustomer(Long customerId, CustomerRequest customerRequest);
   void deleteCustomer (Long customerId);
   Customer getAsSinglCustomer(Long customerId);
   List <Customer> getAllCustomer();
}
