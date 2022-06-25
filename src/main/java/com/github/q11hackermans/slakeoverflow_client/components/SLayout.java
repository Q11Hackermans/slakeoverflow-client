package com.github.q11hackermans.slakeoverflow_client.components;

import javax.swing.*;
import java.awt.*;

public class SLayout extends JPanel {


    private int gapX = 0;
    private int gapY = 0;
    private int columns = 0;
    private int rows = 1;

    public SLayout(int width, int height) {
        this.updateLayout();
        this.setBackground(Color.red);
        this.setPreferredSize(
                new Dimension(
                        width, height
                )
        );
    }


    public void setGapX(int gapX) {
        this.gapX = gapX;
        this.updateLayout();
    }

    public void setGapY(int gapY) {
        this.gapY = gapY;
        this.updateLayout();
    }

    public void addComponentToRow(Component c) {
        this.add(c);
        this.rows++;

        this.updateLayout();
    }


    public void addComponentToColumn(Component c) {
        this.add(c);
        this.columns++;

        this.updateLayout();
    }

    public void updateLayout() {
        this.setLayout(
                new GridLayout(
                        this.rows, this.columns, this.gapX, this.gapY
                )
        );
        this.repaint();
    }

}
