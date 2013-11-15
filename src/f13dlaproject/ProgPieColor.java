/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;

/**
 * A coloring strategy class that divides the crystal into separately colored pie clusters, with colors progressively fading into each other
 * @author outcast
 */
public class ProgPieColor implements ColoringStrategy {

    Color[] colors;
    double cangle;

    /**
     * Constructs a Progressive Pie Color coloring strategy 
     * Divides the slices of the crystal equally according to the number of colors in the array
     * @param colors The color array that will be used for the pie slices 
     */
    public ProgPieColor(Color[] colors) {
        this.colors = colors;
        this.cangle = 2 * Math.PI / colors.length;
    }
    /**
     * Allows pie slices to progressively change color by calculating the difference between colors and gradually transitioning
     */
    @Override
    public void chooseColor(CParticle c) {
        Point p = c.getPos();
        int m = (int) (p.getAngle() / cangle) % colors.length;
        float difR, difG, difB;
        float[] color = new float[3];
        float[] tocolor = new float[3];

        if (m == colors.length-1) {

            colors[m].getColorComponents(color);
            colors[0].getColorComponents(tocolor);

            difR = color[0] - tocolor[0];
            difG = color[1] - tocolor[1];
            difB = color[2] - tocolor[2];
                        
        } else {

            colors[m].getColorComponents(color);
            colors[m + 1].getColorComponents(tocolor);

            difR = color[0] - tocolor[0];
            difG = color[1] - tocolor[1];
            difB = color[2] - tocolor[2];

        }

        float per = (float)((p.getAngle()- m*cangle) / cangle);
        float dR = difR * per;
        float dG = difG * per;
        float dB = difB * per;

        float r = color[0] - dR;
        float g = color[1] - dG;
        float b = color[2] - dB;


        c.setColor(new Color(r, g, b));
    }
}
