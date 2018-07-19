package com.brainmatics.pos.core.Product;

import com.brainmatics.common.RepositoryNonSpring;

import java.util.List;

public interface ProductRepoNonSpring extends RepositoryNonSpring<Product> {

     int getCount();
     Product getById (int id);
     List<Product> getAll() ;

     void save (Product product);
     void remove (int id);
}
