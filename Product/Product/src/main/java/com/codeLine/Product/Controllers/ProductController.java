package com.codeLine.Product.Controllers;

import com.codeLine.Product.DTO.ProductRequest;
import com.codeLine.Product.Entities.Product;
import com.codeLine.Product.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("createProduct")
    public String createProduct(@Valid @RequestBody ProductRequest request, BindingResult result) {
        if(result.hasErrors()){
            List<String> errors=result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return "Errors: " + String.join(", ", errors);
        }
        Product product = Product.builder()
                .name(request.getName())
                .category(request.getCategory())
                .price(request.getPrice())
                .availableQuantity(request.getAvailableQuantity())
                .build();
        productService.addProduct(product);
        return "Added Product with ID: " + product.getId();
    }

    @GetMapping("getAllProducts")
    public List<Product> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return productList;
    }

    @PutMapping("updateProduct")
    public Product updateProduct(@RequestBody Product updateObjFromUser) throws Exception {
        return productService.updateProduct(updateObjFromUser);
    }

    @GetMapping("getProductById/{id}")
    public Product getProduct(@PathVariable int id) throws Exception {
        return productService.getProductById(id);
    }

    @DeleteMapping("deleteProduct/{id}")
    public String deleteStudent(@PathVariable int id) throws Exception {
        productService.deleteProduct(id);
        return "SUCCESS";
    }
}