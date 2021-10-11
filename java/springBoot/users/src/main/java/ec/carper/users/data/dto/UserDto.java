package ec.carper.users.data.dto;

import java.util.ArrayList;
import java.util.List;

import ec.carper.users.data.model.Phone;
import lombok.Data;

@Data
public class UserDto {
    
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones = new ArrayList<>();
}    
