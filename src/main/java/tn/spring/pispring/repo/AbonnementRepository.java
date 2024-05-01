package tn.spring.pispring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Abonnement;

import java.util.List;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement,Long> {

    List<Abonnement> findAllByNameContaining(String title);

}
