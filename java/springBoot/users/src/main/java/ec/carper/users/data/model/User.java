package ec.carper.users.data.model;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import ec.carper.users.data.dto.UserDto;
import lombok.Data;
@Entity
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;
    
    @Email
    @Column(unique=true)
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

        List<Phone> phones = new ArrayList<>();
        for (Phone phone : userDto.getPhones()) {
            Phone aPhone = new Phone();
            aPhone.setNumber(phone.getNumber());
            aPhone.setCitycode(phone.getCitycode());
            aPhone.setCountrycode(phone.getCountrycode());
            aPhone.setUser(user);
            phones.add(aPhone);
        }
        user.setPhones(phones);

        return user;
    }
}
