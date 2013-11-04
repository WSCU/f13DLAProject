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
    
    public StandardColor(Color[] c){
        this.c = c;
    }

    @Override
    public Color chooseColor(int num, double dist, Point p) {
        /*int ra = (int) rad;
            int z = 10;
            int rader = (int) rad * 10;
            int radest = rader % 250;
            int m = rader / 250 % 3;
            int r = 0;
            int g = 255;
            int b = 0;
            //System.out.println("m = "+m+",  radest = "+radest);
            if (m == 0) {
                r = 0;
                g = 255 - radest;
                b = 0 + radest;
            }
            if (m == 1) {
                r = 0 + radest;
                g = 0;
                b = 255 - radest;
            }
            if (m == 2) {
                r = 255 - radest;
                g = 0 + radest;
                b = 0;
            }
            return new Color(r, g, b);
            */
            return Color.BLACK;
    }
    
}
