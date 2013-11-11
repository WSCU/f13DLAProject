/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Graphics;

/**
 *
 * @author outcast
 */
public interface Crystal {
    
    public double getRadius();
    
    public double getZoom();
    
    public void add(Particle p);
    
    public int getSize();
    
    public boolean collides();
    
    public void setColorStrategy(ColoringStrategy Color);
    
    public void setZoom(int z);
    
    public void draw(Graphics g);
    
    public void clear();
}
