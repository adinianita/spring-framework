package com.brainmatics.pos;

import com.brainmatics.pos.core.Product;
import com.brainmatics.pos.core.Sale;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


public class SaleTest {

    @Test
    public void shouldZero (){
        Sale sale = new Sale();
        BigDecimal total= sale.getTotal();

        assertEquals(total, BigDecimal.ZERO);
    }

   @Test
    public  void saleGetTotal(){
        // momogi 2 dan pepsi 1 harus 60000
       Product p1 = new Product();
       p1.setPrice(BigDecimal.valueOf(500));

       Product p2 = new Product();
       p2.setPrice(BigDecimal.valueOf(5_000));

       Sale sale = new Sale();
       sale.addLineItem(p1,2);
       sale.addLineItem(p2,1);

       assertEquals(BigDecimal.valueOf(6000), sale.getTotal());


    }

    @Test
    public void historicalSaleTotal(){
        Product p1 = new Product();
        p1.setPrice(BigDecimal.valueOf(500));

        Sale sale= new Sale();
        sale.addLineItem(p1, 1);

        assertEquals(BigDecimal.valueOf(500), sale.getTotal());

        p1.setPrice(600);
        assertEquals(BigDecimal.valueOf(500), sale.getTotal());
    }
}
