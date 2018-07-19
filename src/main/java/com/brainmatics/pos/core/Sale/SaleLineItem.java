package com.brainmatics.pos.core.Sale;

import com.brainmatics.pos.core.Product.Product;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Embeddable
public class SaleLineItem {
    private int quantitiy;
    private BigDecimal unitPrice;

    @ManyToOne
    private Product product;

    public int getQuantitiy() {
        return quantitiy;
    }

    public void setQuantitiy(int quantitiy) {
        this.quantitiy = quantitiy;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        this.unitPrice = product.getPrice();
    }

    public SaleLineItem(Product product, int quantitiy){
        setProduct(product);
        setQuantitiy(quantitiy);
        setUnitPrice(product.getPrice());
    }

    public SaleLineItem(){ }

    public BigDecimal getSubTotal(){

        return unitPrice.multiply(BigDecimal.valueOf(quantitiy));
    }


}
