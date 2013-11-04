/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

/**
 *
 * @author outcast
 */
public interface Crystal {
    
    public double getRadius();
    
    public double getZoom();
    
    public void add(Particle2D p);
    
    public int getSize();
    
    public boolean collides();
}
