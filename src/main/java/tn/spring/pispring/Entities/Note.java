package tn.spring.pispring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idNote;
    @JsonProperty("valueNote")
    double valueNote;
    @JsonProperty("operation")
    String operation;
    @JsonProperty("descNote")
    String descNote;

    @ManyToOne
    @JsonProperty("quiz")
    Quiz quiz;
}
