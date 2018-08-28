package com.github.artemzi.hw05;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class WorkerTest {
    private static final String FILE_NAME = "data/TEST_DATABASE";

    private Worker worker;

    @BeforeEach
    void setUp() {
        worker = new Worker(FILE_NAME);
    }

    @AfterAll
    public static void cleanUp() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert writer != null;
        writer.print("");
        writer.close();
    }

    @Test
    public void employeeCanBeSaved() {
        Employee employee = new Employee("Mike Doe", 25, "100000", Jobs.DEVELOPER);
        boolean saved = worker.save(employee);
        Assertions.assertTrue(saved);
    }

    @Test
    public void getByNameMustReturnEmployee() {
        Employee employee = worker.getByName("Mike Doe");
        Assertions.assertNotNull(employee);
        Assertions.assertEquals(employee.getName(), "Mike Doe");
    }

    @Test
    public void getByJobMustReturnList() {
        List<Employee> employee = worker.getByJob(Jobs.DEVELOPER);
        Assertions.assertNotNull(employee);
        for (Employee e : employee) {
            Assertions.assertEquals(e.getJob(), Jobs.DEVELOPER);
        }
    }
}
