package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.spring.pispring.Entities.Product;
import tn.spring.pispring.Entities.TypeProduit;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByNameContaining(String name);
    List<Product> findByStockQuantityLessThan(int quantity);
    List<Product> findByType(TypeProduit type);


    @Query(value = "SELECT type, COUNT(*) AS count FROM Product GROUP BY type", nativeQuery = true)
    List<Map<String, Object>> countProductsByType();

 //   @Query("SELECT SUM(p.stockQuantity) FROM Product p WHERE p.type = :type")
   // Integer getStockQuantityByType(@Param("type") TypeProduit type);






    List<Product> findByPriceBetween(Float minPrice, Float maxPrice);




}
