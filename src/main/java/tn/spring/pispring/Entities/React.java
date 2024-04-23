package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    TypeReact typeReact;
    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime createdAt;

    String Type;
    @ToString.Exclude
    @JsonIgnore
    String User;
    LocalDateTime Date;

    @ToString.Exclude
    @JsonIgnore

    @ManyToOne
    private User author;
    @JsonIgnore
    @ManyToOne
    private Post post;
}
