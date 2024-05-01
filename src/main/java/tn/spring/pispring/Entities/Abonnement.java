package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tn.spring.pispring.dto.AbonnementDto;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "abonnement")
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

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




    }
}
