package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class React implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String Type;
    @ToString.Exclude
    @JsonIgnore
    String User;
    LocalDateTime Date;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Commentaire commentaire;
}
