package ec.carper.oms.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.carper.oms.data.model.Customer;
import ec.carper.oms.data.payloads.request.CustomerRequest;
import ec.carper.oms.data.payloads.response.MessageResponse;
import ec.carper.oms.service.CustomerService;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/customer")
@ApiResponses(value = {
    @io.swagger.annotations.ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format"),
    @io.swagger.annotations.ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized"),
    @io.swagger.annotations.ApiResponse(code = 500, message = "The server is down. Please bear with us."),
})
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.getAsSinglCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addCustomer( @RequestBody CustomerRequest customerRequest){
        MessageResponse newCustomer = customerService.createCustomer(customerRequest);
        System.out.println(customerRequest.toString());
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public Optional<Customer> updateCustomer( @PathVariable Long id, @RequestBody CustomerRequest customerRequest){
        return customerService.updateCustomer(id, customerRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
