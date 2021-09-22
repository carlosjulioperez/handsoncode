package ec.carper.oms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.carper.oms.data.model.Customer;
import ec.carper.oms.data.payloads.request.CustomerRequest;
import ec.carper.oms.data.payloads.response.MessageResponse;
import ec.carper.oms.data.repository.CustomerRepository;
import ec.carper.oms.exception.ResourceNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public MessageResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setPhone(customerRequest.getPhone());
        customer.setEmail(customerRequest.getEmail());
        customer.setShippingAddresses(customerRequest.getShippingAddresses());
        customerRepository.save(customer);
        return new MessageResponse("New Customer created successfully");
    }

    @Override
    public Optional<Customer> updateCustomer(Long customerId, CustomerRequest customerRequest) throws ResourceNotFoundException {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()){
            throw new ResourceNotFoundException("Customer", "id", customerId);
        }
        else{
            customer.get().setName(customerRequest.getName());
            customer.get().setPhone(customerRequest.getPhone());
            customer.get().setEmail(customerRequest.getEmail());
            customer.get().setShippingAddresses(customerRequest.getShippingAddresses());
            customerRepository.save(customer.get());
        }
        return customer;
    }

    @Override
    public Customer getAsSinglCustomer(Long customerId) throws ResourceNotFoundException {
        return customerRepository.findById(customerId)
            .orElseThrow( ()-> new ResourceNotFoundException("Customer", "id", customerId) );
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomer(Long customerId) throws ResourceNotFoundException {
        if (customerRepository.getById(customerId).getId().equals(customerId))
            customerRepository.deleteById(customerId);
        else throw new ResourceNotFoundException("Customer", "id", customerId); 
    }

}
