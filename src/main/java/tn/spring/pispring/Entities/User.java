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

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Abonnement abonnement;

    @JsonIgnore
    @ManyToOne
    Role role;
    @JsonIgnore
    @ManyToOne
    private NutritionalGoal nutritionalGoal;
    @JsonIgnore
    @OneToOne
    private Fidelity fidelity;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    public List<Orderr> orderrs;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "userworkout")
    private  List<FollowedProgram> followedProgramsuser;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Post> posts;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Comment> comments;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<SubComment> subcomments;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<React> reacts;
}
