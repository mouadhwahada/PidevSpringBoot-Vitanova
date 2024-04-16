package tn.spring.pispring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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
    @JsonProperty("charQ")
    String charQ;
    @JsonProperty("textQ")
    String textQ;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "question")
    List<Answer> answerList;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JsonProperty("quiz")
    Quiz quiz;


    @JsonProperty("answerList")
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Answer> answerList;

/*
    @ManyToOne
    @JsonProperty("quiz")
    Quiz quiz;
*/
}
