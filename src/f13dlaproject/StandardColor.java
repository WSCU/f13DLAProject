/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;

/**
 *
 * @author outcast
 */
public class StandardColor implements ColoringStrategy {

    private Color[] c;

    public StandardColor(Color[] c) {
        this.c = c;
    }

    @Override
    public Color chooseColor(int num, double dist, Point p) {
        int rader = (int) dist * 10;
        int radest = rader % 250;
        int m = rader / 250 % 3;
        int r = c[0].getRed();
        int g = c[0].getGreen();
        int b = c[0].getBlue();
        System.out.println("m = "+m+",  radest = "+radest);
        if (m == 0) {
            System.out.println("m = "+m+",  radest = "+radest);
            if (c[1].getRed() > c[0].getRed()) {
                r = c[0].getRed() + radest;
            } else if (c[1].getRed() < c[0].getRed()) {
                r = c[0].getRed() - radest;
            }
            if (c[1].getGreen() > c[0].getGreen()) {
                g = c[0].getGreen() + radest;
            } else if (c[1].getGreen() < c[0].getGreen()) {
                g = c[0].getGreen() - radest;
            }
            if (c[1].getBlue() > c[0].getBlue()) {
                b = c[0].getBlue() + radest;
            } else if (c[1].getBlue() < c[0].getBlue()) {
                b = c[0].getBlue() - radest;
            }
        }
        if (m == 1) {
            System.out.println("m = "+m+",  radest = "+radest);
            if (c[2].getRed() > c[1].getRed()) {
                r = c[1].getRed() + radest;
            } else if (c[2].getRed() < c[1].getRed()) {
                r = c[1].getRed() - radest;
            }
            if (c[2].getGreen() > c[1].getGreen()) {
                g = c[1].getGreen() + radest;
            } else if (c[2].getGreen() < c[1].getGreen()) {
                g = c[1].getGreen() - radest;
            }
            if (c[2].getBlue() > c[1].getBlue()) {
                b = c[1].getBlue() + radest;
            } else if (c[2].getBlue() < c[1].getBlue()) {
                b = c[1].getBlue() - radest;
            }
        }
        else{   
            System.out.println("m = "+m+",  radest = "+radest);
            r = c[2].getRed();
            g = c[2].getGreen();
            b = c[2].getBlue();
        }
        System.out.println("m = "+m+",  radest = "+radest);
        return new Color(r, g, b);
    }
}
