package com.github.q11hackermans.slakeoverflow_client.observe;

import java.util.*;

public class Observable {

    private List<Observer> items = new LinkedList<Observer>();

    public void add(Observer o) {
        items.add(o);
    }

    public void remove(Observer o) {
        items.remove(o);
    }

    public void push() {
        items.forEach(a -> {
            a.update();
        });
    }

}