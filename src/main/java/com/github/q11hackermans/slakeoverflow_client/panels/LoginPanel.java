package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.utility.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.utility.Colors;
import com.github.q11hackermans.slakeoverflow_client.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends View {
    private final JButton button;
    private final JTextField host;
    private final JTextField port;


    public LoginPanel(ActionListener actionListener) {

        this.setBackground(Colors.GROUND_1);

        // JButton mit Text "Drück mich" wird erstellt

        button = new JButton("Play");
        button.setAlignmentX(0);
        host = new JTextField();
        port = new JTextField();


        port.setText("26677");
        host.setText("127.0.0.1");

        button.addActionListener(actionListener);
        button.setActionCommand(ActionCommands.connectButtonPressed);

        this.add(new JLabel("Host"));
        this.add(host);


        this.add(new JLabel("Port"));
        this.add(port);
        this.add(button);
        this.configureJPanel();

    }

    public void configureJPanel() {
        this.setVisible(true);

        this.setPreferredSize(
                new Dimension(300, 700)
        );
        this.setLayout(new GridLayout(10, 1, 0, 10));
    }


    /**
     * Get the value of the Login-View host text-field
     *
     * @return String - host
     */
    public String getHost() {
        return host.getText();
    }

    /**
     * Get the value of the Login-View port text-field
     *
     * @return String - port
     */
    public String getPort() {
        return port.getText();
    }
}
