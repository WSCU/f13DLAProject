/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;

/**
 * A coloring strategy that colors the crystal in incremental rings
 * @author outcast
 */
public class RingColor implements ColoringStrategy{
    
    private Color[] colors;
    
  /**
     *
     * @param c The color array that will be used for the individual rings
     */
    public RingColor(Color[] c) {
        this.colors = c;
    }
    /**
     * Make a new ring based on the current particle's distance from the center divided by 10 
     * Cycle through each color for the specified time 
     */
    @Override
    public void chooseColor(CParticle c) {
        double dist = c.getDist();
        int changeDist = 10;
        int m = (int)dist/changeDist % colors.length;
        c.setColor(colors[m]);
    }

    
}
