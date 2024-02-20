package tn.spring.pispring.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Fidelite;

@Repository
public interface FideliteRepo extends JpaRepository<Fidelite, Long> {
}
