package ec.carper.users.service;

import java.util.List;

import org.springframework.stereotype.Component;

import ec.carper.users.dto.UserDto;
import ec.carper.users.model.User;
import ec.carper.users.response.MessageResponse;

/**
 * @Component annotation is a shorthand for the @Bean annotation that register this
 * as a bean in the application context and makes it accessible during classpath scanning.
 */
@Component
public interface UserService {
    
   //User createUser(UserDto userDto);
   MessageResponse createUser(UserDto userDto);
   void deleteUser (Long customerId);
   User getAsSinglUser(Long customerId);
   List <User> getAllUser();
 
}