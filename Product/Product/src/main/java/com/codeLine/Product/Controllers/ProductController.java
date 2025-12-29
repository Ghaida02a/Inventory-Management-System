package com.codeLine.Product.Controllers;

import com.codeLine.Product.DTOCreateRequest.ProductCreateRequest;
import com.codeLine.Product.DTOCreateRequest.ProductUpdateRequest;
import com.codeLine.Product.DTOCreateResponse.ProductCreateResponse;
import com.codeLine.Product.DTOCreateResponse.ProductUpdateResponse;
import com.codeLine.Product.Entities.Product;
import com.codeLine.Product.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    // Create Product
    @PostMapping
    public ResponseEntity<ProductCreateResponse> createProduct(@RequestBody ProductCreateRequest request) throws Exception {
        ProductCreateRequest.validateForCreate(request);
        ProductCreateResponse createdProduct = productService.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductCreateResponse>> getAllProducts() {
        List<ProductCreateResponse> response = productService.getAllProducts().stream()
                .map(ProductCreateResponse::entityToDTOResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductUpdateResponse> getProduct(@PathVariable int id) throws Exception {
        ProductUpdateResponse product = ProductUpdateResponse.entityToDTOResponse(productService.getProductById(id));
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }


    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody ProductUpdateRequest request) throws Exception {
        ProductUpdateRequest.validateForUpdate(request);
        Product updated = productService.updateProduct(request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) throws Exception {
        productService.deleteProduct(id);
        return ResponseEntity.ok("SUCCESS");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Validation error");

        return ResponseEntity.badRequest().body(errorMessage);
    }
}