package com.github.q11hackermans.slakeoverflow_client.observe;
import java.util.*;

public class Observable {

    private List<Observer> items = new LinkedList<Observer>();

    public void add(Observer o) {
        System.out.println("-- adding observers");
        this.items.add(o);
    }

    public void remove(Observer o) {
        System.out.println("-- removing observers");
        this.items.remove(o);
    }

    public void push(int[][] i){
        this.items.forEach(a -> {
            a.update(i);
        });
    }

}