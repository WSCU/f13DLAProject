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
    public Color chooseColor(double dist, int num, Point p, CParticle parent) {
        int changeDist = 10;
        int m = (int)dist/changeDist % c.length;
        return c[m];
    }

    
}
