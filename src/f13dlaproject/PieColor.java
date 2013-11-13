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
public class PieColor implements ColoringStrategy {


    Color[] colors;
    double cangle;

    public PieColor(Color[] colors) {
        this.colors = colors;
        this.cangle = 2 * Math.PI / colors.length;
    }

    @Override
    public Color chooseColor(double dist, int num, Point p, CParticle parent) {
        int m = (int) (p.getAngle() / cangle) % colors.length;
        return colors[m];
    }
}

