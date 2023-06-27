package com.products.api.Dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    
    private UUID uuid;

    private String product;

    private Double price;

    public ProductDTO(UUID uuid, String product, Double price) {
        this.uuid = uuid;
        this.product = product;
        this.price = price;
    }
}
