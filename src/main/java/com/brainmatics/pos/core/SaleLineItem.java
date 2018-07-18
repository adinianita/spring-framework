package com.brainmatics.pos.core;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SaleLineItem {
    private int quantitiy;
    private BigDecimal unitPrice;
    private Product product;
    private ArrayList<Product> products;

    public int getQuantitiy(int i) {
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

    public Product getProduct(Product p1) {
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

    public BigDecimal getSubTotal(){

        return unitPrice.multiply(BigDecimal.valueOf(quantitiy));
    }
}
