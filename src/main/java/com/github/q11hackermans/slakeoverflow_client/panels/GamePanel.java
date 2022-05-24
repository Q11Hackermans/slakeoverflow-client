//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client.panels;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

import com.github.q11hackermans.slakeoverflow_client.controller.GameController;
import com.github.q11hackermans.slakeoverflow_client.utility.*;
import com.github.q11hackermans.slakeoverflow_client.view.View;

public class GamePanel extends View {

    private JPanel currentFrame = null;
    private JPanel lastFrame = null;
    private GameController gameController;
    private JPanel frame;

    private int width, height;

    private JLabel[][] matrix;

    public GamePanel() {

    }

    public GamePanel(ActionListener actionListener, KeyListener keyListener, GameController gameController) {
        this.gameController = gameController;
        this.width = 60;
        this.height = 40;
        this.createPanel(actionListener, keyListener);
        this.addKeyListener(keyListener);
    }

    private void createPanel(ActionListener actionListener, KeyListener keyListener) {
        setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(0, 3, 0, 0));

        JButton btnNewButton = new JButton("Back to Lobby");
        btnNewButton.addActionListener(actionListener);
        btnNewButton.setActionCommand(ActionCommands.backToLobbyButton);
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Disconnect from Server");
        btnNewButton_1.addActionListener(actionListener);
        btnNewButton_1.setActionCommand(ActionCommands.disconnectButtonPressed);
        panel.add(btnNewButton_1);

        JPanel panel_1 = new JPanel();
        add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));

        frame = new JPanel();
        //frame.setBackground(Color.GRAY);
        frame.setLayout(new GridLayout(this.height, this.width, 0, 0));
        panel_1.add(frame);

        this.matrix = generateBackground(this.height, this.width); // apparently the server sends a 40x60 matrix
        System.out.println("generated");

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.frame.add(matrix[i][j]);
            }
        }
        frame.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
        frame.repaint();
    }


    // RENDERING


    private JLabel[][] generateBackground(int height, int width) {
        JLabel[][] background = new JLabel[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {


                JLabel next = new JLabel("as");

                if (Numbers.isEven(i)) {
                    if (!Numbers.isEven(j)) {
                        next.setBackground(Colors.GROUND_1);
                    } else {
                        next.setBackground(Colors.GROUND_2);
                    }
                } else {
                    if (Numbers.isEven(j)) {
                        next.setBackground(Colors.GROUND_1);
                    } else {
                        next.setBackground(Colors.GROUND_2);
                    }
                }
                next.setOpaque(true);
                background[i][j] = next;
            }
        }

        return background;
    }

    /*
    create background and apply sprites depending on received fieldState
     */

    public void render(int[][] fields) {

        /*Logger.info("creating background JPanel, size:" + fields.length + ", " + fields[0].length);
        JPanel newFrame = new JPanel();
        newFrame.setLayout(new GridLayout(fields.length, fields[0].length, 0, 0));

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                JLabel jL = new JLabel(Assets.getSpriteFromCode(fields[i][j]));

                // background
                if (Numbers.isEven(i)) {
                    if (Numbers.isEven(j)) {
                        jL.setBackground(Colors.GROUND_1);
                    } else {
                        jL.setBackground(Colors.GROUND_2);
                    }
                } else {
                    if (Numbers.isEven(j)) {
                        jL.setBackground(Colors.GROUND_2);
                    } else {
                        jL.setBackground(Colors.GROUND_1);
                    }
                }
                jL.setOpaque(true);
                newFrame.add(jL);
            }
        }
        this.add(newFrame);
        SwingUtilities.updateComponentTreeUI(this); // should replace the tow revalidate() functions above!!
        this.frame.repaint();
        this.frame.removeAll();
        this.remove(frame);
        this.frame = newFrame;

        SwingUtilities.updateComponentTreeUI(this); // should replace the tow revalidate() functions above!!
        this.frame.repaint();
        */
    }


    /*
    remove last frame and replace with next frame
     */
    public void applyNextFrame(JPanel nextFrame) {
        this.lastFrame = this.currentFrame;
        this.currentFrame = nextFrame;
        if (lastFrame != null) {
            this.remove(lastFrame);
        }
        this.add(this.currentFrame);
        this.revalidate();
    }
}
