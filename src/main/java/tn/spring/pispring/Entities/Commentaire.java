package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Commentaire implements Serializable {

  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long Id;
    String Contenu;
    Date Date ;

    String Auteur;
    String Reponses;

    @ManyToOne
    private Post post;


 @ToString.Exclude
 @JsonIgnore
    @ManyToOne
    private User user;



    @OneToMany(mappedBy = "commentaire")
    @ToString.Exclude
    @JsonIgnore
    private List<React> reactions ;

    @OneToMany(mappedBy ="c" )
    @ToString.Exclude
    @JsonIgnore
    private List<Commentaire> commentaires;
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    Commentaire c;

    // Auteur: Utilisateur (classe représentant l'auteur du commentaire)
           // Réponses: List<Commentaire> (liste des réponses à ce commentaire)

}
