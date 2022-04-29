package com.github.q11hackermans.slakeoverflow_client.controller;

import java.awt.event.*;

import com.github.q11hackermans.slakeoverflow_client.view.GameView;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;

public class GameController implements KeyListener {
        private GameView gameView;
        private GameModel gameModel;

        public GameController() {
                this.gameModel = new GameModel();
                this.gameView = new GameView();

                gameModel.add(gameView);

                this.gameView.addKeyListener(this);
        }

        @Override
        public void keyPressed(KeyEvent e) {
                this.gameModel.sendData(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

}
