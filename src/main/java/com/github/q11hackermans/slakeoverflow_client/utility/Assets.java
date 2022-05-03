//@blame Joshua3212

package com.github.q11hackermans.slakeoverflow_client.utility;

import javax.swing.*;

public class Assets {

        public static final ImageIcon PLAYER_HEAD_OWN = new ImageIcon("assets/player_head_own.png");
        public static final ImageIcon PLAYER_BODY_OWN = new ImageIcon("assets/player_body_own.png");
        //public static final ImageIcon PLAYER_TAIL = new ImageIcon("assets/player_head.png");

        public static final ImageIcon PLAYER_HEAD_OTHER = new ImageIcon("assets/player_head_other.png");
        public static final ImageIcon PLAYER_BODY_OTHER = new ImageIcon("assets/player_body_other.png");
        
        public static final ImageIcon ITEM_APPLE = new ImageIcon("assets/item_apple.png");




    public static ImageIcon getSpriteFromCode(int code){
        switch (code) {
            // color codes for own player | 100-199
            case 101:
                return PLAYER_HEAD_OWN;
            case 102:
                return PLAYER_BODY_OWN;

            // color codes for other player(s) | 200-299
            case 201:
                return PLAYER_HEAD_OTHER;
            case 202:
                return PLAYER_BODY_OTHER;

            // items eg. apples | 300-399
            case 301:
                return ITEM_APPLE;

        }
        return null;
    }
}
