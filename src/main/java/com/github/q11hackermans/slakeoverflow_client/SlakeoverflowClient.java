//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client;

import java.util.Arrays;

public class SlakeoverflowClient {

    public static void main(String[] args) {
        System.out.println("-- launching...");

        // debug
        int[][] demoArray = {
                { 0, 1, 0 },
                { 0, 0, 1 },
                { 0, 0, 1 }
        };

        System.out.println(Arrays.deepToString(demoArray));

        GameFrame f = new GameFrame();
        f.render(demoArray);
    }
}
