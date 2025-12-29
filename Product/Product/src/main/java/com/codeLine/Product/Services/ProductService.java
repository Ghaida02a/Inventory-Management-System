package com.codeLine.Product.Services;
import com.codeLine.Product.DTOCreateRequest.ProductCreateRequest;
import com.codeLine.Product.DTOCreateRequest.ProductUpdateRequest;
import com.codeLine.Product.DTOCreateResponse.ProductCreateResponse;
import com.codeLine.Product.Entities.Product;
import com.codeLine.Product.Exceptions.CustomException;
import com.codeLine.Product.Helper.Constants;
import com.codeLine.Product.Helper.Utils;
import com.codeLine.Product.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // Get all active products
    public List<Product> getAllProducts() {
        if (Utils.isListNotEmpty(productRepository.findAllActiveProducts())) {
            return productRepository.findAllActiveProducts();
        } else {
            throw new IllegalArgumentException(Constants.PRODUCT_ACTIVE_LIST_IS_EMPTY);
        }
    }

    //Add new product
    public ProductCreateResponse addProduct(ProductCreateRequest requestedProduct) throws CustomException {
        if(Utils.isNull(requestedProduct)){
            throw new IllegalArgumentException(Constants.PRODUCT_IS_NULL);
        }
        Product product = ProductCreateRequest.convertProductDTOToProduct(requestedProduct);
        product.setCreatedDate(new Date());
        product.setIsActive(Boolean.TRUE);
        return ProductCreateResponse.entityToDTOResponse(productRepository.save(product));
    }

    // Get product by ID if active
    public Product getProductById(Integer id) throws Exception {
        Product productOpt = productRepository.getProductById(id);
        if (Utils.isNotNull(productOpt)) {
            return productOpt;
        } else {
            throw new Exception(Constants.PRODUCT_NOT_FOUND);
        }
    }

    public Product updateProduct(ProductUpdateRequest requestedProduct) throws Exception {
        if (Utils.isNull(requestedProduct) || Utils.isNull(requestedProduct.getId())) {
            throw new IllegalArgumentException(Constants.PRODUCT_UPDATE_REQUEST_IS_NULL);
        }

        Product existingProduct = productRepository.getProductById(requestedProduct.getId());
        if (Utils.isNotNull(existingProduct) && existingProduct.getIsActive()) {
            Product product = ProductUpdateRequest.convertProductDTOToProduct(requestedProduct);
            product.setId(existingProduct.getId());
            product.setUpdatedDate(new Date());
            product.setIsActive(Boolean.TRUE);
            return productRepository.save(product);
        } else {
            throw new Exception(Constants.PRODUCT_UPDATE_REQUEST_ID_NOT_VALID);
        }
    }

    public void deleteProduct(Integer id) throws Exception {
        if (Utils.isNull(id)){
            throw new IllegalArgumentException(Constants.PRODUCT_ID);
        }

        Product existingProduct = productRepository.getProductById(id);
        if (Utils.isNotNull(existingProduct) && existingProduct.getIsActive()) {
            existingProduct.setUpdatedDate(new Date());
            existingProduct.setIsActive(Boolean.FALSE);
            productRepository.save(existingProduct);
        } else {
            throw new Exception(Constants.PRODUCT_IS_NOT_ACTIVE_OR_NOT_FOUND);
        }
    }
}