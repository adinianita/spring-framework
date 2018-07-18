package com.brainmatics.pos.core;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String ccde;
    private String name;
    private BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(int price){
        setPrice(BigDecimal.valueOf(price));
    }

    public String getCcde() {
        return ccde;
    }

    public void setCcde(String ccde) {
        this.ccde = ccde;
    }
}
