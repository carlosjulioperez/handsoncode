package ec.carper.users.data.payload.request;

import java.util.HashSet;
import java.util.Set;

import ec.carper.users.data.model.Phone;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserRequest {
    
    private String name;
    private String email;
    private String password;
    //private List<Phone> phones;
    private Set<Phone> phones = new HashSet<>();
    
}
