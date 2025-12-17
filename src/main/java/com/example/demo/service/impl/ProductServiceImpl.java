package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product createProduct(Product product){
        return productRepository.save(product);
    }
    @Override
    public Product updateProduct(Long id,Product product){
        Optional<Product> lis=productRepository.findById(id);
        if(lis.isPresent()){
            Product old=lis.get();
            old.setSku(product.getSku());
            old.setName(product.getName());
            old.setCategory(product.getCategory());
            old.setPrice(product.getPrice());
            old.setActive(product.getActive());
            old.setCreatedAt(product.getCreatedAt());
            return productRepository.save(old);

    }
    return null;
}
    @Override
    public Product getProductById(Long id){
       Optional<Product> lis = productRepository.findById(id);
      return lis.orElse(null);
        
    }
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @Override
    public void deactiveProduct(Long id){
        Product product=getProductById(id);
        product.setActive(false);
        productRepository.save(product);
    }




}
