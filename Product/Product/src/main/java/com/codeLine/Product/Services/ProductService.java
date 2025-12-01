package com.codeLine.Product.Services;
import com.codeLine.Product.DTO.ProductRequest;
import com.codeLine.Product.Entities.Product;
import com.codeLine.Product.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // Get all active products
    public List<Product> getAllProducts() {
        List<Product> activeProducts = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (Boolean.TRUE.equals(product.getIsActive())) {
                activeProducts.add(product);
            }
        }
        return activeProducts;
    }

    //Add new product
    public Product addProduct(ProductRequest requestedProduct) {
        if(requestedProduct == null){
            throw new IllegalArgumentException("Product cannot be null");
        }
        Product product = ProductRequest.toEntity(requestedProduct);
        product.setCreatedDate(new Date());
        product.setIsActive(Boolean.TRUE);
        return productRepository.save(product);
    }

    public Product updateProduct(ProductRequest requestedProduct) throws Exception {
        if (requestedProduct == null || requestedProduct.getId() == null) {
            throw new IllegalArgumentException("Product Request cannot be empty and must contain an ID");
        }

        Product existingProduct = productRepository.findById(requestedProduct.getId()).get();
        if (existingProduct != null && existingProduct.getIsActive()) {
            Product product = ProductRequest.toEntity(requestedProduct);
            product.setId(existingProduct.getId());
            product.setUpdatedDate(new Date());
            product.setIsActive(Boolean.TRUE);
            return productRepository.save(product);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    public void deleteProduct(Integer id) throws Exception {
        if (id == null){
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        Product existingProduct = productRepository.findById(id).get();
        if (existingProduct != null && existingProduct.getIsActive()) {
            existingProduct.setUpdatedDate(new Date());
            existingProduct.setIsActive(Boolean.FALSE);
            productRepository.save(existingProduct);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    // Get product by ID if active
    public Product getProductById(Integer id) throws Exception {
        if (id == null){
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        Product existingProduct = productRepository.findById(id).get();
        if (existingProduct != null && existingProduct.getIsActive()) {
            return existingProduct;
        } else {
            throw new Exception("Product not found or is inactive");
        }
    }
//    public Product updateProduct(Product product) throws Exception {
//        Product existingProduct = productRepository.findById(product.getId()).get();
//
//        if (existingProduct != null && existingProduct.getIsActive()) {
//            product.setUpdatedDate(new Date());
//            product.setIsActive(Boolean .TRUE);
//            return productRepository.save(product);
//        } else {
//            throw new Exception("BAD REQUEST");
//        }
//    }
//
//    public void deleteProduct(Integer id) throws Exception {
//        Product existingProduct = productRepository.findById(id).get();
//        if (existingProduct != null && existingProduct.getIsActive()) {
//            existingProduct.setUpdatedDate(new Date());
//            existingProduct.setIsActive(Boolean.FALSE);
//            productRepository.save(existingProduct);
//        } else {
//            throw new Exception("BAD REQUEST");
//        }
//    }
//
//    // Get product by ID if active
//    public Product getProductById(Integer id) throws Exception{
//        Product existingProduct = productRepository.findById(id).get();
//        if (existingProduct != null && existingProduct.getIsActive()){
//            return existingProduct;
//        } else {
//            throw new Exception("Product not found or is inactive");
//        }
//    }
}