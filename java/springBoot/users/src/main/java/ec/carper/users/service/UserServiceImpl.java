package ec.carper.users.service;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> updateUser(Long userId, UserDto userDto) throws ResourceNotFoundException{
        Optional<User> user = userRepository.findById(userId);

        // https://stackoverflow.com/questions/56290161/the-method-isempty-is-undefined-for-the-type-optional
        if ( !user.isPresent() )
            throw new ResourceNotFoundException("User", "id", userId);
        else{
            user.get().setName(userDto.getName());
            user.get().setEmail(userDto.getEmail());
            user.get().setPassword(userDto.getPassword());
          
            // List<Phone>phones = new ArrayList<>();
            // for (Phone phone: userDto.getPhones()){
            //     Phone aPhone = new Phone();
            //     aPhone.setNumber(phone.getNumber());
            //     aPhone.setCitycode(phone.getCitycode());
            //     aPhone.setCountrycode(phone.getCountrycode());
            //     aPhone.setUser(user.get());
            //     phones.add(aPhone);
            // }
            // user.get().setPhones(phones);
            userRepository.save(user.get());
        }
        return user;
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