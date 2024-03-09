package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.spring.pispring.Entities.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByNameContaining(String name);
    List<Product> findByStockQuantityLessThan(int quantity);

    @Query(value = "SELECT type, COUNT(*) AS count FROM Product GROUP BY type", nativeQuery = true)
    List<Map<String, Object>> countProductsByType();

}
