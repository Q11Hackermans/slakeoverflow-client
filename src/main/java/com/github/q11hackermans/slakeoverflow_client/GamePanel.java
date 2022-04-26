//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {

    private Color green1;
    private Color green2;
    private Color bg;

    GamePanel() {

        this.green1 = Color.decode("#4aa832");
        this.green2 = Color.decode("#356e26");
        this.bg = Color.decode("#000000");
        this.setBackground(bg); // if a user chooses to resize their window make the rest of the background be
                                // bg.
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

    public boolean isEven(int i) {
        return (i & 1) == 0;
    }

    // takes a 2d array as a representation for the entire playing field
    public void render(int[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                JPanel p = new JPanel();

                // create the chessboardy look | surely theres a nicer approach

                // debug
                // System.out.println(this.isEven(i));
                // System.out.println(this.isEven(j));
                if (this.isEven(i)) {
                    if (this.isEven(j)) {

                        p.setBackground(this.green1);
                    } else {
                        p.setBackground(this.green2);
                    }
                } else {
                    if (this.isEven(j)) {
                        p.setBackground(this.green2);
                    } else {
                        p.setBackground(this.green1);
                    }
                }
                p.setPreferredSize(new Dimension(50, 50));
                this.add(p);

            }
        }
    }

}
