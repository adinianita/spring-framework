package com.brainmatics.pos.core.Sale;

import com.brainmatics.pos.core.Employee.Employee;
import com.brainmatics.pos.core.Product.Product;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sale {

    @Id
    private int id;
    private LocalDateTime time;

    @ManyToOne
    private Employee cashier;

    @ElementCollection
    private List<SaleLineItem> lineItems;

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

    public List<SaleLineItem> getLineItems() {
        return lineItems;
    }



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
