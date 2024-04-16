package tn.spring.pispring.Entities;


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

    private Long roleId;
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
    private Set <UserRole> userRoles=new HashSet<>();


}
