/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;

/**
 * A coloring strategy class that looks all nuclear and bumblebee like 
 * @author outcast
 */
public class NuclearColor implements ColoringStrategy{
    
    Color[] colors = {Color.BLACK, Color.YELLOW};
        /**
     * Constructs a NuclearColor strategy 
     * Create a point that is the current position of the passed in CParticle 
     * Divides the crystal into 6 angle segments 
     * Alternates the color of the angle segments between yellow and black 
     */
    public NuclearColor(){}

    @Override
    public void chooseColor(CParticle c) {
        Point p = c.getPos();
        int m = (int) (p.getAngle() / (2*Math.PI/6)) % 6;
        c.setColor(colors[m%2]);
    }
    
}
