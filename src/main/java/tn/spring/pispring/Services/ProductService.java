package tn.spring.pispring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.pispring.Entities.Product;
import tn.spring.pispring.Entities.TypeProduit;
import tn.spring.pispring.Interfaces.ProductInterface;
import tn.spring.pispring.Repositories.ProductRepository;
import tn.spring.pispring.util.FileNamingUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
            //  existingProduct.setImage(product.getImage());


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

    public ProductService(FileNamingUtil fileNamingUtil, @Value("${uploadProductImages}") String uploadProductImages) {
        this.fileNamingUtil = fileNamingUtil;
        this.uploadProductImages = uploadProductImages;
    }

    private final FileNamingUtil fileNamingUtil;
    private final String uploadProductImages;


    public Product addProduct(String name, Float price, String description, int stockQuantity,
                              TypeProduit type, MultipartFile imageFile) {
        try {
            // Copier l'image vers le répertoire de destination
            String fileName = fileNamingUtil.nameFile(imageFile);
            Path destinationPath = Paths.get(uploadProductImages, fileName);
            Files.copy(imageFile.getInputStream(), destinationPath);

            // Créer un nouvel objet Product avec le chemin de l'image
            Product newProduct = new Product();
            newProduct.setName(name);
            newProduct.setPrice(price);
            newProduct.setDescription(description);
            newProduct.setStockQuantity(stockQuantity);
            newProduct.setType(type);
            newProduct.setImage(fileName);
            newProduct.setDateAdded(new Date()); // Définit la date actuelle comme date d'ajout

            // Enregistrer le produit dans la base de données
            return productRepository.save(newProduct);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }
    // Si vous avez besoin de sauvegarder le produit mis à jour dans votre repository de produits, faites-le ici


    public List<Product> getProductsByType(TypeProduit type) {
        return productRepository.findByType(type);
    }


///boucle

    // public Integer getStockQuantityByType(TypeProduit type) {
    //   return productRepository.getStockQuantityByType(type);
    // }


    public List<Product> getFilteredProducts(Float minPrice, Float maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }


    public Product discount(int productId, float discountPercentage) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            // Appliquer le rabais au prix du produit en fonction du pourcentage de réduction
            float discountFactor = 1 - (discountPercentage / 100);
            float discountedPrice = product.getPrice() * discountFactor;

            // Arrondir le prix sans virgule
            discountedPrice = Math.round(discountedPrice);

            product.setPrice(discountedPrice);

            // Mettre à jour le produit dans la base de données
            productRepository.save(product);

            return product;
        } else {
            return null;
        }
    }
}







     //   Optional<Product> optionalProduct = productRepository.findById(productId);
      //  if (optionalProduct.isPresent()) {
        //    Product product = optionalProduct.get();
            // Appliquer le rabais au prix du produit en fonction du pourcentage de réduction
          //  float discountFactor = 1 - (discountPercentage / 100);
          //  product.setPrice(product.getPrice() * discountFactor);

            // Mettre à jour le produit dans la base de données
           // productRepository.save(product);

          //  return product;
     //   } else {
         //   return null;
       // }





























