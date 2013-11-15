/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;
import java.awt.Graphics;

/**
 * An interface that represents crystal particles and methods
 * Inner class for CParticle located in {@link Crystal}
 * @author stu781945
 */
public interface CParticle {
    
    /**
     * Draws CParticles to the screen 
     * @param g the CParticle to be drawn
     */
    public void draw(Graphics g);
    
    /**
     * If a particle collides with a crystal node, make a new crystal node 
     * @return a new CParticle (crystal node)
     */
    public CParticle collides();
    
    /**
     * Sets the color of a crystal node. 
     * @param c The color to set the crystal node
     */
    public void setColor(Color c);
    
    /**
     * Accesses the color of the current crystal node. 
     * @return The color of the crystal node 
     */
    public Color getColor();
    
    /**
     * Accesses the {@link Point} coordinates of the crystal node (x, y)
     * @return The point coordinate the the crystal node
     */
    public Point getPos();
    
    /**
     * Finds the parent node of the current crystal node 
     * @return The parent node from which the current crystal node has branched from. 
     */
    public CParticle getParent();
    
    /**
     * Accesses the rank number of the crystal node (ranked according to time added to crystal) 
     * @return The rank number of the crystal node
     */
    public int getNum();
    
    /**
     * Finds the distance of the node from the origin
     * @return The distance from the crystal node to the origin of the crystal
     */
    public double getDist();
    
}
