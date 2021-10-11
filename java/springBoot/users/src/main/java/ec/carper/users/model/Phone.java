package ec.carper.users.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Phone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long number;
    
    private Long citycode;
    
    private Long countrycode;

    @ManyToOne
    @JsonIgnore
    private User user;
 
    public static List<Phone> from(List<Phone> phones, User user) {
        List<Phone> list = new ArrayList<>();
        for (Phone phone : phones) {
            Phone aPhone = new Phone();
            aPhone.setNumber(phone.getNumber());
            aPhone.setCitycode(phone.getCitycode());
            aPhone.setCountrycode(phone.getCountrycode());
            aPhone.setUser(user);
            list.add(aPhone);
        }
        return list;
    }
}
