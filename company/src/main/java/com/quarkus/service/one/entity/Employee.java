package com.quarkus.service.one.entity;

import jakarta.persistence.*;

@Entity
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 64, unique = true)
    private String name;
    @Column(length = 64)
    private String position;

    @ManyToOne
    @JoinColumn(name = "department_name", referencedColumnName = "department_name")
    private Department department;

    public Employee() {
    }

    public Employee(String name, String position, Department department) {
        this.name = name;
        this.position = position;
        this.department = department;
    }

    public Employee(Long id, String name, String position, Department department) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
