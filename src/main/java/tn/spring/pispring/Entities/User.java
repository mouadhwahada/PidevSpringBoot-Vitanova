package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;

    private String password;
    private Date datenaissance;
    private Float weight;
    private Float hight;
    @Enumerated(EnumType.STRING)
    private Objectif objectif;
    private Float imc;




    //enumeration private Objectif //


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
    private NutritionalGoal nutritionalGoal;

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


//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  //  private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Commentaire> commentaires;

}
