package com.github.artemzi.hw05;

import java.math.BigDecimal;

public class Employee {

    private String name;
    private int age;
    private BigDecimal salary;
    private enum Job {
        MANAGER, DEVELOPER, HR, CEO, COO, CTO, EXECUTIVE;
    }

    public Employee(String name, int age, BigDecimal salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
