package com.codeLine.Product.Entities;

import com.codeLine.Product.DTOCreateRequest.ProductCreateRequest;
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
    public static ProductCreateRequest toDTO(Product entity) {
        return ProductCreateRequest.convertProductToProductDTO(entity);
    }

    //DTO → Entity
    public static Product fromDTO(ProductCreateRequest dto) {
        return ProductCreateRequest.convertProductDTOToProduct(dto);
    }
}
