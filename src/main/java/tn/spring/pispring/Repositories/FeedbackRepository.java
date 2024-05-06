package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.pispring.Entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
