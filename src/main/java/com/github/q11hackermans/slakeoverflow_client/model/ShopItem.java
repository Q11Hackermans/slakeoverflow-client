package com.github.q11hackermans.slakeoverflow_client.model;

import java.util.Objects;

public class ShopItem {

    private int price;
    private int id;
    private boolean owned;
    private String buttonActionCommand;

    public ShopItem(int price, int id, boolean owned){
        this.setOwned(owned);
        this.price = price;
        this.id = id;
    }

    public ShopItem(int price, int id){
        this.price = price;
        this.id = id;
        this.setOwned(false);
    }

    @Override
    public String toString() {
        return "ShopItem{" +
                "price=" + price +
                ", id=" + id +
                ", owned=" + owned +
                ", buttonActionCommand='" + buttonActionCommand + '\'' +
                '}';
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
        if (this.owned){
            this.buttonActionCommand = ("shopItemButton-" + this.id + "-1");
        } else {
            this.buttonActionCommand = ("shopItemButton-" + this.id + "-0");
        }
    }

    public String getButtonActionCommand() {
        return buttonActionCommand;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopItem shopItem = (ShopItem) o;
        return price == shopItem.price && id == shopItem.id && owned == shopItem.owned && Objects.equals(buttonActionCommand, shopItem.buttonActionCommand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, id, owned, buttonActionCommand);
    }
}
