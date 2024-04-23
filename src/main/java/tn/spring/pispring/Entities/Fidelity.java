package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fidelity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long point;
    private String niveau;



    @ToString.Exclude
    @JsonIgnore


    @OneToOne(mappedBy = "fidelity")
    private User user;
}
