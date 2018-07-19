package com.brainmatics;

import com.brainmatics.pos.core.Product.Product;
import com.brainmatics.pos.core.Sale.SaleLineItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SaleLineItemTest {

    @Test
    public void fgnj(){
        Product p1 = new Product();
        p1.setPrice(BigDecimal.valueOf(500));

        SaleLineItem sli = new SaleLineItem(p1,2);
       // sli.setQuantitiy(2);
     //   sli.setProduct(p1);
       // sli.setUnitPrice(p1.getPrice());
        BigDecimal subtotal = sli.getSubTotal();

        assertEquals(BigDecimal.valueOf(1000), subtotal);

    }
}
