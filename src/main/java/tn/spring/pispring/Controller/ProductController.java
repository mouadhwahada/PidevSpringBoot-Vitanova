package tn.spring.pispring.Controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Product;
import tn.spring.pispring.Services.ProductService;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@CrossOrigin("*")

@RestController
@RequestMapping("/Product")
public class ProductController {

ProductService productService;
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

}
