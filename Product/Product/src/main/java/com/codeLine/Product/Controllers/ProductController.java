package com.codeLine.Product.Controllers;

import com.codeLine.Product.DTO.ProductRequest;
import com.codeLine.Product.Entities.Product;
import com.codeLine.Product.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    // Create Product
    @PostMapping("/createProduct")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductRequest request) {
        productService.addProduct(request); // save product
        return ResponseEntity.status(HttpStatus.CREATED).body("Added Successfully");
    }

    @GetMapping("getAllProducts")
    public List<Product> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return productList;
    }

    @PutMapping("updateProduct")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody ProductRequest request) throws Exception {
        Product updated = productService.updateProduct(request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("getProductById/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) throws Exception {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) throws Exception {
        productService.deleteProduct(id);
        return ResponseEntity.ok("SUCCESS");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage()) // just display the message
                .orElse("Validation error");

        return ResponseEntity.badRequest().body(errorMessage);
    }
}