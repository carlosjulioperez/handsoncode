package ec.carper.users.response;

import ec.carper.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter @Setter @ToString
public class MessageResponse {
    private String message;
    private User user;
    
}
