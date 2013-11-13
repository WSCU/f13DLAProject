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
public interface Particle {
    
    public void setVelocity(double v);
    
    public void setA(double a);
    
    public void setAngle();
    
    public void setPosition();
    
    public int getLaunched();
    
    public double getVelocity();
      
    public Point getPosition();
     
    public void move();
    
    public void reset();
    
    public void clear();
    
    public void draw(Graphics g);
}
