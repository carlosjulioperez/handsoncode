package ec.carper.users.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ec.carper.users.model.Phone;
import ec.carper.users.util.ValidPassword;
import lombok.Data;

@Data
public class UserDto {
    
    private Long id;
    
    @NotNull
    @NotBlank
    private String name;
    
    @Email
    private String email;
    
    // @NotBlank
    // @Size(min=13)
    // @Pattern(regexp="^(?=.*[0-9])(?=.*[A-Z])$", message = "Password must contain at least 1 number and uppercase character")
    @ValidPassword
    private String password;

    private List<Phone> phones = new ArrayList<>();
}    
