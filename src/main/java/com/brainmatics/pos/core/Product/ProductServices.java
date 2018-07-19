package com.brainmatics.pos.core.Product;

import org.springframework.stereotype.Service;

@Service
public class ProductServices {
    private ProductRepoNonSpring repository;

    public ProductServices(ProductRepoNonSpring repository){
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
