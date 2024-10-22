package tn.spring.pispring.Entities;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

=======

import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tn.spring.pispring.dto.AbonnementDto;

<<<<<<< HEAD
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
=======
import javax.persistence.*;
>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "abonnement")
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

<<<<<<< HEAD

    @ToString.Exclude
    @JsonIgnore
    @OneToOne
    private User user;
=======
    private  Long id;

    private String name;

    private Long price;

    @Lob
    private String decription;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name ="category_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public AbonnementDto getDto(){
        AbonnementDto abonnementDto=new AbonnementDto();
        abonnementDto.setId(id);
        abonnementDto.setName(name);
        abonnementDto.setPrice(price);
        abonnementDto.setDecription(decription);
        abonnementDto.setCategoryId(category.getId());
       abonnementDto.setCategoryName(category.getName());
       abonnementDto.setByteImg(img);
        return  abonnementDto;
>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2




    }
}
