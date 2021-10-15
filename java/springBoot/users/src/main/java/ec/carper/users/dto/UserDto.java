package ec.carper.users.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import ec.carper.users.model.Phone;
import lombok.Data;

@Data
public class UserDto {
    
    private Long id;
    
    @NotNull
    private String name;
    
    @Email
    private String email;
    
    private String password;
    private List<Phone> phones = new ArrayList<>();
}    
