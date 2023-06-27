package com.products.api.Controllers;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.products.api.Dtos.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.products.api.Services.ProductService;

// @CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProductController {
   
    @Autowired
    private ProductService productService;
    
    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAllParoducts() throws IOException{
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByProduct(@PathVariable(value = "id") UUID id) throws IOException {
        ProductDTO product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
    }
}
