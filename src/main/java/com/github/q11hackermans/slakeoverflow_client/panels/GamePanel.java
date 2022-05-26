//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client.panels;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.*;

import com.github.q11hackermans.slakeoverflow_client.utility.*;
import com.github.q11hackermans.slakeoverflow_client.view.View;

public class GamePanel extends View {

    private JPanel currentFrame = null;
    private JPanel lastFrame = null;

    private JPanel matrixFrame;
    private JLabel map;

    private int width, height;

    private JLabel[][] matrix;

    public GamePanel() {

    }

    public GamePanel(ActionListener actionListener) {
        this.width = 59; // one smaller than the matrix (60)
        this.height = 39; // one smaller than the matrix (40)
        this.createPanel(actionListener);
    }

    private void createPanel(ActionListener actionListener) {
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

        this.matrixFrame = new JPanel();
        add(this.matrixFrame, BorderLayout.CENTER);
        this.matrixFrame.setLayout(new GridLayout(0, 1, 0, 0));

        System.out.println("generated");

        JPanel startMapPanel = new JPanel();
        startMapPanel.setLayout(null);

        this.map = new JLabel(Assets.MAP);
        this.map.setBounds(0, 0, 1200, 800);
        startMapPanel.add(this.map);

        //JLabel jl2 = new JLabel(Assets.ITEM_APPLE);
        //jl2.setBounds(200,200,20,20);

        this.matrixFrame.add(startMapPanel);

        this.matrixFrame.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
        this.matrixFrame.repaint();
    }


    // RENDERING


    private JLabel[][] generateBackground(int height, int width) {
        JLabel[][] background = new JLabel[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {


                JLabel next = new JLabel();

                if (Numbers.isEven(i)) {
                    if (Numbers.isEven(j)) {
                        next.setBackground(Colors.GROUND_1);
                    } else {
                        next.setBackground(Colors.GROUND_2);
                    }
                } else {
                    if (Numbers.isEven(j)) {
                        next.setBackground(Colors.GROUND_2);
                    } else {
                        next.setBackground(Colors.GROUND_1);
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
        //System.out.println(Arrays.deepToString(fields));

        JPanel nextFrame = new JPanel();
        nextFrame.setLayout(null);
        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields[0].length; y++) {
                if (fields[x][y] > 0) {
                    JLabel nextSprite = new JLabel(Assets.getSpriteFromCode(fields[x][y]));
                    nextSprite.setBounds((x * 20), (y * 20), 20, 20);
                    nextFrame.add(nextSprite);
                }
            }
        }
        nextFrame.add(this.map);
        this.applyNextFrame(nextFrame);
    }


    /*
    remove last frame and replace with next frame
     */
    public void applyNextFrame(JPanel nextFrame) {
        this.lastFrame = this.currentFrame;
        this.currentFrame = nextFrame;
        if (lastFrame != null) {
            this.matrixFrame.removeAll();
        }
        this.matrixFrame.add(this.currentFrame);
        this.matrixFrame.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
        this.matrixFrame.repaint();
        //System.out.println("next Frame");
    }
}
