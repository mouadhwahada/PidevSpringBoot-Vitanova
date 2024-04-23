package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
    @JsonIgnore
    @ManyToOne
    private Post post;
    @ManyToOne
    private User author;
    @JsonIgnore
    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL)
    private List<SubComment> subComments ;

}
