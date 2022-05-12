package com.github.q11hackermans.slakeoverflow_client.observe;

public interface GameObserver extends Observer{
    public void updateGame(int[][] i);
    public void stopGame();
    public void startGame();
}