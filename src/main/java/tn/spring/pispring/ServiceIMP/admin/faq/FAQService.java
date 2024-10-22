package tn.spring.pispring.ServiceIMP.admin.faq;

import tn.spring.pispring.dto.FAQDto;

public interface FAQService {

    FAQDto postFAQ(Long abonnementId , FAQDto faqDto);
}
