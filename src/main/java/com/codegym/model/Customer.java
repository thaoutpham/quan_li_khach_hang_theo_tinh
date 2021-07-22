package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String firstName;
private String lastName;
private String province;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String province) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
