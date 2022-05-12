package com.github.q11hackermans.slakeoverflow_client.observe;

public interface GameObserver extends Observer{
    void updateGame(int[][] i);
    void stopGame();
    void startGame();
}