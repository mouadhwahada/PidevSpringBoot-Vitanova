package tn.spring.pispring.Controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.pispring.Entities.Product;
import tn.spring.pispring.Entities.TypeProduit;
import tn.spring.pispring.Repositories.ProductRepository;
import tn.spring.pispring.Services.ProductService;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@CrossOrigin("*")

@RestController
@RequestMapping("/Product")
public class ProductController {

    @Autowired
ProductService productService;
@Autowired
ProductRepository productRepository;
    @PostMapping("/addProduct")
    public Product addNewProduct(@RequestBody Product product) {

        productService.addProduct(product);
        return product;
    }

    @GetMapping("/retrieveproduct")
    public List<Product> getProducts() {
        return productService.retrieveProducts();

    }

    @PutMapping("/updateProduct")
    public Product updateProducts(@RequestBody Product product) {
        return productService.updateProducts(product);
    }

    @GetMapping("/retrieveproduct/{idProduct}")
    public Product getProductById(@PathVariable("idProduct") int idProduct) {
        return productService.retrieveProduct(idProduct);
    }


    @DeleteMapping("/removeProduct/{idProduct}")
    public void removeBloc(@PathVariable("idProduct") int idProduct) {
        productService.removeProduct(idProduct);
    }

    @PutMapping("/updateProducts/{idProduct}")
    public Product updateProduct(@PathVariable("idProduct") int id, @RequestBody Product newProductData) {
        return productService.updateProduct(id, newProductData);

    }

    @GetMapping("/search")
    public List<Product> searchProductByName(@RequestParam String name) {
        return productService.searchProductByName(name);
    }

    @GetMapping("/retrieveproductlowstock")
    public List<Product> getProductsLowStock() {
        return productService.getProductsLowStock();
    }


    @GetMapping("/countbytype")
    public List<Map<String, Object>> countProductsByType() {
        return productService.countProductsByType();


    }

    @PostMapping("/addProductwith")
    public Product addProductwith(@RequestParam("name") String name,
                                                  @RequestParam("price") Float price,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("stockQuantity") Integer stockQuantity,
                                                  @RequestParam("type") TypeProduit type,
                                                  @RequestParam("imageFile") MultipartFile imageFile) {
        return productService.addProduct(name, price, description, stockQuantity, type, imageFile);
        // Définir les attributs isFavourite et dateAdded

    }



    @GetMapping("/byType")
    public List<Product> getProductsByType(@RequestParam TypeProduit type) {
        return productService.getProductsByType(type);
    }

  //  @GetMapping("/stockQuantityByType")
    //public Integer getStockQuantityByType(@RequestParam TypeProduit type) {
      //  return productService.getStockQuantityByType(type);
    //}




    @GetMapping("/productsByMarge")
    public List<Product> getFilteredProducts(@RequestParam(required = false) Float minPrice,
                                             @RequestParam(required = false) Float maxPrice) {
        return productService.getFilteredProducts(minPrice, maxPrice);
    }


    @PutMapping("/{productId}/discount")
    public ResponseEntity<Product> applyDiscount(@PathVariable int productId, @RequestParam float discountPercentage) {
        Product product = productService.discount(productId, discountPercentage);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
