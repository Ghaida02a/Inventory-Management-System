package com.codeLine.Product.DTOCreateRequest;

import com.codeLine.Product.Entities.Product;
import com.codeLine.Product.Helper.Constants;
import com.codeLine.Product.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCreateRequest {
    private String name;
    private Float price;
    private String category;
    private Integer availableQuantity;

    //Validation for create
    public static void validateForCreate(ProductCreateRequest dto) throws Exception {
        if (Utils.isNull(dto)) {
            throw new IllegalArgumentException(Constants.PRODUCT_REQUEST_IS_NULL);
        }
        if (Utils.isBlank(dto.getName())) {
            throw new IllegalArgumentException(Constants.PRODUCT_NAME_IS_NULL);
        }
        if (Utils.isNull(dto.getPrice()) || dto.getPrice() <= Constants.PRODUCT_PRICE_IS_LESS_THAN_ZERO) {
            throw new IllegalArgumentException(Constants.PRODUCT_PRICE_IS_NEGATIVE);
        }
        if (Utils.isBlank(dto.getCategory())) {
            throw new IllegalArgumentException(Constants.PRODUCT_CATEGORY_IS_NULL);
        }
        if (Utils.isNull(dto.getAvailableQuantity()) || dto.getAvailableQuantity() < Constants.PRODUCT__AVAILABLE_QUANTITY_IS_LESS_THAN_ZERO) {
            throw new IllegalArgumentException(Constants.PRODUCT_AVAILABLE_QUANTITY_IS_NULL);
        }
    }

    //Convert DTO → Entity
    public static Product convertProductDTOToProduct(ProductCreateRequest dto) {
        if (Utils.isNull(dto)) {
            throw new IllegalArgumentException(Constants.PRODUCT_REQUEST_IS_NULL);
        }
        return Product.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .availableQuantity(dto.getAvailableQuantity())
                .build();
    }

    //Convert Entity → DTO
    public static ProductCreateRequest convertProductToProductDTO(Product entity) {
        if (Utils.isNull(entity)) {
            throw new IllegalArgumentException(Constants.PRODUCT_IS_NULL);
        }
        return ProductCreateRequest.builder()
                .name(entity.getName())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .availableQuantity(entity.getAvailableQuantity())
                .build();
    }
}
