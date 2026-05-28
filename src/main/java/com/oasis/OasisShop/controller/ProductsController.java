package com.oasis.OasisShop.controller;

import com.oasis.OasisShop.Exception.ProductNotFoundException;
import com.oasis.OasisShop.model.Product;
import com.oasis.OasisShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> allProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") int id){
        try{
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ProductNotFoundException e){
            return new ResponseEntity<>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<?> getImageByProductId(@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(productService.getProductById(id).getImageData(), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/product", consumes = {"multipart/form-data"})
    public ResponseEntity<?> addProduct(@RequestPart() Product product, @RequestPart() MultipartFile imageFile) {
        try {
            Product savedProduct = productService.addorUpdateProduct(product, imageFile);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/product/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> updateProduct(@RequestPart() Product product, @RequestPart() MultipartFile imageFile) {
        try {
            return new ResponseEntity<>(productService.addorUpdateProduct(product, imageFile), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
        try{
            Product product = productService.getProductById(id);
            productService.deleteProduct(product);
            return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);
        } catch (ProductNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        return new ResponseEntity<>(productService.searchProducts(keyword), HttpStatus.OK);
    }
}
