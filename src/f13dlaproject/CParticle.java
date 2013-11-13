/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author stu781945
 */
public interface CParticle {
    
    public void draw(Graphics g);
    
    public CParticle collides();
    
    public void setColor(Color c);
    
    public Color getColor();
    
    public Point getPos();
    
    public CParticle getParent();
    
    public int getNum();
    
    public double getDist();
    
}
