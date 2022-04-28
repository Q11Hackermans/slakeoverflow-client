//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client;

public class SlakeoverflowClient {

    public static void main(String[] args) {
        System.out.println("-- launching...");

        // debug
        int[][] demoArray = {
                { 0, 1, 0 },
                { 0, 0, 1 },
                { 0, 0, 1 }
        };

        GameFrame f = new GameFrame();
        f.render(demoArray);
    }
}
