//@blame Joshua3212

package com.github.q11hackermans.slakeoverflow_client.utility;

import javax.swing.*;

public abstract class Assets {

    /// SPRITES

    // MAP
    public static final ImageIcon MAP1 = new ImageIcon("assets/map.png");
    public static final ImageIcon MAP2 = new ImageIcon("assets/map2.png");
    public static final ImageIcon BORDER = new ImageIcon("assets/border.png");

    // OWN PLAYER
    public static final ImageIcon PLAYER_HEAD_OWN_NORTH = new ImageIcon("assets/player_head_own_north.png");
    public static final ImageIcon PLAYER_HEAD_OWN_SOUTH = new ImageIcon("assets/player_head_own_south.png");
    public static final ImageIcon PLAYER_HEAD_OWN_EAST = new ImageIcon("assets/player_head_own_east.png");
    public static final ImageIcon PLAYER_HEAD_OWN_WEST = new ImageIcon("assets/player_head_own_west.png");
    public static final ImageIcon PLAYER_BODY_OWN = new ImageIcon("assets/player_body_own.png");

    // OTHER PLAYERS
    public static final ImageIcon PLAYER_HEAD_OTHER_NORTH = new ImageIcon("assets/player_head_other_north.png");
    public static final ImageIcon PLAYER_HEAD_OTHER_SOUTH = new ImageIcon("assets/player_head_other_south.png");
    public static final ImageIcon PLAYER_HEAD_OTHER_EAST = new ImageIcon("assets/player_head_other_east.png");
    public static final ImageIcon PLAYER_HEAD_OTHER_WEST = new ImageIcon("assets/player_head_other_west.png");
    public static final ImageIcon PLAYER_BODY_OTHER = new ImageIcon("assets/player_body_enemy.png");

    // ITEMS
    public static final ImageIcon ITEM_UNKNOWN = new ImageIcon("assets/item_food.png");
    public static final ImageIcon ITEM_FOOD = new ImageIcon("assets/item_food.png");
    public static final ImageIcon ITEM_SUPER_FOOD = new ImageIcon("assets/item_super_food.png");


    /// ID's

    // MAP
    public static final int EMPTY_ID = 0;
    public static final int BORDER_ID = 1;

    // OWN PLAYER
    public static final int PLAYER_HEAD_OWN_NORTH_ID = 101;
    public static final int PLAYER_HEAD_OWN_SOUTH_ID = 103;
    public static final int PLAYER_HEAD_OWN_EAST_ID = 102;
    public static final int PLAYER_HEAD_OWN_WEST_ID = 104;
    public static final int PLAYER_BODY_OWN_ID = 105;

    // OTHER PLAYERS
    public static final int PLAYER_HEAD_OTHER_NORTH_ID = 201;
    public static final int PLAYER_HEAD_OTHER_SOUTH_ID = 203;
    public static final int PLAYER_HEAD_OTHER_EAST_ID = 202;
    public static final int PLAYER_HEAD_OTHER_WEST_ID = 204;
    public static final int PLAYER_BODY_OTHER_ID = 205;

    // ITEMS
    public static final int ITEM_UNKNOWN_ID = 501; // If it is an unknown item
    public static final int ITEM_FOOD_ID = 502; // The default food item
    public static final int ITEM_SUPER_FOOD_ID = 503; // This item will be dropped when a player dies


    public static ImageIcon getSpriteFromCode(int code) {

        switch (code) {

            // MAP
            case BORDER_ID:
                return BORDER;

            // OWN PLAYER
            case PLAYER_HEAD_OWN_NORTH_ID:
                return PLAYER_HEAD_OWN_NORTH;
            case PLAYER_HEAD_OWN_SOUTH_ID:
                return PLAYER_HEAD_OWN_SOUTH;
            case PLAYER_HEAD_OWN_EAST_ID:
                return PLAYER_HEAD_OWN_EAST;
            case PLAYER_HEAD_OWN_WEST_ID:
                return PLAYER_HEAD_OWN_WEST;
            case PLAYER_BODY_OWN_ID:
                return PLAYER_BODY_OWN;

            // OTHER PLAYERS
            case PLAYER_HEAD_OTHER_NORTH_ID:
                return PLAYER_HEAD_OTHER_NORTH;
            case PLAYER_HEAD_OTHER_SOUTH_ID:
                return PLAYER_HEAD_OTHER_SOUTH;
            case PLAYER_HEAD_OTHER_EAST_ID:
                return PLAYER_HEAD_OTHER_EAST;
            case PLAYER_HEAD_OTHER_WEST_ID:
                return PLAYER_HEAD_OTHER_WEST;
            case PLAYER_BODY_OTHER_ID:
                return PLAYER_BODY_OTHER;

            // ITEMS
            case ITEM_UNKNOWN_ID:
                return ITEM_UNKNOWN;
            case ITEM_FOOD_ID:
                return ITEM_FOOD;
            case ITEM_SUPER_FOOD_ID:
                return ITEM_SUPER_FOOD;

            default:
                return null;
        }
    }
}
