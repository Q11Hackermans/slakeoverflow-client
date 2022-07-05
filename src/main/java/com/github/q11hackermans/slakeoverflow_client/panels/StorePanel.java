package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;
import com.github.q11hackermans.slakeoverflow_client.model.ShopItem;
import com.github.q11hackermans.slakeoverflow_client.utility.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Objects;

public class StorePanel extends UnauthenticatedPanel {

    private String creatorCode;
    private int coinBalance;
    private int activeItem;
    private HashMap<Integer, ShopItem> items;

    private JPanel itemPanel;
    private JTextField creatorCodeTextField;
    private JButton backToLobbyBtn;
    private JLabel accountBalanceLabel;
    private JButton creatorCodeButton;
    private GameModel gameModel;
    private ActionListener actionListener;


    public StorePanel(ActionListener actionListener, GameModel gameModel) {
        this.gameModel = gameModel;
        this.actionListener = actionListener;
        this.getGameModelData();
        this.configureJPanel();
        this.configureJButtons();
        this.gameModel.requestUserInfo();
    }

    private void configureJButtons(){
        this.backToLobbyBtn.addActionListener(this.actionListener);
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

        accountBalanceLabel = new JLabel("Balance: " + this.coinBalance + " Coins");
        accountBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountBalanceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_accountBalanceLabel = new GridBagConstraints();
        gbc_accountBalanceLabel.anchor = GridBagConstraints.EAST;
        gbc_accountBalanceLabel.insets = new Insets(0, 0, 5, 5);
        gbc_accountBalanceLabel.gridx = 4;
        gbc_accountBalanceLabel.gridy = 1;
        add(accountBalanceLabel, gbc_accountBalanceLabel);

        JScrollPane itemsScrollPanel = new JScrollPane();
        itemsScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        itemsScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        GridBagConstraints gbc_itemsScrollPanel = new GridBagConstraints();
        gbc_itemsScrollPanel.gridwidth = 4;
        gbc_itemsScrollPanel.insets = new Insets(0, 0, 5, 5);
        gbc_itemsScrollPanel.fill = GridBagConstraints.BOTH;
        gbc_itemsScrollPanel.gridx = 1;
        gbc_itemsScrollPanel.gridy = 2;
        add(itemsScrollPanel, gbc_itemsScrollPanel);

        JPanel panel = new JPanel();
        this.itemPanel = panel;
        itemsScrollPanel.setViewportView(panel);
        panel.setLayout(new GridLayout(0, 1, 0, 0));

        this.configureItemPanel();

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

    private void configureItemPanel(){
        this.items.forEach((id, it) -> {
            JPanel panel_1 = new JPanel();
            this.itemPanel.add(panel_1);
            GridBagLayout gbl_panel_1 = new GridBagLayout();
            gbl_panel_1.columnWidths = new int[]{19, 408, 133, 450, 32, 45, 0};
            gbl_panel_1.rowHeights = new int[]{335, 0, 0};
            gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
            gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
            panel_1.setLayout(gbl_panel_1);

            JLabel lblNewLabel_1 = new JLabel(Assets.getSpriteFromCode(600, id));
            GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
            gbc_lblNewLabel_1.gridwidth = 4;
            gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
            gbc_lblNewLabel_1.gridx = 1;
            gbc_lblNewLabel_1.gridy = 0;
            panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

            JLabel lblNewLabel_0 = new JLabel("ID - " + it.getId());
            lblNewLabel_0.setFont(new Font("Tahoma", Font.BOLD, 10));
            GridBagConstraints gbc_lblNewLabel_0 = new GridBagConstraints();
            gbc_lblNewLabel_0.anchor = GridBagConstraints.WEST;
            gbc_lblNewLabel_0.insets = new Insets(0, 0, 0, 5);
            gbc_lblNewLabel_0.gridx = 1;
            gbc_lblNewLabel_0.gridy = 1;
            panel_1.add(lblNewLabel_0, gbc_lblNewLabel_0);

            JLabel lblNewLabel_2 = new JLabel("Item - Price: " + it.getPrice() + " Coins");
            lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
            GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
            gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
            gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
            gbc_lblNewLabel_2.gridx = 3;
            gbc_lblNewLabel_2.gridy = 1;
            panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);

            JButton btnNewButton_1 = new JButton();

            if (this.activeItem == id){
                btnNewButton_1.setText("Selected");
            } else if (it.getOwned()){
                btnNewButton_1.setText("Owned");
            } else {
                btnNewButton_1.setText("Buy");
            }
            btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

            btnNewButton_1.setActionCommand(it.getButtonActionCommand());
            btnNewButton_1.addActionListener(this.actionListener);

            GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
            gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
            gbc_btnNewButton_1.gridx = 4;
            gbc_btnNewButton_1.gridy = 1;
            panel_1.add(btnNewButton_1, gbc_btnNewButton_1);
        });
    }

    private void getGameModelData(){
        this.items = (HashMap<Integer, ShopItem>) this.gameModel.getItems();
        this.activeItem = this.gameModel.getActiveItem();
        this.coinBalance = this.gameModel.getCoinBalance();
    }

    public String getCreatorCode() {
        return creatorCode;
    }

    public int getCoinBalance() {
        return coinBalance;
    }

    public int getActiveItem() {
        return activeItem;
    }

    public HashMap<Integer, ShopItem> getItems() {
        return items;
    }

    @Override
    public boolean isUpToDate(Panel panel) {
        if (panel.getClass() == this.getClass()){
            return Objects.equals(((StorePanel) panel).getActiveItem(), this.gameModel.getActiveItem()) && Objects.equals(((StorePanel) panel).getCoinBalance(), this.gameModel.getCoinBalance()) && Objects.equals(((StorePanel) panel).getItems(), this.gameModel.getItems());
        }
        return false;
    }
}
