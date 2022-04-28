//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.github.q11hackermans.slakeoverflow_client.utility.Numbers;
import com.github.q11hackermans.slakeoverflow_client.utility.Colors;
import com.github.q11hackermans.slakeoverflow_client.components.SnakeHead;

public class GamePanel extends JPanel implements ActionListener {

    GamePanel() {
        this.setBackground(Colors.bg);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

    // takes a 2d array as a representation for the entire playing field
    public void render(int[][] fields) {
        System.out.println("-- render GamePanel");

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        wrapper.setPreferredSize(new Dimension(fields.length * 20, fields.length * 20));

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {

                JPanel p = new JPanel();
                p.setPreferredSize(new Dimension(20, 20));
                if (fields[i][j] == 0) {
                    // create the chessboardy look | surely theres a nicer approach
                    if (Numbers.isEven(i)) {
                        if (Numbers.isEven(j)) {
                            p.setBackground(Colors.green1);
                        } else {
                            p.setBackground(Colors.green2);
                        }
                    } else {
                        if (Numbers.isEven(j)) {
                            p.setBackground(Colors.green2);
                        } else {
                            p.setBackground(Colors.green1);
                        }
                    }
                } else {
                    p.setBackground(Colors.getColorFromCode(fields[i][j]));
                    // p = new SnakeHead();
                }
                wrapper.add(p);
            }
        }

        this.add(wrapper);
    }

}
