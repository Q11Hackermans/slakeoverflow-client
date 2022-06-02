package com.github.q11hackermans.slakeoverflow_client.panels;

import javax.swing.*;

public abstract class Panel extends JPanel {

    /**
     * Get the value of the Start-Panel host text-field
     *
     * @return String - host
     */
    public String getHost() {
        return "";
    }

    public String getUsername() {
        return "";
    }

    public String getPasswordHash() {
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
