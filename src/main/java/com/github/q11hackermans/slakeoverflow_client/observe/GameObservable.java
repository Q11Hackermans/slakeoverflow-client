package com.github.q11hackermans.slakeoverflow_client.observe;

import com.github.q11hackermans.slakeoverflow_client.utility.Logger;

import java.util.LinkedList;
import java.util.List;

public class GameObservable {
    /*
     * More boilerplate code
     */
    private List<GameObserver> items = new LinkedList<GameObserver>();


    // GETTERS + LOGGING
    public List<GameObserver> getItems() {
        return items;
    }

    public void log(String message){
        Logger.info("> GameObservable | " + message);
    }



    // OBSERVERS
    public void add(GameObserver o) {
        log("adding observer");
        this.items.add(o);
    }

    public void remove(GameObserver o) {
        log("removing observer");
        this.items.remove(o);
    }



    // METHODS

    /*
        Rerender / update a gamePanel if new data is received from the server
     */
    public void updateGame(int[][] i) {
        this.items.forEach(a -> {
            a.updateGame(i);
        });
    }


    /*
    start game if the server approves the connection
     */

    public void startGame(){
        this.items.forEach(a -> {
            a.startGame();
        });
    }

    /*
    close gamePanel if the server closes a session
     */
    public void stopGame(){
        this.items.forEach(a -> {
            a.stopGame();
        });
    }


}
