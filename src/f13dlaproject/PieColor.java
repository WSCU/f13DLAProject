/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;

/**
 * A coloring strategy class that divides the crystal into separately colored pie clusters 
 * @author outcast
 */
public class PieColor implements ColoringStrategy {


    Color[] colors;
    double cangle;

    /**
     * Constructs a PieColor coloring strategy 
     * Divides the slices of the crystal equally according to the number of colors in the array
     * @param colors The color array that will be used for the pie slices 
     */
    public PieColor(Color[] colors) {
        this.colors = colors;
        this.cangle = 2 * Math.PI / colors.length;
    }

    @Override
    public void chooseColor(CParticle c) {
        Point p = c.getPos();
        int m = (int) (p.getAngle() / cangle) % colors.length;
        c.setColor(colors[m]);
    }
}

