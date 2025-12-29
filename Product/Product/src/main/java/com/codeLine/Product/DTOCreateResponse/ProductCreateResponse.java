package com.codeLine.Product.DTOCreateResponse;

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
public class ProductCreateResponse {
    String name;
    String category;
    Float price;
    Integer availableQuantity;
    Date createdDate;
    Date UpdatedDate;
    Boolean isActive;

    // Convert Entity → ResponseDTO
    public static ProductCreateResponse entityToDTOResponse(com.codeLine.Product.Entities.Product entity) {
        return ProductCreateResponse.builder()
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
    public static com.codeLine.Product.Entities.Product convertDTOToEntity(ProductCreateResponse dto) {
        if (Utils.isNull(dto)) {
            throw new IllegalArgumentException(Constants.PRODUCT_CREATE_RESPONSE_IS_NULL);
        }
        return com.codeLine.Product.Entities.Product.builder()
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
