/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;

/**
 *
 * @author stu473293
 */
public class RingColor implements ColoringStrategy{
    
    private Color[] c;
    
    public RingColor(Color[] c) {
        this.c = c;
    }

    @Override
    public Color chooseColor(double dist) {
        int changeDist = 10;
        int m = (int)dist/changeDist % 3;
        if(m == 0) {
            return c[0];
        }
        if(m == 1) {
            return c[1];           
        }
        if(m == 2) {
            return c[2];
        }
        else return c[0];
    }
    
}
