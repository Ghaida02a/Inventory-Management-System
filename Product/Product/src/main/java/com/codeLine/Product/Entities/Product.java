package com.codeLine.Product.Entities;

import com.codeLine.Product.DTO.ProductRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
            @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    Integer id;
    String name;
    String category;
    Float price;
    Integer availableQuantity;
    Date createdDate;
    Date UpdatedDate;
    Boolean isActive;

    //Entity → DTO
    public static ProductRequest toDTO(Product entity) {
        return ProductRequest.fromEntity(entity);
    }

    //DTO → Entity
    public static Product fromDTO(ProductRequest dto) {
        return ProductRequest.toEntity(dto);
    }
}
