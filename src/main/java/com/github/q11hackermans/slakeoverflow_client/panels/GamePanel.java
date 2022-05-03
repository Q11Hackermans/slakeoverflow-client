//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client.panels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.github.q11hackermans.slakeoverflow_client.utility.Numbers;
import com.github.q11hackermans.slakeoverflow_client.utility.Assets;
import com.github.q11hackermans.slakeoverflow_client.utility.Colors;

public class GamePanel extends JPanel implements ActionListener {

    public GamePanel() {
        this.setBackground(Colors.bg);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100)); //todo: properly center
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
            
                // create the chessboardy background
                if (Numbers.isEven(i)) {
                    if (Numbers.isEven(j)) {
                        p.setBackground(Colors.ground1);
                    } else {
                        p.setBackground(Colors.ground2);
                    }
                } else {
                    if (Numbers.isEven(j)) {
                        p.setBackground(Colors.ground2);
                    } else {
                        p.setBackground(Colors.ground1);
                    }
                }
                
                p.add(new JLabel(Assets.getSpriteFromCode(fields[i][j])));                    

                wrapper.add(p);
            }
        }

        this.add(wrapper);
    }

}
