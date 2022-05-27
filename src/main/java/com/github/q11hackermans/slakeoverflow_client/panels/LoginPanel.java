package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends View {

    private JButton loginButton;
    private JButton registerButton;
    private JButton backToLobbyButton;

    private JFormattedTextField usernameField;
    private JPasswordField passwordField;


    public LoginPanel(ActionListener actionListener) {
        this.configureJPanel();
        this.configureButtons(actionListener);
    }

    private void configureButtons(ActionListener actionListener){
        this.backToLobbyButton.addActionListener(actionListener);
        this.backToLobbyButton.setActionCommand(ActionCommands.backToLobbyFromLoginButton);

        this.loginButton.addActionListener(actionListener);
        this.loginButton.setActionCommand(ActionCommands.loginButton);

        this.registerButton.addActionListener(actionListener);
        this.registerButton.setActionCommand(ActionCommands.registerButton);
    }

    public void configureJPanel() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {158, 173, 101, 56, 77, 53, 292, 3, 202};
        gridBagLayout.rowHeights = new int[]{17, 94, 412, 12, 36, 10, 33, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel welcomeMsgLabel = new JLabel("Hi, welcome at Slakomaina");
        welcomeMsgLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        GridBagConstraints gbc_welcomeMsgLabel = new GridBagConstraints();
        gbc_welcomeMsgLabel.gridwidth = 3;
        gbc_welcomeMsgLabel.anchor = GridBagConstraints.WEST;
        gbc_welcomeMsgLabel.insets = new Insets(0, 0, 5, 5);
        gbc_welcomeMsgLabel.gridx = 2;
        gbc_welcomeMsgLabel.gridy = 1;
        add(welcomeMsgLabel, gbc_welcomeMsgLabel);

        JLabel loginMsgLabel = new JLabel("Please log in or sign up");
        loginMsgLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        GridBagConstraints gbc_loginMsgLabel = new GridBagConstraints();
        gbc_loginMsgLabel.gridwidth = 2;
        gbc_loginMsgLabel.anchor = GridBagConstraints.WEST;
        gbc_loginMsgLabel.insets = new Insets(0, 0, 5, 5);
        gbc_loginMsgLabel.gridx = 5;
        gbc_loginMsgLabel.gridy = 1;
        add(loginMsgLabel, gbc_loginMsgLabel);

        JLabel slakeGifLabel = new JLabel("New label");
        GridBagConstraints gbc_slakeGifLabel = new GridBagConstraints();
        gbc_slakeGifLabel.gridheight = 2;
        gbc_slakeGifLabel.gridwidth = 6;
        gbc_slakeGifLabel.insets = new Insets(0, 0, 5, 5);
        gbc_slakeGifLabel.gridx = 1;
        gbc_slakeGifLabel.gridy = 2;
        add(slakeGifLabel, gbc_slakeGifLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
        GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
        gbc_usernameLabel.anchor = GridBagConstraints.SOUTHWEST;
        gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_usernameLabel.gridx = 1;
        gbc_usernameLabel.gridy = 4;
        add(usernameLabel, gbc_usernameLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
        GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
        gbc_passwordLabel.anchor = GridBagConstraints.SOUTHWEST;
        gbc_passwordLabel.gridwidth = 2;
        gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
        gbc_passwordLabel.gridx = 5;
        gbc_passwordLabel.gridy = 4;
        add(passwordLabel, gbc_passwordLabel);

        backToLobbyButton = new JButton("Back to Lobby");
        backToLobbyButton.setMnemonic('b');
        GridBagConstraints gbc_playAsGuestButton = new GridBagConstraints();
        gbc_playAsGuestButton.anchor = GridBagConstraints.NORTHWEST;
        gbc_playAsGuestButton.insets = new Insets(0, 0, 0, 5);
        gbc_playAsGuestButton.gridx = 4;
        gbc_playAsGuestButton.gridy = 6;
        add(backToLobbyButton, gbc_playAsGuestButton);

        registerButton = new JButton("Register");
        registerButton.setMnemonic('r');
        GridBagConstraints gbc_registerButton = new GridBagConstraints();
        gbc_registerButton.insets = new Insets(0, 0, 0, 5);
        gbc_registerButton.anchor = GridBagConstraints.NORTHEAST;
        gbc_registerButton.gridx = 5;
        gbc_registerButton.gridy = 6;
        add(registerButton, gbc_registerButton);

        usernameField = new JFormattedTextField();
        usernameField.setToolTipText("username");
        GridBagConstraints gbc_usernameField = new GridBagConstraints();
        gbc_usernameField.gridheight = 2;
        gbc_usernameField.gridwidth = 4;
        gbc_usernameField.anchor = GridBagConstraints.LAST_LINE_END;
        gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
        gbc_usernameField.insets = new Insets(0, 0, 5, 5);
        gbc_usernameField.gridx = 1;
        gbc_usernameField.gridy = 4;
        add(usernameField, gbc_usernameField);

        passwordField = new JPasswordField();
        passwordField.setToolTipText("password");
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.gridwidth = 3;
        gbc_passwordField.insets = new Insets(0, 0, 5, 5);
        gbc_passwordField.anchor = GridBagConstraints.LAST_LINE_END;
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.gridx = 5;
        gbc_passwordField.gridy = 5;
        add(passwordField, gbc_passwordField);

        loginButton = new JButton("Log in");
        loginButton.setMnemonic('l');
        GridBagConstraints gbc_loginButton = new GridBagConstraints();
        gbc_loginButton.gridwidth = 2;
        gbc_loginButton.insets = new Insets(0, 0, 0, 5);
        gbc_loginButton.anchor = GridBagConstraints.NORTHWEST;
        gbc_loginButton.gridx = 6;
        gbc_loginButton.gridy = 6;
        add(loginButton, gbc_loginButton);
    }
}
