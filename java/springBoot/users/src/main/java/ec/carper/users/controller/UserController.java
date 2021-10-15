package ec.carper.users.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.carper.users.dto.UserDto;
import ec.carper.users.model.User;
import ec.carper.users.response.MessageResponse;
import ec.carper.users.service.UserService;
import io.swagger.annotations.ApiResponses;

// https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
@RestController
@RequestMapping("/user")
@ApiResponses(value = {
    @io.swagger.annotations.ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format"),
    @io.swagger.annotations.ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized"),
    @io.swagger.annotations.ApiResponse(code = 500, message = "The server is down. Please bear with us."),
})
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addUser(@Valid @RequestBody UserDto userDto){
        MessageResponse messageResponse = userService.createUser(userDto);
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getAsSinglUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    // @PutMapping("/update/{id}")
    // public Optional<User> updateUser( @PathVariable Long id, @RequestBody UserDto userDto){
    //     return userService.updateUser(id, userDto);
    // }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
}
