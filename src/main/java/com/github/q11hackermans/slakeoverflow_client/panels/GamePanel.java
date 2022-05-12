//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client.panels;

import java.awt.*;

import javax.swing.*;

import com.github.q11hackermans.slakeoverflow_client.utility.Logger;
import com.github.q11hackermans.slakeoverflow_client.utility.Numbers;
import com.github.q11hackermans.slakeoverflow_client.utility.Assets;
import com.github.q11hackermans.slakeoverflow_client.utility.Colors;

public class GamePanel extends JPanel implements  Panel {

    private int gameWidth=0;
    private int gameHeight=0;

    private JPanel background=null;
    private JPanel lastBackground=null;

    public GamePanel() {
        createPanel();
    }

    private void createPanel() {
        this.setBackground(Colors.BG);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100)); //todo: properly center
    }



    // RENDERING

    /*
    Render the typical chessboard background (only if field-size changes (for some reason)
     */

    public JPanel getBackgroundJPanel(int[][] fields){
        Logger.info("creating background JPanel");
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        wrapper.setPreferredSize(new Dimension(fields.length * 20, fields.length * 20));

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {

                JPanel nextPanel = new JPanel();
                nextPanel.setPreferredSize(new Dimension(20, 20));

                if (Numbers.isEven(i)) {
                    if (Numbers.isEven(j)) {
                        nextPanel.setBackground(Colors.GROUND_1);
                    } else {
                        nextPanel.setBackground(Colors.GROUND_2);
                    }
                } else {
                    if (Numbers.isEven(j)) {
                        nextPanel.setBackground(Colors.GROUND_2);
                    } else {
                        nextPanel.setBackground(Colors.GROUND_1);
                    }
                }
                wrapper.add(nextPanel);
            }
        }
        return wrapper;
    }


    /*
    Main render method; map new sprites onto chessboard background
     */
    @Override
    public void render(int[][] fields) {
        Logger.info("rendering background");

        if ((fields.length == gameHeight && fields[0].length == gameWidth ) && lastBackground != null){ //no need to check for gameHeight (if the field was at least rendered once)

            JPanel nextFrame = this.background;

            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields[0].length; j++) {
                    nextFrame.add(new JLabel(Assets.getSpriteFromCode(fields[i][j])));
                }
            }



            // lets hope this wont break
            this.remove(this.lastBackground);
            this.add(nextFrame);
        }else{
            // generate background first
            this.background = this.getBackgroundJPanel(fields);
            if(lastBackground == null){
                this.lastBackground = background;
            }

            this.gameHeight = fields.length;
            this.gameWidth = fields[0].length;
            //this.render(fields);

        }

    }

}
