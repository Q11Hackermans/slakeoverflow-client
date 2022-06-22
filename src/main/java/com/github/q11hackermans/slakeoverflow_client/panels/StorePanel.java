package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StorePanel extends UnauthenticatedPanel {

    private JTextField creatorCodeTextField;
    private JButton backToLobbyBtn;
    private JLabel accountBalanceLabel;
    private JButton creatorCodeButton;

    public StorePanel(ActionListener actionListener) {
        this.configureJPanel();
        this.configureJButtons(actionListener);
    }

    private void configureJButtons(ActionListener actionListener){
        this.backToLobbyBtn.addActionListener(actionListener);
        this.backToLobbyBtn.setActionCommand(ActionCommands.toLobbyButton);
    }

    private void configureJPanel() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{28, 84, 263, 372, 421, -114, 0};
        gridBagLayout.rowHeights = new int[]{19, 21, 747, -25, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        backToLobbyBtn = new JButton("Back to Lobby");
        backToLobbyBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_backToLobbyBtn = new GridBagConstraints();
        gbc_backToLobbyBtn.gridwidth = 3;
        gbc_backToLobbyBtn.gridheight = 2;
        gbc_backToLobbyBtn.insets = new Insets(0, 0, 5, 5);
        gbc_backToLobbyBtn.anchor = GridBagConstraints.SOUTHWEST;
        gbc_backToLobbyBtn.gridx = 1;
        gbc_backToLobbyBtn.gridy = 0;
        add(backToLobbyBtn, gbc_backToLobbyBtn);

        accountBalanceLabel = new JLabel("Balance: 500 Coins");
        accountBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountBalanceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_accountBalanceLabel = new GridBagConstraints();
        gbc_accountBalanceLabel.anchor = GridBagConstraints.EAST;
        gbc_accountBalanceLabel.insets = new Insets(0, 0, 5, 5);
        gbc_accountBalanceLabel.gridx = 4;
        gbc_accountBalanceLabel.gridy = 1;
        add(accountBalanceLabel, gbc_accountBalanceLabel);

        JScrollPane itemsScrollPanel = new JScrollPane();
        itemsScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        GridBagConstraints gbc_itemsScrollPanel = new GridBagConstraints();
        gbc_itemsScrollPanel.gridwidth = 4;
        gbc_itemsScrollPanel.insets = new Insets(0, 0, 5, 5);
        gbc_itemsScrollPanel.fill = GridBagConstraints.BOTH;
        gbc_itemsScrollPanel.gridx = 1;
        gbc_itemsScrollPanel.gridy = 2;
        add(itemsScrollPanel, gbc_itemsScrollPanel);

        JPanel panel = new JPanel();
        itemsScrollPanel.setViewportView(panel);
        panel.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel panel_1 = new JPanel();
        panel.add(panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{19, 408, 133, 450, 32, 45, 0};
        gbl_panel_1.rowHeights = new int[]{335, 0, 0};
        gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);

        JLabel lblNewLabel_1 = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHWEST;
        gbc_lblNewLabel_1.gridwidth = 4;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 0;
        panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Default - Price: 500 Coins");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 3;
        gbc_lblNewLabel_2.gridy = 1;
        panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);

        JButton btnNewButton_2 = new JButton("Selected");
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_2.gridx = 4;
        gbc_btnNewButton_2.gridy = 1;
        panel_1.add(btnNewButton_2, gbc_btnNewButton_2);

        JPanel panel_2 = new JPanel();
        panel.add(panel_2);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{19, 408, 133, 450, 32, 45, 0};
        gbl_panel_2.rowHeights = new int[]{335, 0, 0};
        gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);

        JLabel lblNewLabel_1_1 = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1.anchor = GridBagConstraints.NORTHWEST;
        gbc_lblNewLabel_1_1.gridwidth = 4;
        gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1.gridx = 1;
        gbc_lblNewLabel_1_1.gridy = 0;
        panel_2.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);

        JLabel lblNewLabel_2_1 = new JLabel("Pro - Price: 5000 Coins");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
        gbc_lblNewLabel_2_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2_1.gridx = 3;
        gbc_lblNewLabel_2_1.gridy = 1;
        panel_2.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);

        JButton btnNewButton_2_1 = new JButton("Buy");
        btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_btnNewButton_2_1 = new GridBagConstraints();
        gbc_btnNewButton_2_1.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_2_1.gridx = 4;
        gbc_btnNewButton_2_1.gridy = 1;
        panel_2.add(btnNewButton_2_1, gbc_btnNewButton_2_1);

        JPanel panel_3 = new JPanel();
        panel.add(panel_3);
        GridBagLayout gbl_panel_3 = new GridBagLayout();
        gbl_panel_3.columnWidths = new int[]{19, 408, 133, 450, 32, 45, 0};
        gbl_panel_3.rowHeights = new int[]{335, 0, 0};
        gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_3.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        panel_3.setLayout(gbl_panel_3);

        JLabel lblNewLabel_1_2 = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
        gbc_lblNewLabel_1_2.anchor = GridBagConstraints.NORTHWEST;
        gbc_lblNewLabel_1_2.gridwidth = 4;
        gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_2.gridx = 1;
        gbc_lblNewLabel_1_2.gridy = 0;
        panel_3.add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);

        JLabel lblNewLabel_2_2 = new JLabel("ULTRA - Price: 50000 Coins");
        lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblNewLabel_2_2 = new GridBagConstraints();
        gbc_lblNewLabel_2_2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2_2.gridx = 3;
        gbc_lblNewLabel_2_2.gridy = 1;
        panel_3.add(lblNewLabel_2_2, gbc_lblNewLabel_2_2);

        JButton btnNewButton_2_2 = new JButton("Buy");
        btnNewButton_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_btnNewButton_2_2 = new GridBagConstraints();
        gbc_btnNewButton_2_2.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_2_2.gridx = 4;
        gbc_btnNewButton_2_2.gridy = 1;
        panel_3.add(btnNewButton_2_2, gbc_btnNewButton_2_2);

        JLabel creatorCode = new JLabel("Creatorcode:");
        creatorCode.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_creatorCode = new GridBagConstraints();
        gbc_creatorCode.insets = new Insets(0, 0, 0, 5);
        gbc_creatorCode.anchor = GridBagConstraints.EAST;
        gbc_creatorCode.gridx = 2;
        gbc_creatorCode.gridy = 3;
        add(creatorCode, gbc_creatorCode);

        creatorCodeTextField = new JTextField();
        creatorCodeTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        creatorCodeTextField.setText("D3rHase");
        GridBagConstraints gbc_creatorCodeTextField = new GridBagConstraints();
        gbc_creatorCodeTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_creatorCodeTextField.insets = new Insets(0, 0, 0, 5);
        gbc_creatorCodeTextField.gridx = 3;
        gbc_creatorCodeTextField.gridy = 3;
        add(creatorCodeTextField, gbc_creatorCodeTextField);
        creatorCodeTextField.setColumns(10);

        creatorCodeButton = new JButton("Choose");
        creatorCodeButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_creatorCodeButton = new GridBagConstraints();
        gbc_creatorCodeButton.anchor = GridBagConstraints.WEST;
        gbc_creatorCodeButton.insets = new Insets(0, 0, 0, 5);
        gbc_creatorCodeButton.gridx = 4;
        gbc_creatorCodeButton.gridy = 3;
        add(creatorCodeButton, gbc_creatorCodeButton);

    }

    @Override
    public boolean isUpToDate(Panel panel) {
        return false;
    }
}
