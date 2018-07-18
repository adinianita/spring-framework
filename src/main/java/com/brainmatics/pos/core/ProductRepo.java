package com.brainmatics.pos.core;

import java.util.ArrayList;

public interface ProductRepo {

     int getCount();
     Product getById (int id);
     ArrayList<Product> getAll() ;

     void save (Product product);
     void remove (int id);
}
