package com.codeLine.Product.DTO;

import com.codeLine.Product.Entities.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private Integer id;
    @NotBlank(message = "Name must not be empty")
    private String name;

    @Min(message = "Price must be greater than zero", value = 0)
    private Float price;

    @NotBlank(message = "Category must not be empty")
    private String category;

    @Positive(message = "Quantity must not be negative")
    private Integer availableQuantity;

    //Convert DTO → Entity
    public static Product toEntity(ProductRequest dto) {
        if (dto == null){
            return null;
        }
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .availableQuantity(dto.getAvailableQuantity())
                .build();
    }

    //Convert Entity → DTO
    public static ProductRequest fromEntity(Product entity) {
        if (entity == null){
            return null;
        }
        return ProductRequest.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .availableQuantity(entity.getAvailableQuantity())
                .build();
    }
}
