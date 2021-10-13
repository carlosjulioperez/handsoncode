package ec.carper.users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ec.carper.users.dto.UserDto;
import ec.carper.users.model.Phone;

public class UserServiceControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

	@Test
	public void addUser() throws Exception {
		String uri = "/user/add";
		UserDto userDto = new UserDto();
		userDto.setName("Carlos Julio Pérez Quizhpe");
		userDto.setEmail("carlosjulioperez@nisum.com");
		userDto.setPassword("abc123");

        Phone phone = new Phone();
        phone.setNumber(123l);
        phone.setCountrycode(10l);
        phone.setCitycode(9l);
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        userDto.setPhones(phones);

		String inputJson = super.mapToJson(userDto);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
    
}
