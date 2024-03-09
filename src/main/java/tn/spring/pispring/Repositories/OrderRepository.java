package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.pispring.Entities.Orderr;

public interface OrderRepository extends JpaRepository<Orderr,Integer> {
}
