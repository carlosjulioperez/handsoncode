package ec.carper.oms;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ec.carper.oms.data.model.Customer;

/**
 * Test class
 * https://www.tutorialspoint.com/spring_boot/spring_boot_rest_controller_unit_test.htm
 */
public class CustomerServiceControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getCustomersList() throws Exception {
        String uri = "/customer/all";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        
        String content = mvcResult.getResponse().getContentAsString();
        Customer[] list = super.mapFromJson(content, Customer[].class);
        assertTrue(list.length > 0);
    }

    @Test
    public void addCustomer() throws Exception {
        String uri = "/customer/add";
        Customer customer = new Customer();
        customer.setName("James Bond");
        customer.setPhone("+01 123456");
        customer.setEmail("jamesbond@agents.com");

        String inputJson = super.mapToJson(customer);
        MvcResult mvcResult = mvc.perform(
            MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
            .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();

        String expectedResponse = "{\"message\":\"New Customer created successfully\"}";
        assertEquals(content, expectedResponse);
    }

}
