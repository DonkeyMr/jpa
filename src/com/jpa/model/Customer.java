package com.jpa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "t_customer")
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private Integer age;

    private String email;

    private Date birth;

    @Column(name = "create_date")
    private Date createDate;
}
