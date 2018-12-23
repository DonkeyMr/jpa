package com.jpa.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_department")
@Data
public class Department {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "dept_name")
    private String departmentName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
