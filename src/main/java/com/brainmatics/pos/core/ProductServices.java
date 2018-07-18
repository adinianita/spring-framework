package com.brainmatics.pos.core;

import org.springframework.stereotype.Service;

@Service
public class ProductServices {
    private ProductRepo repository;

    public ProductServices(ProductRepo repository){
        this.repository = repository;
    }

    public String generateCode (){

        return "P" + (repository.getCount() + 1);
    }

    public Product newProduct(){
        Product  result = new Product();
        result.setCcde(generateCode());
        return result;
    }



}
