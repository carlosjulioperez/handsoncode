package ec.carper.users.dto;

import java.util.ArrayList;
import java.util.List;

import ec.carper.users.model.Phone;
import lombok.Data;

@Data
public class UserDto {
    
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones = new ArrayList<>();
}    
