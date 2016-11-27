package com.mmnaseri.cs.ctci.ch08.p12;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/26/16, 11:50 PM)
 */
public class BoardPrinter {

    public void print(List<Integer> positions) {
        if (positions == null) {
            System.out.println("No placement found!");
            return;
        }
        for (int i = 0; i < positions.size(); i++) {
            for (int j = 0; j < positions.size(); j++) {
                System.out.print(positions.get(i) == j ? 'Q' : '.');
            }
            System.out.println();
        }
    }

}
