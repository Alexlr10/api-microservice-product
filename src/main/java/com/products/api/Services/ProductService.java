package com.products.api.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.api.Dtos.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ObjectMapper objectMapper;

    public List<ProductDTO> loadProductsFromJson() throws IOException {
        InputStream json = getClass().getClassLoader().getResourceAsStream("products.json");
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String, Object>>(){});

        if (!map.containsKey("products")) {
            throw new IOException("Arquivo JSON inválido: a lista 'products' não foi encontrada.");
        }

        List<Map<String, Object>> productsList = (List<Map<String, Object>>) map.get("products");
        List<ProductDTO> productListDTO = new ArrayList<>();
        for (Map<String, Object> productMap : productsList) {
            UUID uuid = UUID.fromString(productMap.get("uuid").toString());
            String product = (String) productMap.get("product");
            Double price = Double.valueOf(productMap.get("price").toString());
            productListDTO.add(new ProductDTO(uuid, product, price));
        }
        return productListDTO;
    }

    public List<ProductDTO> getAllProducts() throws IOException {
        return loadProductsFromJson();
    }

    public ProductDTO getProductById(UUID id) throws IOException {
        List<ProductDTO> products = loadProductsFromJson();
        return products
                .stream()
                .filter(p -> p.getUuid().equals(id))
                .findFirst()
                .orElse(null);
    }
}
