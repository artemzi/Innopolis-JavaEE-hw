package com.github.artemzi.hw05;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Worker implements Storable {
    private static final String FILE_NAME = "data/DATABASE";

    @Override
    public Employee getByName(String name) {
        ArrayList<Employee> employees = getAllEmployees();
        if (employees != null) {
            for (Employee e : employees) {
                if (e.getName().equals(name)) {
                    return e;
                }
            }
        }
        return null;
    }

    @Override
    public List<Employee> getByJob(Jobs job) {
        ArrayList<Employee> employees = getAllEmployees();
        ArrayList<Employee> result = new ArrayList<>();
        if (employees != null) {
            for (Employee e : employees) {
                if (e.getJob().equals(job)) {
                    result.add(e);
                }
            }
        }
        return result;
    }

    @Override
    public boolean save(Employee employee) {
        ArrayList<Employee> employees = getAllEmployees();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        ) {
            if (employees == null) { // file is empty
                employees = new ArrayList<>();
                employees.add(employee);
                outputStream.writeObject(employees);
                return true;
            } else { // file already have content
                for (Employee e : employees) {
                    if (e.equals(employee)) {
                        outputStream.writeObject(employees); // must write it back before return
                        return false; // provided object already exists in file
                    }
                }
                employees.add(employee);
                outputStream.writeObject(employees);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Employee employee) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Employee employee) {
        return false;
    }

    @Override
    public boolean changeAllWork(Jobs fromJobTitle, Jobs toJobTitle) {
        return false;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> result;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME));
        ) {
            result = (ArrayList<Employee>) inputStream.readObject();
            return result;
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // debug output
    public ArrayList<Employee> getDatabaseFileContent() {
        return getAllEmployees();
    }
}
