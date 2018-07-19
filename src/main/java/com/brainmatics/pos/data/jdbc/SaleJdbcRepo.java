package com.brainmatics.pos.data.jdbc;

import com.brainmatics.pos.core.Employee.Employee;
import com.brainmatics.pos.core.Product.Product;
import com.brainmatics.pos.core.Sale.Sale;
import com.brainmatics.pos.core.Sale.SaleRepoNonSpring;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class SaleJdbcRepo implements SaleRepoNonSpring {

    private JdbcTemplate jdbc;

    public SaleJdbcRepo(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public Sale getByIdEager(int id){
        String sql = "select * from pos.sale s join pos.salelineitem sli on s.id=sli.saleid join product p on p.id = sli.productid \n" +
                "";
        return jdbc.query(sql,new Object[]{}, rs -> {
            Sale sale = new Sale();
            sale.setId(id);
            Employee e = new Employee();
            rs.next();
            e.setId(rs.getInt("employeeid"));
            sale.setCashier(e);
            do {
                Product p = new Product();
                p.setId(rs.getInt("productid"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getBigDecimal("price"));
                sale.addLineItem(p, rs.getInt("quantity"));
            } while (rs.next());


            return sale;
        });
    }

    @Override
    public int getCount() {
        return 0;
    }

    public Sale getById(int id){
        String sql = "select * from pos.sale s join pos.salelineitem sli on s.id=sli.saleid \n" +
                "where s.id = ?";
        return jdbc.query(sql,new Object[]{id}, rs -> {
           Sale sale= new Sale();
           sale.setId(id);
            Employee e = new Employee();
            rs.next();
            e.setId(rs.getInt("employeeid"));
            sale.setCashier(e);
          do {
               Product p = new Product();
               p.setId(rs.getInt("productid"));
               p.setName(rs.getString("name"));
               p.setPrice(rs.getBigDecimal("price"));
               sale.addLineItem(p, rs.getInt("quantity"));
           }while (rs.next());


           return sale;

        });



    }

    @Override
    public List<Sale> getAll() {
        return null;
    }

    @Override
    public void save (Sale sale){
     /*  String sqlSale= "INSERT INTO sale(id,employeeid) values (?,?)";
        jdbc.update(sqlSale,sale.getId(),sale.getCashier().getId());

        String sqlSli = "INSERT into salelineitem (saleid, productid, quantity, price) values (?,?,?,?)";

        for(SaleLineItem sli: sale.getLineItems()){
            jdbc.update(sqlSli,sale.getId(),sli.getProduct().getId(),sli.getQuantitiy(),sli.getUnitPrice());


        } */

        String salesql= String.format("INSERT INTO sale(id,employeeid) values (?, ?)");
        jdbc.update(salesql, sale.getId(), sale.getCashier().getId());

        String sliSql = "INSERT into salelineitem (saleid, productid, quantity, price (?,?,?,?))";
        List<Object[]> paramList = sale.getLineItems().stream()
                .map(sli -> new Object[]{sale.getId(),sli.getProduct().getId(),sli.getQuantitiy(),sli.getUnitPrice()})
                .collect(Collectors.toList());

               jdbc.batchUpdate(salesql,paramList);



    }

    @Override
    public void remove(int id) {

    }
}
