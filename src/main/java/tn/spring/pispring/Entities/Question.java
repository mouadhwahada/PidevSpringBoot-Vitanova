package tn.spring.pispring.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idQuestion;
    String textQuestion;

    @OneToMany(mappedBy = "question")
    List<Answer> answerList;
    @ManyToOne
    Quiz quiz;
}
