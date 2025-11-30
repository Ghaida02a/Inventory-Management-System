package com.codeLine.Product.DTO;

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
    @NotBlank(message = "Name must not be empty")
    String name;

    @Min(message = "Price must be greater than zero", value = 0)
    Float price;

    @NotBlank(message = "Category must not be empty")
    String category;

    @Positive(message = "Quantity must not be negative")
    Integer availableQuantity;
}
