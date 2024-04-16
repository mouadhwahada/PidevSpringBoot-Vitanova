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
    private String email;

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
    // @Enumerated(EnumType.STRING)
    //private Objectif objectif;





    //enumeration private Objectif //




/*
   /* @ManyToOne
    private NutritionalGoal nutritionalGoal;
    @OneToOne
    private Fidelity fidelity;


    @OneToOne(mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    private Abonnement abonnement;


    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    Role role;

    @ManyToOne

    @ToString.Exclude
    @JsonIgnore
    @ToString.Exclude
    @JsonIgnore
    private NutritionalGoal nutritionalGoal;

    @OneToOne
    private NutritionalGoal nutritionalGoal;

    @OneToOne
    private NutritionalGoal nutritionalGoal;
    @OneToOne

    private Fidelity fidelity;

    @OneToOne
    @ToString.Exclude
    @JsonIgnore
    private Fidelity fidelity;


    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    public List<Orderr> orderrs;


    @OneToMany(mappedBy = "userworkout")
    @ToString.Exclude
    @JsonIgnore
    private  List<FollowedProgram> followedProgramsuser;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="user_workout_program",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="workout_id")
    )
    private Set<Workoutprogram> workoutPrograms;


*/


//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  //  private List<Post> posts;

  //  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<Commentaire> commentaires;

 /*   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> roless = new ArrayList<>() ;
        for (Role authority : roles ) {
            if (authority !=null&& authority.getName() != null)
                roless.add(new SimpleGrantedAuthority(authority.getName().name()));
            else
                System.out.println("----- U have no role ----");
        }
        return roless;
    }*/
   /*
    public Set<Role> getAuthFromBase(){
        return  this.roles;}//role

    @Override
    public String getPassword() {
        return paswd;
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public boolean isEnabled() {
        return true;
    }*/

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Commentaire> commentaires;

}
