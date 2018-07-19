package com.brainmatics.pos.core.Employee;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    private int id;
    private String name;
    private LocalDate birthdate;

    @Embedded
    private Address home;
  //  private Address office;

    public Address getHome() {
        return home;
    }

    public void setHome(Address home) {
        this.home = home;
    }

  //  public Address getOffice() {
  //      return office;
  //  }

  //  public void setOffice(Address office) {
  //      this.office = office;
  //  }

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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge(){
       return LocalDate.now().getYear() - birthdate.getYear();
    }

    public Employee() {
        this.birthdate = LocalDate.now().minusYears(25);
    }

}
