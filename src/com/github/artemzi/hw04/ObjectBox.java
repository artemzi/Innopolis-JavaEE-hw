package com.github.artemzi.hw04;

import com.github.artemzi.hw04.exception.CustomCastException;

import java.util.*;

public class ObjectBox<T extends Number> {
    private NavigableSet<T> storage = new TreeSet<>();

    ObjectBox(Object[] data) {
        for (Object d : data) {
            try {
                // first part of task. Raise exception if wrong class parent.
                this.storage.add((T) d);
            } catch (ClassCastException e) {
                throw new CustomCastException("Not an instance of Number was passed to the constructor.");
            }
        }
    }

    double summator() {
        double result = 0;
        for (T current : this.storage) {
            result += (double) current;
        }
        return result;
    }

    public List<Double> splitter(int divider) {
        ArrayList<Double> result = new ArrayList<>();
        for (T current : this.storage) {
                result.add((double) current / divider);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox<?> objectBox = (ObjectBox<?>) o;
        return Objects.equals(storage, objectBox.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage);
    }
}
