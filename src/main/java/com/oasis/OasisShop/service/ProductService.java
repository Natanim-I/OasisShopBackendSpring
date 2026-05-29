package com.oasis.OasisShop.service;

import com.oasis.OasisShop.Exception.ProductNotFoundException;
import com.oasis.OasisShop.model.Product;
import com.oasis.OasisShop.repo.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepo;

    public List<Product> getAllProducts(){
        return productsRepo.findAll();
    }

    public Product getProductById(int id) throws ProductNotFoundException {
        return productsRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return productsRepo.save(product);
    }

    public Product updateProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return productsRepo.save(product);
    }

    public void deleteProduct(Product product){
        productsRepo.delete(product);
    }

    public List<Product> searchProducts(String keyword) {
        return productsRepo.searchProducts(keyword);
    }
}
