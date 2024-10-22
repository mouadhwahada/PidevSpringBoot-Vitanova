package tn.spring.pispring.Entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tn.spring.pispring.dto.FAQDto;

import javax.persistence.*;

@Entity
@Data
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "abonnement_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Abonnement abonnement;


    public FAQDto getFAQDto(){
        FAQDto faqDto = new FAQDto();
        faqDto.setId(id);
        faqDto.setQuestion(question);
        faqDto.setAnswer(answer);
        faqDto.setAbonnementId(abonnement.getId());

        return faqDto;
    }

}
