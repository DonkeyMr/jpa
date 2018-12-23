package com.jpa.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_manager")
@Data
public class Manager {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "manager_name")
    private String managerName;

    @OneToOne(mappedBy = "manager")
    private Department department;
}
