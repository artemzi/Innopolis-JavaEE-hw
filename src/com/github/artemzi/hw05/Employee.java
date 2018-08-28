package com.github.artemzi.hw05;

import java.math.BigDecimal;
import java.util.List;

/**
 * Employee class required by home work assignment
 */
public class Employee implements Storable {

    private String name;
    private int age;
    // salary value stored in cents
    private BigDecimal salary;
    private Jobs job;

    public Employee(String name, int age, String salary, Jobs job) {
        this.name = name;
        this.age = age;
        this.salary = new BigDecimal(salary);
        this.job = job;
    }

    @Override
    public boolean save(Employee employee) {
        return false;
    }

    @Override
    public boolean delete(Employee employee) {
        return false;
    }

    @Override
    public Employee getByName() {
        return null;
    }

    @Override
    public List<Employee> getByJob(Jobs job) {
        return null;
    }

    @Override
    public boolean saveOrUpdate(Employee employee) {
        return false;
    }

    @Override
    public boolean changeAllWork(Jobs fromJobTitle, Jobs toJobTitle) {
        return false;
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

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", job=" + job +
                '}';
    }
}
