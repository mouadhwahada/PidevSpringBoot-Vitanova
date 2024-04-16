package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.spring.pispring.Entities.Payment;

import java.math.BigDecimal;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query("SELECT SUM(p.amount) FROM Payment p")
    BigDecimal getTotalPaymentAmount();


}
