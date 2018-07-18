package com.brainmatics.pos.core;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sale {
    private int id;
    private LocalDateTime time;
    private Employee cashier;
    private ArrayList<SaleLineItem> lineItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Employee getCashier() {
        return cashier;
    }

    public void setCashier(Employee cashier) {
        this.cashier = cashier;
    }

//    public ArrayList<SaleLineItem> getLineItems() {
//        return lineItems();
//    }



    // public void setProducts(ArrayList<Product> products) {
   //     this.products = products;
  //  }

    public Sale(){
        time = LocalDateTime.now();
        lineItems = new ArrayList<>();
    }

    public void addLineItem(Product product, int quantity){
        SaleLineItem sli = new SaleLineItem(product,quantity);
        lineItems.add(sli);
    }

    public BigDecimal getTotal(){
        lineItems.stream().map(SaleLineItem::getSubTotal).reduce(BigDecimal.ZERO, (a,b) -> a.add(b));

        BigDecimal total = BigDecimal.ZERO;
        for(SaleLineItem sli : lineItems)
            total = total.add(sli.getSubTotal());
        return total;
    }
}
