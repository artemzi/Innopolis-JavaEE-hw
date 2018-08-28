package com.github.artemzi.hw05;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

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

    /**
     * For now, equals method of Employee class check only
     * two fields, name, and job, so objects are equals if
     * both fields are same. This means that we can use this
     * fields for exact much.
     *
     * @param employee object for deleting
     * @return true if succeed
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean delete(Employee employee) {
        // we can use here getByJob for reduce collection size, but
        // this will require more moves with rewriting collection after removing element
        ArrayList<Employee> allEmployees = getAllEmployees();
        // avoiding java.util.ConcurrentModificationException
        ArrayList<Employee> listForRemoving = new ArrayList<>();
        assert allEmployees != null;
        for (Employee e : allEmployees) {
            if (e.equals(employee)) {
                listForRemoving.add(e);
            }
        }
        allEmployees.removeAll(listForRemoving);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        ) {
            outputStream.writeObject(allEmployees);
            return true;
        } catch (IOException e1) {
            System.err.println(e1.getMessage());
        }
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
