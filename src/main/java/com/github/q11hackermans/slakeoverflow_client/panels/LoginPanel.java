package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.listeners.LoginPanelListener;
import com.github.q11hackermans.slakeoverflow_client.utility.Logger;

import javax.swing.*;
import java.awt.* ;
import java.awt.event.* ;

public class LoginPanel extends JPanel {

    private int port=0;
    private String host = null;

    private LoginPanelListener loginPanelListener;

    public LoginPanel() {
        this.setBackground(ground1);

        // JButton mit Text "Drück mich" wird erstellt
        JButton button = new JButton("Play");

        // JButton wird dem Panel hinzugefügt
        panel.add(button);

        this.button.setBounds(50,100,100,30)

        meinJFrame.setVisible(true);
    }


    public LoginPanelListener getLoginPanelListener() {
        return loginPanelListener;
    }

    public void setLoginPanelListener(LoginPanelListener loginPanelListener) {
        this.loginPanelListener = loginPanelListener;
    }

    public void login(){
        if (host != null && port != 0){
            this.loginPanelListener.onLogin(host, port);
        }else{
            Logger.error("host and/or port have to be specified");
        }
    }
}