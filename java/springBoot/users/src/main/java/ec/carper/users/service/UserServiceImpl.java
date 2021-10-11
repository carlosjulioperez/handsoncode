package ec.carper.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.carper.users.dto.UserDto;
import ec.carper.users.exception.ResourceNotFoundException;
import ec.carper.users.model.User;
import ec.carper.users.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(UserDto userDto) {
        //return new MessageResponse("New User created succesfully");
        return userRepository.save(User.from(userDto));
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