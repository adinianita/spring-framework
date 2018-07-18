package com.brainmatics.pos.core;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.Date;

public class Employee {
    private int id;
    private String name;
    private LocalDate birthdate;

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
