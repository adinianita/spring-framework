package com.brainmatics.pos.infra.data.mongodb;

import com.brainmatics.pos.core.Product.ProductRepoNonSpring;
import com.brainmatics.pos.core.Product.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ProductMongoRepo implements ProductRepoNonSpring {

    private static ArrayList<Product> data = new ArrayList<>();

    public int getCount(){

        System.out.println("from mongodb");
        return 1;
    }

    public Product getById (int id){
        System.out.println("from mongodb");
        return new Product();
    }

    public ArrayList<Product> getAll() {

        return new ArrayList<>();
    }

    public void save(Product product){


    }

    public void remove (int id){

    }
}
