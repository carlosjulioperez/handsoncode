package ec.carper.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.carper.users.data.dto.UserDto;
import ec.carper.users.data.model.User;
import ec.carper.users.data.repository.UserRepository;
import ec.carper.users.data.response.MessageResponse;
import ec.carper.users.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public MessageResponse createUser(UserDto userDto) {
        User user = User.from(userDto);
        userRepository.save(user);
        return new MessageResponse("New User created succesfully");
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