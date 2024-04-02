package tn.spring.pispring.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Fidelity;

@Repository
public interface FideliteRepo extends JpaRepository<Fidelity, Long> {

}
