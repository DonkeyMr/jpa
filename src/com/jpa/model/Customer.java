package com.jpa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "t_customer")
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private Integer age;

    private String email;

    private Date birth;

    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    public Customer() {
    }

    public Customer(Integer id, Integer age) {
        this.id = id;
        this.age = age;
    }
}
