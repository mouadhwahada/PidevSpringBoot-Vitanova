package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;



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


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="users")
public class User implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

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


}
