package tn.spring.pispring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
<<<<<<< HEAD



import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;

import java.util.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

=======
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2

@Entity
@Getter
@Setter
<<<<<<< HEAD
@Table(name ="users")
public class User implements UserDetails  {
=======
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
<<<<<<< HEAD

    private String username;

    private String userName;
    private String email;


    private String password;
    private String firstname;
    private String lastname;


    private String phone ;

    private boolean enabled =true;


    private String profile;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
    @JsonIgnore
        private Set<UserRole> userRoles = new HashSet<>();
=======
    private String name;
    private String lastname;

    private String username;
    private String email;
    private String password;
    private String address;
    private int number;
    private boolean blocked ;
    private boolean valid ;
    private String token;
    private String image;


    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude

    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    //@JsonIgnore

    private Set <Role> roles = new HashSet<>();
>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

<<<<<<< HEAD
        Set<Authority> set=new HashSet<>();

        this.userRoles.forEach(userRole -> {
            set.add(new Authority(userRole.getRole().getRoleName()));
        });

        return set;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Comment> comments;

=======
    public User(String name,String lastname, String username, String email, String password, boolean blocked, String address, boolean valid) {

        this.name = name;
        this.lastname=lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.blocked = blocked;
        this.address = address;
        this.valid = valid;

    }

>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2

}
