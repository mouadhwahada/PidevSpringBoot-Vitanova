package tn.spring.pispring.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.CartItems;

import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems,Long> {

    Optional<CartItems> findByAbonnementIdAndOrderIdAndUserId(Long abonnementId,Long orderId , Long userId);
}
