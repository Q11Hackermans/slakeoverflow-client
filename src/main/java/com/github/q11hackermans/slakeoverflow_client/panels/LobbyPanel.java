package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LobbyPanel extends UnauthenticatedPanel {

    private JButton playerMode;
    private JButton spectatorMode;
    private JButton disconnect;
    private JButton logInOutButton;
    private JButton shopPanelButton;
    private JButton sendChatMessageBtn;
    private JLabel usernameLabel;
    private JLabel balanceLabel;
    private JPanel chatMsgPanel;
    private JTextField updatePwdField;
    private JTextField updatePwdConfirmField;
    private JTextField chatField;
    private JMenuBar settingsBar;
    private GameModel gameModel;

    private String usernameLabelText;
    private String coinBalanceLabelText;
    private boolean loginButtonVisible;

    public LobbyPanel(ActionListener actionListener, GameModel gameModel) {

        this.gameModel = gameModel;
        this.gameModel.requestUserInfo();
        this.getGameModelData();

        this.configureJPanel();
        this.configureJElements(actionListener);
    }

    private void configureJElements(ActionListener actionListener) {

        this.playerMode.addActionListener(actionListener);
        this.playerMode.setActionCommand(ActionCommands.playButtonPressed);

        this.spectatorMode.addActionListener(actionListener);
        this.spectatorMode.setActionCommand(ActionCommands.spectatorButtonPressed);

        this.disconnect.addActionListener(actionListener);
        this.disconnect.setActionCommand(ActionCommands.disconnectButtonPressed);

        this.logInOutButton.addActionListener(actionListener);
        this.shopPanelButton.addActionListener(actionListener);

        if (this.loginButtonVisible){
            this.logInOutButton.setText("Logout");
            this.logInOutButton.setActionCommand(ActionCommands.logoutButton);

            this.usernameLabel.setVisible(true);
            this.balanceLabel.setVisible(true);

            this.shopPanelButton.setActionCommand(ActionCommands.toStoreViewButton);
            this.shopPanelButton.setVisible(true);

            this.settingsBar.setVisible(true);
        } else {
            this.logInOutButton.setText("Login");
            this.logInOutButton.setActionCommand(ActionCommands.toLoginViewButton);

            this.usernameLabel.setVisible(false);
            this.balanceLabel.setVisible(false);
            this.shopPanelButton.setVisible(false);
            this.settingsBar.setVisible(false);
        }
    }

    public boolean isLoginButtonVisible() {
        return loginButtonVisible;
    }

    private void configureJPanel() {

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{171, 42, 12, 69, 10, 22, 15, 0};
        gridBagLayout.rowHeights = new int[]{16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -166, 221, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        disconnect = new JButton("Disconnect");
        disconnect.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_btnDisconnect = new GridBagConstraints();
        gbc_btnDisconnect.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnDisconnect.insets = new Insets(0, 0, 5, 5);
        gbc_btnDisconnect.gridx = 0;
        gbc_btnDisconnect.gridy = 0;
        add(disconnect, gbc_btnDisconnect);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.VERTICAL;
        gbc_panel.anchor = GridBagConstraints.EAST;
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.gridx = 5;
        gbc_panel.gridy = 0;
        add(panel, gbc_panel);

        settingsBar = new JMenuBar();
        settingsBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.add(settingsBar);

        JMenu mnNewMenu = new JMenu("Account");
        mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
        mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        settingsBar.add(mnNewMenu);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        mnNewMenu.add(panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{98, 98, 0};
        gbl_panel_1.rowHeights = new int[]{21, 21, 0, 0, 0};
        gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);

        JLabel lblNewLabel = new JLabel("Update password:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 2;
        gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panel_1.add(lblNewLabel, gbc_lblNewLabel);

        updatePwdField = new JTextField();
        updatePwdField.setText("new password");
        GridBagConstraints gbc_txtNewPassword = new GridBagConstraints();
        gbc_txtNewPassword.gridwidth = 2;
        gbc_txtNewPassword.insets = new Insets(0, 0, 5, 0);
        gbc_txtNewPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNewPassword.gridx = 0;
        gbc_txtNewPassword.gridy = 1;
        panel_1.add(updatePwdField, gbc_txtNewPassword);
        updatePwdField.setColumns(10);

        JButton btnNewButton_1 = new JButton("Update");
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton_1.gridx = 0;
        gbc_btnNewButton_1.gridy = 3;
        panel_1.add(btnNewButton_1, gbc_btnNewButton_1);

        updatePwdConfirmField = new JTextField();
        updatePwdConfirmField.setText("confirm password");
        GridBagConstraints gbc_txtNewPassword_1 = new GridBagConstraints();
        gbc_txtNewPassword_1.gridwidth = 2;
        gbc_txtNewPassword_1.insets = new Insets(0, 0, 5, 0);
        gbc_txtNewPassword_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNewPassword_1.gridx = 0;
        gbc_txtNewPassword_1.gridy = 2;
        panel_1.add(updatePwdConfirmField, gbc_txtNewPassword_1);
        updatePwdConfirmField.setColumns(10);

        JCheckBoxMenuItem chckbxmntmNewCheckItem_1 = new JCheckBoxMenuItem("Enable Chat");
        chckbxmntmNewCheckItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mnNewMenu.add(chckbxmntmNewCheckItem_1);

        JMenu mnNewMenu_1 = new JMenu("General");
        mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        settingsBar.add(mnNewMenu_1);

        JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Fixed FOV");
        chckbxmntmNewCheckItem.setHorizontalAlignment(SwingConstants.RIGHT);
        chckbxmntmNewCheckItem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mnNewMenu_1.add(chckbxmntmNewCheckItem);

        logInOutButton = new JButton("Login");
        logInOutButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
        gbc_btnLogin.gridx = 6;
        gbc_btnLogin.gridy = 0;
        add(logInOutButton, gbc_btnLogin);

        JPanel panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.gridwidth = 2;
        gbc_panel_2.gridheight = 2;
        gbc_panel_2.insets = new Insets(0, 0, 5, 0);
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 5;
        gbc_panel_2.gridy = 1;
        add(panel_2, gbc_panel_2);
        panel_2.setLayout(new GridLayout(0, 1, 0, 0));

        usernameLabel = new JLabel(this.usernameLabelText);
        usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_2.add(usernameLabel);

        balanceLabel = new JLabel(this.coinBalanceLabelText);
        balanceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_2.add(balanceLabel);

        JPanel panel_3 = new JPanel();
        GridBagConstraints gbc_panel_3 = new GridBagConstraints();
        gbc_panel_3.gridheight = 10;
        gbc_panel_3.gridwidth = 5;
        gbc_panel_3.insets = new Insets(0, 0, 5, 5);
        gbc_panel_3.fill = GridBagConstraints.BOTH;
        gbc_panel_3.gridx = 1;
        gbc_panel_3.gridy = 3;
        add(panel_3, gbc_panel_3);
        panel_3.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblNewLabel_3 = new JLabel("Irgendwas?");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        panel_3.add(lblNewLabel_3);

        playerMode = new JButton("Join as Player");
        playerMode.setFont(new Font("Tahoma", Font.BOLD, 16));
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 14;
        add(playerMode, gbc_btnNewButton);

        shopPanelButton = new JButton("Shop");
        shopPanelButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        GridBagConstraints gbc_btnShop = new GridBagConstraints();
        gbc_btnShop.fill = GridBagConstraints.BOTH;
        gbc_btnShop.insets = new Insets(0, 0, 5, 5);
        gbc_btnShop.gridx = 3;
        gbc_btnShop.gridy = 14;
        add(shopPanelButton, gbc_btnShop);

        spectatorMode = new JButton("Join as Spectator");
        spectatorMode.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_btnJoinAsSpectator = new GridBagConstraints();
        gbc_btnJoinAsSpectator.fill = GridBagConstraints.BOTH;
        gbc_btnJoinAsSpectator.insets = new Insets(0, 0, 5, 5);
        gbc_btnJoinAsSpectator.gridx = 5;
        gbc_btnJoinAsSpectator.gridy = 14;
        add(spectatorMode, gbc_btnJoinAsSpectator);

        chatMsgPanel = new JPanel();
        chatMsgPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        GridBagConstraints gbc_panel_4 = new GridBagConstraints();
        gbc_panel_4.gridwidth = 5;
        gbc_panel_4.insets = new Insets(0, 0, 5, 5);
        gbc_panel_4.fill = GridBagConstraints.BOTH;
        gbc_panel_4.gridx = 1;
        gbc_panel_4.gridy = 15;
        add(chatMsgPanel, gbc_panel_4);

        chatField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.gridwidth = 5;
        gbc_textField.insets = new Insets(0, 0, 0, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 16;
        add(chatField, gbc_textField);
        chatField.setColumns(10);

        sendChatMessageBtn = new JButton("Send Message");
        sendChatMessageBtn.setHorizontalAlignment(SwingConstants.TRAILING);
        GridBagConstraints gbc_btnNewButton1 = new GridBagConstraints();
        gbc_btnNewButton1.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton1.gridx = 6;
        gbc_btnNewButton1.gridy = 16;
        add(sendChatMessageBtn, gbc_btnNewButton1);

    }

    private void getGameModelData() {
        this.coinBalanceLabelText = ("Balance: " + this.gameModel.getCoinBalance() + " Coins");
        this.usernameLabelText = ("Logged in as: " + this.gameModel.getUsername());
        this.loginButtonVisible = this.gameModel.isLoggedIn();
    }

    private String username(){
        return this.usernameLabelText.split("Logged in as: ")[1];
    }

    private String coinBalance(){
        return this.coinBalanceLabelText.split("Balance: ")[1].split(" ")[0];
    }

    @Override
    public boolean isUpToDate(Panel panel) {
        if (panel.getClass() == this.getClass()){
            return Objects.equals(((LobbyPanel) panel).username(), this.gameModel.getUsername()) && Objects.equals(((LobbyPanel) panel).coinBalance(), Integer.toString(this.gameModel.getCoinBalance())) && Objects.equals(((LobbyPanel) panel).isLoginButtonVisible(), this.gameModel.isLoggedIn());
        }
        return false;
    }
}
