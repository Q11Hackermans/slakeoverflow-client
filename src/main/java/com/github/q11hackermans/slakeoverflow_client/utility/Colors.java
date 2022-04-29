//@blame Joshua3212

package com.github.q11hackermans.slakeoverflow_client.utility;

import java.awt.Color;

public class Colors {
    public static Color ground1 = Color.decode("#4aa832");
    public static Color ground2 = Color.decode("#356e26");
    
    public static Color playerBodyOwn = Color.decode("#4147e8");
    public static Color playerHeadOwn = Color.decode("#5459e3");
    
    public static Color playerBodyOther = Color.decode("#44455e");
    public static Color playerHeadOther = Color.decode("#545573");
    
    public static Color itemApple = Color.decode("#4147e8");
    
    
    //other
    public static Color black = Color.decode("#000");
    public static Color debug = Color.RED;

    public static Color getColorFromCode(int code) {
        switch (code) {
            // color codes for own player | 100-199
            case 101:
                return playerHeadOwn;
            case 102:
                return playerBodyOwn;
        

            // color codes for other player(s) | 200-299
            case 201:
                return playerHeadOther;
            case 202:
                return playerBodyOther;
        

            //items eg. apples | 300-399
            case 301:
                return playerHeadOther;
            
            
            }

        // if no vaild code was received
        return debug;
    }

}
