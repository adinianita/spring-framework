package com.brainmatics.pos.infra.data.inmemory;

import com.brainmatics.pos.core.Product;
import com.brainmatics.pos.core.ProductRepo;

import java.util.ArrayList;


public class ProductMemRepo implements ProductRepo {

    private static ArrayList<Product> data = new ArrayList<>();

    public int getCount(){
        return data.size();
    }

    public Product getById (int id){
        for (Product p :data){
            if (p.getId() == id){
               return p;
            }
        }
        return null;
    }

    public ArrayList<Product> getAll() {

        return data;
    }

    public void save(Product product){
        data.add(product);
    }

    public void remove (int id){
        for (Product p :data){
            if (p.getId() == id){
                data.remove(p);
            }
        }
    }
}
