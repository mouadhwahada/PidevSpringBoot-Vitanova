package tn.spring.pispring.ServiceIMP.admin.faq;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Abonnement;
import tn.spring.pispring.Entities.FAQ;
import tn.spring.pispring.dto.FAQDto;
import tn.spring.pispring.repo.AbonnementRepository;
import tn.spring.pispring.repo.FAQRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService  {

    private final FAQRepository faqRepository;

    private final AbonnementRepository abonnementRepository;

    public FAQDto postFAQ(Long abonnementId , FAQDto faqDto){
        Optional<Abonnement> optionalAbonnement = abonnementRepository.findById(abonnementId);
        if (optionalAbonnement.isPresent()){
            FAQ faq = new FAQ();

            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setAbonnement(optionalAbonnement.get());

            return  faqRepository.save(faq).getFAQDto();
        }
        return null;
    }
}
