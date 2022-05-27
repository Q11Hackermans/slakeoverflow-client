package com.github.q11hackermans.slakeoverflow_client.view;

import javax.swing.*;

public abstract class View extends JPanel {

    /**
     * Get the value of the Start-Panel host text-field
     *
     * @return String - host
     */
    public String getHost() {
        return "";
    }

    /**
     * Get the value of the Start-Panel port text-field
     *
     * @return String - port
     */
    public String getPort() {
        return "";
    }
}
