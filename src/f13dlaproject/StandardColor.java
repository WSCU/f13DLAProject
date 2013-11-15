/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;

/**
 * A coloring strategy that colors the crystal in a standard incremental way based on distance
 * @author outcast
 */
public class StandardColor implements ColoringStrategy {

    private Color[] colors;
    /**
     *
     * @param c The color array that will be used for the individual rings
     */
    public StandardColor(Color[] c) {
        this.colors = c;
    }
    /**
     * Gradually cycle through each color in the array, progressively transitioning between each color
     * Transitions are based on distance 
     */
    @Override
    public void chooseColor(CParticle c) {
        double dist = c.getDist();
        int changeDist = 20;
        int m = (int)dist/changeDist % colors.length;
        float difR,difG,difB;
        float[] color = new float[3];
        float[] tocolor = new float[3];
        
    
            
            colors[m].getColorComponents(color);
            colors[(m+1)%colors.length].getColorComponents(tocolor);
                       
            difR = color[0] - tocolor[0];
            difG = color[1] - tocolor[1];
            difB = color[2] - tocolor[2];
        
        
            float per = (1.0f*((int)dist%changeDist))/changeDist;
            
            float dR = difR*per;
            float dG = difG*per;
            float dB = difB*per;
            
            float r = color[0] - dR;
            float g = color[1] - dG;
            float b = color[2] - dB; 
            
            
        c.setColor(new Color(r, g, b));
    }
}
