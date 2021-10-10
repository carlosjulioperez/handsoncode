package ec.carper.users.data.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter @Setter @ToString
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // @OneToMany(mappedBy = "user")
    // private List<Phone> phones;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Phone> phones = new HashSet<>();

}
