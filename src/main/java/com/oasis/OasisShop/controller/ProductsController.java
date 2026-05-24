package com.oasis.OasisShop.controller;

import com.oasis.OasisShop.model.Product;
import com.oasis.OasisShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> allProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
}
