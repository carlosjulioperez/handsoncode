package ec.carper.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.carper.users.dto.UserDto;
import ec.carper.users.exception.ResourceNotFoundException;
import ec.carper.users.model.User;
import ec.carper.users.repository.UserRepository;
import ec.carper.users.response.MessageResponse;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public MessageResponse createUser(UserDto userDto) {
        User user = new User();
        String message = "The email has already exists";
        if (userRepository.findByEmail(userDto.getEmail()) ==null ){
            user = userRepository.save(User.from(userDto));
            message = "New Customer created successfully";
        }
        return new MessageResponse(message, user);
    }

    @Override
    public void deleteUser(Long userId) throws ResourceNotFoundException{
        if (userRepository.getById(userId).getId().equals(userId) )
            userRepository.deleteById(userId);
        else throw new ResourceNotFoundException("User", "id", userId);
    }

    @Override
    public User getAsSinglUser(Long userId) throws ResourceNotFoundException{
        return userRepository.findById(userId)
        .orElseThrow( ()-> new ResourceNotFoundException("User", "id", userId) );
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    
}