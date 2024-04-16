package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.Product;

import java.util.List;
import java.util.Map;

public interface ProductInterface {

    void addProduct(Product product);

    List<Product> retrieveProducts();

    Product updateProducts(Product product);

    Product updateProduct(int id,Product product) ;

    Product retrieveProduct (int idProduct);
    void removeProduct (int idProduct);

    List<Product> searchProductByName(String name);

    List<Product> getProductsLowStock();

    List<Map<String, Object>> countProductsByType();


}
