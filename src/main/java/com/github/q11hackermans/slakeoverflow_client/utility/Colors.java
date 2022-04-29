//@blame Joshua3212

package com.github.q11hackermans.slakeoverflow_client.utility;

import java.awt.Color;

public class Colors {
    public static Color ground1 = Color.decode("#4aa832");
    public static Color ground2 = Color.decode("#356e26");
    public static Color black = Color.decode("#000");
    
    public static Color playerBodyOwn = Color.decode("#4147e8");
    public static Color playerHeadOwn = Color.decode("#4147e8");
    public static Color playerTailOwn = Color.decode("#4147e8");

    // red basicially
    public static Color debug = Color.RED;

    public static Color getColorFromCode(int code) {
        switch (code) {
            case 1:
                return body;
        }
        return debug;
    }

}
