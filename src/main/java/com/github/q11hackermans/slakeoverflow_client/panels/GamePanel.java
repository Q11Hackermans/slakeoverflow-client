//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client.panels;

import java.awt.*;

import javax.swing.*;

import com.github.q11hackermans.slakeoverflow_client.utility.Logger;
import com.github.q11hackermans.slakeoverflow_client.utility.Numbers;
import com.github.q11hackermans.slakeoverflow_client.utility.Assets;
import com.github.q11hackermans.slakeoverflow_client.utility.Colors;

public class GamePanel extends JPanel {

    private JPanel currentFrame =null;
    private JPanel lastFrame =null;

    public GamePanel() {
        createPanel();
    }

    private void createPanel() {
        this.setBackground(Colors.BG);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100)); //todo: properly center
    }



    // RENDERING

    /*
    create background and apply sprites depending on received fieldState
     */

    public void render(int[][] fields){
        Logger.info("creating background JPanel");
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        wrapper.setPreferredSize(new Dimension(fields.length * 20, fields.length * 20));

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {

                JPanel nextPanel = new JPanel();
                nextPanel.setPreferredSize(new Dimension(20, 20));

                // background
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

                // add sprite
                nextPanel.add(new JLabel(Assets.getSpriteFromCode(fields[i][j])));
                // https://stackoverflow.com/questions/11069807/jpanel-doesnt-update-until-resize-jframe | need to call revalidate() for updates to show up
                nextPanel.revalidate();

                wrapper.add(nextPanel);
                wrapper.revalidate();
            }
        }

        this.applyNextFrame(wrapper);
    }



    /*
    remove last frame and replace with next frame
     */
    public void applyNextFrame(JPanel nextFrame){
        this.lastFrame = this.currentFrame;
        this.currentFrame = nextFrame;
        if (lastFrame != null){
            this.remove(lastFrame);
        }
        this.add(this.currentFrame);
        this.revalidate();
    }
}
