package ec.carper.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ec.carper.users.data.model.User;
import ec.carper.users.data.payload.request.UserRequest;
import ec.carper.users.data.payload.response.MessageResponse;

/**
 * @Component annotation is a shorthand for the @Bean annotation that register this
 * as a bean in the application context and makes it accessible during classpath scanning.
 */
@Component
public interface UserService {
    
   MessageResponse createUser(UserRequest customerRequest);
   Optional <User> updateUser(Long customerId, UserRequest customerRequest);
   void deleteUser (Long customerId);
   User getAsSinglUser(Long customerId);
   List <User> getAllUser();
 
}