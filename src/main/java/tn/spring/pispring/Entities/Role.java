package tn.spring.pispring.Entities;


<<<<<<< HEAD

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
=======
import lombok.*;
import org.hibernate.annotations.NaturalId;
import tn.spring.pispring.dto.RoleName;

import javax.persistence.*;


@Entity
>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2
@Getter

@Setter
<<<<<<< HEAD
@Table(name = "roles")
public class Role {

    @Id

    private Long roleId;
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
    private Set <UserRole> userRoles=new HashSet<>();










    @OneToMany(mappedBy = "role")
    @ToString.Exclude
    @JsonIgnore
    List<User>users;
=======
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;



>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2

}
