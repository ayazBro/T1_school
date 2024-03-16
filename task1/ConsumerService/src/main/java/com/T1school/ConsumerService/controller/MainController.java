package com.T1school.ConsumerService.controller;

import com.T1school.ConsumerService.dto.Product;
import com.T1school.ConsumerService.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/client")
public class MainController {

    private final ProductService productService;


    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(value = "page", required = false) Integer pageNumber,
            @RequestParam(value = "size", required = false) Integer pageSize
    ) {
        Page<Product> response = productService.findAll(Optional.ofNullable(minPrice), Optional.ofNullable(maxPrice),
                Optional.ofNullable(category), Optional.ofNullable(pageNumber), Optional.ofNullable(pageSize));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/products/searchingByName")
    public ResponseEntity<List<Product>> getProductByName(@RequestParam String name) {
        List<Product> products = productService.findByName(name);
        return ResponseEntity.ok(products);
    }
    @PostMapping("/products")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid Product product) {
        Product newProduct = productService.save(product);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product) {
        productService.update(product, id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}