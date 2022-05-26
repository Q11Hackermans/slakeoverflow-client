package com.github.q11hackermans.slakeoverflow_client.listeners;

public interface GameModelListener {
    public void updateMatrix();

    public void stopGame();

    public void startGame();

    public void lobbyJoined();

    public void lobbyClosed();

}
