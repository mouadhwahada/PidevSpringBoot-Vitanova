package tn.spring.pispring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idQuiz;
    @JsonProperty("titleQuiz")
    String titleQuiz;
    @JsonProperty("topicQuiz")
    String topicQuiz;
    @OneToMany(mappedBy = "quiz")
    @JsonIgnore
    @ToString.Exclude
    List<Question> questionList;
}
