package com.codeLine.Product.DTOCreateRequest;

import com.codeLine.Product.Entities.Product;
import com.codeLine.Product.Exceptions.CustomException;
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
    public static void validateForCreate(ProductCreateRequest dto) throws CustomException {
        if (Utils.isNull(dto)) {
            throw new CustomException(Constants.PRODUCT_REQUEST_IS_NULL, Constants.HTTP_STATUS_BAD_REQUEST);
        }
        if (Utils.isBlank(dto.getName())) {
            throw new CustomException(Constants.PRODUCT_NAME_IS_NULL, Constants.HTTP_STATUS_BAD_REQUEST);
        }
        if (Utils.isNull(dto.getPrice()) || dto.getPrice() <= Constants.PRODUCT_PRICE_IS_LESS_THAN_ZERO) {
            throw new CustomException(Constants.PRODUCT_PRICE_IS_NEGATIVE , Constants.HTTP_STATUS_BAD_REQUEST);
        }
        if (Utils.isBlank(dto.getCategory())) {
            throw new CustomException(Constants.PRODUCT_CATEGORY_IS_NULL, Constants.HTTP_STATUS_BAD_REQUEST);
        }
        if (Utils.isNull(dto.getAvailableQuantity()) || dto.getAvailableQuantity() < Constants.PRODUCT__AVAILABLE_QUANTITY_IS_LESS_THAN_ZERO) {
            throw new CustomException(Constants.PRODUCT_AVAILABLE_QUANTITY_IS_NULL, Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    //Convert DTO → Entity
    public static Product convertProductDTOToProduct(ProductCreateRequest dto) throws CustomException{
        if (Utils.isNull(dto)) {
            throw new CustomException(Constants.PRODUCT_REQUEST_IS_NULL, Constants.HTTP_STATUS_BAD_REQUEST);
        }
        return Product.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .availableQuantity(dto.getAvailableQuantity())
                .build();
    }

    //Convert Entity → DTO
    public static ProductCreateRequest convertProductToProductDTO(Product entity) throws CustomException {
        if (Utils.isNull(entity)) {
            throw new CustomException(Constants.PRODUCT_IS_NULL, Constants.HTTP_STATUS_BAD_REQUEST);
        }
        return ProductCreateRequest.builder()
                .name(entity.getName())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .availableQuantity(entity.getAvailableQuantity())
                .build();
    }
}
