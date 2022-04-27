//@blame Joshua3212

package com.github.q11hackermans.slakeoverflow_client.utility;

import java.awt.Color;

public class Colors {
    public static Color green1 = Color.decode("#4aa832");
    public static Color green2 = Color.decode("#356e26");
    public static Color bg = Color.decode("#000");
    public static Color body = Color.decode("#4147e8");
    public static Color head = Color.decode("#4147e8");
    public static Color tail = Color.decode("#4147e8");

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
