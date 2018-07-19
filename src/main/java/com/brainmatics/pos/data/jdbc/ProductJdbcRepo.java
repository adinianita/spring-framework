package com.brainmatics.pos.data.jdbc;

import com.brainmatics.pos.core.Product.Product;
import com.brainmatics.pos.core.Product.ProductRepoNonSpring;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductJdbcRepo implements ProductRepoNonSpring {

    String COUNT = "select count(*) from product";

    JdbcTemplate jdbc;

    public ProductJdbcRepo(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public int getCount(){

        return jdbc.query(COUNT, rs -> {
            rs.next();
            return rs.getInt(1);
        });
    }

    @Override
    public Product getById(int id){
        String sql= "SELECT id,code,name,price from product where id=?";
        return jdbc.query(sql,new Object[]{id},rs -> {
            rs.next();
            return map(rs);
        });

    }

    private Product map(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setCcde(rs.getString("code"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getBigDecimal("price"));
        return p;
    }

    @Override
    public List<Product> getAll(){
        return jdbc.query("SELECT * FROM product", (rs, num) ->{
            return map(rs);
        });

    }

    @Override
    public void save (Product product){
        String sql = "INSERT INTO product (id,code,name,price) VALUES (?,?,?,?)";
        jdbc.update(sql,product.getId(),product.getCcde(),product.getName(),product.getPrice());

    }

    @Override
    public void remove (int id){
        String sql = "DELETE FROM product where id=?";
        jdbc.update(sql,id);

    }



}
