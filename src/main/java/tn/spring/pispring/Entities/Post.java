package tn.spring.pispring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String imageName;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    private int nbViews;
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    private User author;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comments;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL)
    private List<React> reactions ;

}
