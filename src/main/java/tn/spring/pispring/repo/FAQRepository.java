package tn.spring.pispring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.FAQ;

@Repository
public interface FAQRepository extends JpaRepository<FAQ,Long> {
}
