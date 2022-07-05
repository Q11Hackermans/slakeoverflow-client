//@blame Joshua3212

package com.github.q11hackermans.slakeoverflow_client.utility;

import javax.swing.*;

public abstract class Assets {

    // MAP
    public static final int BORDER_ID = 1;

    // OWN PLAYER
    public static final int PLAYER_HEAD_OWN_NORTH_ID = 101;
    public static final int PLAYER_HEAD_OWN_SOUTH_ID = 103;
    public static final int PLAYER_HEAD_OWN_EAST_ID = 102;
    public static final int PLAYER_HEAD_OWN_WEST_ID = 104;
    public static final int PLAYER_BODY_OWN_ID = 105;

    // OTHER PLAYERS
    public static final int PLAYER_HEAD_enemy_NORTH_ID = 201;
    public static final int PLAYER_HEAD_enemy_SOUTH_ID = 203;
    public static final int PLAYER_HEAD_enemy_EAST_ID = 202;
    public static final int PLAYER_HEAD_enemy_WEST_ID = 204;
    public static final int PLAYER_BODY_enemy_ID = 205;

    // ITEMS
    public static final int ITEM_UNKNOWN_ID = 501; // If it is an unknown item
    public static final int ITEM_FOOD_ID = 502; // The default food item
    public static final int ITEM_SUPER_FOOD_ID = 503; // This item will be dropped when a player dies

    // STORE
    public static final int STORE_PREVIEW_ID  = 600;



    public static ImageIcon getMap(int skinPack) {
        return new ImageIcon("assets/skins/" + skinPack + "/map.png");
    }


    public static ImageIcon getSpriteFromCode(int code, int skinPack) {

        switch (code) {


            // BORDER
            case BORDER_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/border.png");

            // OWN PLAYER
            case PLAYER_HEAD_OWN_NORTH_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/player_head_own_north.png");
            case PLAYER_HEAD_OWN_SOUTH_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/player_head_own_south.png");
            case PLAYER_HEAD_OWN_EAST_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/player_head_own_east.png");
            case PLAYER_HEAD_OWN_WEST_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/player_head_own_west.png");
            case PLAYER_BODY_OWN_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/player_body_own.png");


            // OTHER PLAYER
            case PLAYER_HEAD_enemy_NORTH_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/player_head_enemy_north.png");
            case PLAYER_HEAD_enemy_SOUTH_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/player_head_enemy_south.png");
            case PLAYER_HEAD_enemy_EAST_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/player_head_enemy_east.png");
            case PLAYER_HEAD_enemy_WEST_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/player_head_enemy_west.png");
            case PLAYER_BODY_enemy_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/player_body_enemy.png");

            // ITEMS
            case ITEM_UNKNOWN_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/border.png");
            case ITEM_FOOD_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/item_food.png");
            case ITEM_SUPER_FOOD_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/item_superfood.png");

                // PREVIEW

            case STORE_PREVIEW_ID:
                return new ImageIcon("assets/skins/" + skinPack + "/preview.png");

            default:
                return null;
        }
    }
}
