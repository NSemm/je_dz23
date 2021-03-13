package com.k7;

import java.util.ArrayList;
import java.util.List;

public class Condition {
    private final List<Object> objectsArray = new ArrayList<>();

    public void set(Object o) {
        objectsArray.add(o);
    }

    public void removeAll() {
        for (int i = 0; i < objectsArray.size(); i++) {
            objectsArray.remove(i);
        }
    }

    public List<Object> getList() {
        return objectsArray;
    }
}
