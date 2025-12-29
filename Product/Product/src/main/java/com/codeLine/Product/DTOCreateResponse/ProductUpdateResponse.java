package com.codeLine.Product.DTOCreateResponse;

import com.codeLine.Product.Entities.Product;
import com.codeLine.Product.Exceptions.CustomException;
import com.codeLine.Product.Helper.Constants;
import com.codeLine.Product.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateResponse {
    private Integer id;
    private String name;
    private String category;
    private Float price;
    private Integer availableQuantity;
    private Date createdDate;
    private Date UpdatedDate;
    private Boolean isActive;

    // Convert Entity → ResponseDTO
    public static ProductUpdateResponse entityToDTOResponse(Product entity) {
        return ProductUpdateResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .availableQuantity(entity.getAvailableQuantity())
                .createdDate(entity.getCreatedDate())
                .UpdatedDate(entity.getUpdatedDate())
                .isActive(entity.getIsActive())
                .build();
    }

    //DTO Response -> Entity
    public static Product convertDTOToEntity(ProductUpdateResponse dto) throws CustomException {
        if (Utils.isNull(dto)) {
            throw new CustomException(Constants.PRODUCT_UPDATE_RESPONSE_IS_NULL, Constants.HTTP_STATUS_BAD_REQUEST);
        }
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .availableQuantity(dto.getAvailableQuantity())
                .createdDate(dto.getCreatedDate())
                .UpdatedDate(dto.getUpdatedDate())
                .isActive(dto.getIsActive())
                .build();
    }
}
