package com.github.artemzi.hw05;

public class Main {
    public static void main(String[] args) {
        Worker worker = new Worker();

        Employee employee = new Employee("Mike Doe", 25, "100000", Jobs.DEVELOPER);
        boolean saved = worker.save(employee);
        System.out.printf("%s was saved: %b\n", employee, saved);

        Employee employee1 = new Employee("John Smith", 25, "100000", Jobs.CTO);
        boolean saved1 = worker.save(employee1);
        System.out.printf("%s was saved: %b\n", employee1, saved1);

        Employee employee2 = new Employee("John Smith", 25, "100000", Jobs.MANAGER);
        boolean saved2 = worker.save(employee1);
        System.out.printf("%s was saved: %b\n", employee2, saved2);

        System.out.println(worker.getDatabaseFileContent());
        System.out.println("[getByName] " + worker.getByName("Mike Doe"));
        System.out.println("[getByJob] " + worker.getByJob(Jobs.CTO));

        worker.delete(employee1);
        System.out.println("[deleted] " + worker.getDatabaseFileContent()); // object deleted

        saved2 = worker.save(employee1);
        System.out.printf("%s was saved: %b\n", employee2, saved2);
        System.out.println("[saved back] " + worker.getDatabaseFileContent());

        Employee employeeForUpdate = new Employee("Mike Doe", 45, "100", Jobs.MANAGER);
        boolean savedUpdate = worker.save(employeeForUpdate);
        System.out.printf("[updated] %s was saved: %b\n", employeeForUpdate, savedUpdate);

        Employee developer1 = new Employee("Mike 1", 11, "10000", Jobs.DEVELOPER);
        Employee developer2 = new Employee("Mike 2", 21, "10000", Jobs.DEVELOPER);
        boolean savedDev1 = worker.save(developer1);
        boolean savedDev2 = worker.save(developer2);
        assert savedDev1 && savedDev2;
        boolean changed1 = worker.changeAllWork(Jobs.CTO, Jobs.MANAGER);
        boolean changed2 = worker.changeAllWork(Jobs.DEVELOPER, Jobs.CTO);
        assert changed1 && changed2;
        System.out.println("[jobs casting] " + worker.getDatabaseFileContent());
    }
}
