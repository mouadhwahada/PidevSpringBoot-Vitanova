package tn.spring.pispring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Product;
import tn.spring.pispring.Interfaces.ProductInterface;
import tn.spring.pispring.Repositories.ProductRepository;

import java.util.List;
import java.util.Map;

@Service
public class ProductService implements ProductInterface {

      @Autowired
    ProductRepository productRepository;


    @Override
    public void addProduct(Product product) {

             productRepository.save(product);
    }

    @Override
    public List<Product> retrieveProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product updateProducts(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);

        // Si le produit existe, mettez à jour ses attributs avec les nouvelles valeurs
        if (existingProduct != null) {
            // Mettez à jour les attributs du produit existant avec les nouvelles valeurs
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStockQuantity(product.getStockQuantity());
            existingProduct.setType(product.getType());
            existingProduct.setImage(product.getImage());


            //   if (product.getImage() != null && product.getImage().length > 0) {
            //     try {
            //       // Décodez la chaîne Base64 de la nouvelle image en un tableau d'octets
            //     byte[] decodedImage = Base64.getDecoder().decode(product.getImage());
            // Mettre à jour l'image du produit avec les données de la nouvelle image décodée
            //   existingProduct.setImage(decodedImage);
            //} catch (IllegalArgumentException e) {
            // Gérer l'exception si la chaîne Base64 n'est pas valide
            //  System.err.println("Erreur lors du décodage de la nouvelle image Base64 : " + e.getMessage());
            //            }
            //          }
//
            // Enregistrez les modifications dans la base de données
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }


    @Override
    public Product retrieveProduct(int idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    @Override
    public void removeProduct(int idProduct) {
        productRepository.deleteById(idProduct);
    }

    @Override
    public List<Product> searchProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public List<Product> getProductsLowStock() {
        return productRepository.findByStockQuantityLessThan(10);
    }


    @Override
    public List<Map<String, Object>> countProductsByType() {
        return productRepository.countProductsByType();
    }
}