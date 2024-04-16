package tn.spring.pispring.Entities;


<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
>>>>>>> 9fff4c4cd8f02a68082fc6526300b075cb0d6d09
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter

@Setter
@Table(name = "roles")
public class Role {

    @Id

<<<<<<< HEAD
    private Long roleId;
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
    private Set <UserRole> userRoles=new HashSet<>();


=======




    @OneToMany(mappedBy = "role")
    @ToString.Exclude
    @JsonIgnore
    List<User>users;
>>>>>>> 9fff4c4cd8f02a68082fc6526300b075cb0d6d09
}
