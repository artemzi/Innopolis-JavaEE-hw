package com.github.artemzi.hw05;

public class Main {
    public static void main(String[] args) {
        Worker worker = new Worker();

        Employee employee = new Employee("Mike Doe", 25, "100000", Jobs.CTO);
        boolean saved = worker.save(employee);
        System.out.printf("%s was saved: %b\n", employee, saved);

        Employee employee1 = new Employee("John Smith", 25, "100000", Jobs.CTO);
        boolean saved1 = worker.save(employee1);
        System.out.printf("%s was saved: %b\n", employee1, saved1);

        Employee employee2 = new Employee("John Smith", 25, "100000", Jobs.CTO);
        boolean saved2 = worker.save(employee1);
        System.out.printf("%s was saved: %b\n", employee2, saved2);
    }
}