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
    private String password;
    private Date datenaissance;
    private Float weight;
    private Float hight;
    @Enumerated(EnumType.STRING)
    private Objectif objectif;
    private Float imc;




    //enumeration private Objectif //


    @OneToOne(mappedBy = "user")
    private Abonnement abonnement;


    @ManyToOne
    Role role;

    @ManyToOne
    private NutritionalGoal nutritionalGoal;
    @OneToOne
    private Fidelity fidelity;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    public List<Orderr> orderrs;


    @OneToMany(mappedBy = "userworkout")
    private  List<FollowedProgram> followedProgramsuser;


//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  //  private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;

}
