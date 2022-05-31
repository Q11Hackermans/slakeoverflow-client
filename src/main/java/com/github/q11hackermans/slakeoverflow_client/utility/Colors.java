//@blame Joshua3212

package com.github.q11hackermans.slakeoverflow_client.utility;

import java.awt.*;

public class Colors {

    // GAMEFIELD COLORS
    public static final Color GROUND_1 = Color.decode("#4aa832");
    public static final Color GROUND_2 = Color.decode("#017821");
    public static final Color BG = Color.decode("#356e26");

    //apple colors
    public static final Color apple_red = Color.decode("#e71f1f");
    public static final Color apple_brown = Color.decode("#c35d2d");
    public static final Color apple_white = Color.decode("#f0e7e2");

    //potential bordercolors
    public static final Color Border_dark_blue = Color.decode("#09205c");
    public static final Color Border_dark_brown = Color.decode("#643620");
    public static final Color Border_light_brown = Color.decode("#d7825a");
    public static final Color Border_light_gray = Color.decode("#7a7c88");
    public static final Color Border_orrange = Color.decode("#e09814");
    public static final Color Border_pink = Color.decode("#de1bb3");
    public static final Color Border_purple = Color.decode("#62355a");
    public static final Color Border_turquoise = Color.decode("#14e5bd");
    public static final Color Border_yellow = Color.decode("#ebdf5b");

    //Body Colors
    public static final Color Player_own = Color.decode("#5079e4");
    public static final Color Player_enemy = Color.decode("#7a7c88");
    public static final Color Player_eye_white = Color.decode("#ffffff");
    public static final Color Player_eye_black = Color.decode("#000206");

    // CONSOLE COLORS
    public static final String CONSOLE_RESET = "\033[0m";
    public static final String CONSOLE_DEBUG = "\033[0;36m"; //light blue / cyan
    public static final String CONSOLE_ERROR = "\033[0;31m"; // red
    public static final String CONSOLE_INFO = CONSOLE_RESET; // no color

    // chat overlay colors
    public static final Color OVERLAY_BACKGOUNR = new Color(0, 0, 0, 50);

}
