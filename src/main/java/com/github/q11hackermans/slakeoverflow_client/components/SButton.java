package com.github.q11hackermans.slakeoverflow_client.components;

import com.github.q11hackermans.slakeoverflow_client.utility.OldLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.github.q11hackermans.slakeoverflow_client.utility.SComponents.*;

public class SButton extends JButton {


    private final JButton self = this;

    public SButton(String content) {
        super(content);

        this.setFont(FONT);
        this.setForeground(BUTTON_COLOR);
        this.setBackground(BUTTON_BG);
        this.setFocusPainted(false);
        setBorderPainted(false);

        // set padding
        this.setBorder(
                BorderFactory.createMatteBorder(CTA_PADDING_Y, CTA_PADDING_X, CTA_PADDING_Y, CTA_PADDING_X, BUTTON_BG)
        );


        this.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseClicked(MouseEvent e) {
                OldLogger.debug("ehy");
                self.setBackground(BUTTON_ON_FOCUS);
                self.setBorder(
                        BorderFactory.createMatteBorder(CTA_PADDING_Y, CTA_PADDING_X, CTA_PADDING_Y, CTA_PADDING_X, BUTTON_ON_FOCUS)
                );
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                self.setBackground(BUTTON_ON_HOVER);
                self.setBorder(
                        BorderFactory.createMatteBorder(CTA_PADDING_Y, CTA_PADDING_X, CTA_PADDING_Y, CTA_PADDING_X, BUTTON_ON_HOVER)
                );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                self.setBackground(BUTTON_BG);
                self.setBorder(
                        BorderFactory.createMatteBorder(CTA_PADDING_Y, CTA_PADDING_X, CTA_PADDING_Y, CTA_PADDING_X, BUTTON_BG)
                );
            }
        });

    }

    public void setHeight(int height) {
        this.setPreferredSize(
                new Dimension(this.getWidth(), height)
        );
    }
    

    public void setWidth(int width) {
        this.setSize(
                width, getHeight()
        );
    }
}
