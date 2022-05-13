package com.github.q11hackermans.slakeoverflow_client.listeners;

public interface GameModelListener {
    public abstract  void lobbyJoined();
    public abstract  void lobbyClosed();
    public abstract int[][] nextFrame();

}
