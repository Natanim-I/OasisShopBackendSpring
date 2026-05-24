package com.oasis.OasisShop.service;

import com.oasis.OasisShop.model.Product;
import com.oasis.OasisShop.repo.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepo;

    public List<Product> getAllProducts(){
        return productsRepo.findAll();
    }

    public Product getProductById(int id) {
        return productsRepo.findById(id).orElse(null);
    }

    public Product addProduct(Product product){
        return productsRepo.save(product);
    }

    public Product updateProduct(Product product) {
        return productsRepo.save(product);
    }

    public void deleteProduct(int id) {
        productsRepo.deleteById(id);
    }
}
