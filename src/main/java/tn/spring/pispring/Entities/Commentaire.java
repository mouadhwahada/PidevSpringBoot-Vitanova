package tn.spring.pispring.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    private User user;



    @OneToMany(mappedBy = "commentaire", cascade = CascadeType.ALL)
    private List<React> reactions ;


    // Auteur: Utilisateur (classe représentant l'auteur du commentaire)
           // Réponses: List<Commentaire> (liste des réponses à ce commentaire)

}
