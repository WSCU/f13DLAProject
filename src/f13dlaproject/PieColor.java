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
    public void chooseColor(CParticle c) {
        Point p = c.getPos();
        int m = (int) (p.getAngle() / cangle) % colors.length;
        c.setColor(colors[m]);
    }
}

