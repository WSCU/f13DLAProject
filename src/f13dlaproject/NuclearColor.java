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
public class NuclearColor implements ColoringStrategy{
    
    Color[] colors = {Color.BLACK, Color.YELLOW};
    
    public NuclearColor(){}

    @Override
    public void chooseColor(CParticle c) {
        Point p = c.getPos();
        int m = (int) (p.getAngle() / (2*Math.PI/6)) % 6;
        c.setColor(colors[m%2]);
    }
    
}
