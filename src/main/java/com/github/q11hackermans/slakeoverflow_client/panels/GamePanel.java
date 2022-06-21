//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.components.Layout;
import com.github.q11hackermans.slakeoverflow_client.components.SButton;
import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.utility.Assets;
import com.github.q11hackermans.slakeoverflow_client.utility.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.github.q11hackermans.slakeoverflow_client.utility.SComponents.CTA_HEIGHT;
import static com.github.q11hackermans.slakeoverflow_client.utility.SComponents.FULL_WIDTH;

public class GamePanel extends Panel {

    private JPanel currentFrame = null;
    private JPanel lastFrame = null;

    private JPanel matrixFrame;
    private JLabel map1;
    private JLabel map2;
    private boolean altMap;


    // chat
    private List<String> chatMessages;
    private JLayeredPane pane;
    private JPanel chat;
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
        chatMessages = new ArrayList<>();

        setLayout(new BorderLayout(0, 0));


        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));

        // buttons
        Layout buttonsPanel = new Layout(FULL_WIDTH, CTA_HEIGHT * 2); // *5 makes the button a bit taller

        SButton btnNewButton = new SButton("Back to Lobby");
        btnNewButton.addActionListener(actionListener);
        btnNewButton.setActionCommand(ActionCommands.unAuthPlayer);
        btnNewButton.setWidth(FULL_WIDTH / 2);
        buttonsPanel.addComponentToColumn(btnNewButton);

        SButton btnNewButton_1 = new SButton("Disconnect from Server");
        btnNewButton_1.addActionListener(actionListener);
        btnNewButton_1.setActionCommand(ActionCommands.disconnectButtonPressed);
        btnNewButton.setWidth(FULL_WIDTH / 2);
        buttonsPanel.addComponentToColumn(btnNewButton_1);

        panel.add(buttonsPanel);
        // overlays

        pane = new JLayeredPane();
        pane.setBounds(0, 0, 1200, 800);
        pane.setBackground(
                Color.BLACK
        );
        chat = new JPanel(new GridLayout(11, 0, 0, 0));
        chat.setPreferredSize(new Dimension(600, 300));
        chat.setBackground(
                Colors.OVERLAY_BACKGROUND
        );
        chat.setBounds(800, 600, 600, 300);
        pane.add(chat);

        pane.repaint();
        add(pane);

        this.matrixFrame = new JPanel();
        add(this.matrixFrame, BorderLayout.CENTER);
        this.matrixFrame.setLayout(new GridLayout(0, 1, 0, 0));

        System.out.println("generated");

        JPanel startMapPanel = new JPanel();
        startMapPanel.setLayout(null);

        this.map1 = new JLabel(Assets.MAP1);
        this.map1.setBounds(0, 0, 1200, 800);
        startMapPanel.add(this.map1, 0, 0);

        this.map2 = new JLabel(Assets.MAP2);
        this.map2.setBounds(0, 0, 1200, 800);

        this.altMap = false;

        //JLabel jl2 = new JLabel(Assets.ITEM_APPLE);
        //jl2.setBounds(200,200,20,20);

        this.matrixFrame.add(startMapPanel);

        this.matrixFrame.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
        this.matrixFrame.repaint();
    }


    // RENDERING

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

        if (this.altMap) {
            nextFrame.add(this.map1);
            nextFrame.add(this.map2);
        } else {
            nextFrame.add(this.map2);
            nextFrame.add(this.map1);
        }

        //nextFrame.add(this.map1);

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

    public void applyNextMessage(String msg) {
        chatMessages.add(msg);

        if (chatMessages.size() >= 11) {
            chatMessages.remove(0);
        }


        //remove all current messages
        this.chat.removeAll();


        for (int i = 0; i < chatMessages.size(); i++) {
            this.chat.add(new JLabel(chatMessages.get(i)));
        }


        this.chat.repaint();
        this.pane.repaint();
        this.repaint();
    }

    public void switchAltMap() {
        this.altMap = !this.altMap;
    }
}
