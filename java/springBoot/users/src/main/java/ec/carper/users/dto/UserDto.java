package ec.carper.users.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Email;

import ec.carper.users.model.Phone;
import lombok.Data;

@Data
public class UserDto {
    
    private Long id;
    private String name;
    
    @Email
    @Column(unique=true)
    private String email;
    
    private String password;
    private List<Phone> phones = new ArrayList<>();
}    
