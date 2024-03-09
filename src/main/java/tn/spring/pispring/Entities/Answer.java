package tn.spring.pispring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idAnswer;
    @JsonProperty("score")
    double score;
    @JsonProperty("textAnswer")
    String textAnswer;
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    Question question;
}
