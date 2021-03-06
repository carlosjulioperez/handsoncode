package ec.carper.users.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import ec.carper.users.dto.UserDto;
import lombok.Data;
@Entity
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(unique= true)
    private String email;
    
    private String password;

    private LocalDate created;

    private LocalDate modified;

    private LocalDate lastlogin;

    private Boolean isactive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Phone> phones = new ArrayList<>();

    public static User from(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCreated(LocalDate.now());
        user.setLastlogin(LocalDate.now());
        user.setIsactive(true);

        user.setPhones(Phone.from(userDto.getPhones(), user));

        return user;
    }
}
