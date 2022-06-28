package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.utility.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartPanel extends Panel {
    private final JButton button;
    private final JTextField host;
    private final JTextField port;


    public StartPanel(ActionListener actionListener) {

        this.setBackground(Colors.GROUND_1);

        button = new JButton("Play");
        button.setAlignmentX(0);
        host = new JTextField();
        port = new JTextField();


        port.setText("26677");
        host.setText("127.0.0.1");

        button.addActionListener(actionListener);
        button.setActionCommand(ActionCommands.connectButtonPressed);

        JLabel slakeLabel = new JLabel("SlakeOverflow");
        slakeLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        this.add(slakeLabel);

        JLabel hostLabel = new JLabel("Host:");
        hostLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
        this.add(hostLabel);
        this.add(host);

        JLabel portLabel = new JLabel("Port:");
        portLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
        this.add(portLabel);
        this.add(port);
        this.add(button);
        this.configureJPanel();

    }

    private void configureJPanel() {
        this.setVisible(true);

        this.setPreferredSize(
                new Dimension(300, 700)
        );
        this.setLayout(new GridLayout(10, 1, 0, 10));
    }


    /**
     * Get the value of the Start-Panel host text-field
     *
     * @return String - host
     */
    public String getHost() {
        return host.getText();
    }

    /**
     * Get the value of the Start-Panel port text-field
     *
     * @return String - port
     */
    public String getPort() {
        return port.getText();
    }

    @Override
    public boolean isUpToDate(Panel panel) {
        return true;
    }
}
