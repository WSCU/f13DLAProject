/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * An interface for an entire Crystal
 * {@link Crystal2D} and {@link Crystal3D} implement this interface
 * @author outcast
 */
public interface Crystal {
    
    /**
     * Gets the radius of the whole crystal
     * @return The radius of the whole crystal
     */
    public double getRadius();
    
    /**
     * Gets the zoom factor of the crystal 
     * @return The zoom factor of the crystal
     */
    public double getZoom();
    
    /** 
     * Adds nodes to the crystal
     * @param p The particle to be added to the crystal
     * @param parent The parent in which to attach the current walking particle
     */
    public void add(Particle p, CParticle parent);
    
    /**
     * Gets the size (number of nodes) of the crystal
     * @return The size of the crystal
     */
    public int getSize();
     /**
     * Gets the cparticle representation of the crystal
     * @return the crystal
     */  
    public void outputToFile();
    /**
     * Determines if each individual particle has collided. 
     * @return Return true if any of the particles have collided, false if not
     */
    
    public boolean collides();
    
    /**
     * Sets the coloring we want for the crystal 
     * @param Color the current coloring strategy of the crystal 
     */
    public void setColorStrategy(ColoringStrategy Color);
    
    /**
     * Sets the zoom factor of the crystal 
     * @param z The zoom factor in which to set the crystal 
     */
    public void setZoom(int z);
    
    /**
     * Draws the crystal to the screen 
     * @param g The crystal to be drawn
     */
    public void draw(Graphics g);
    
    /**
     * Resets the crystal to nothing. 
     */
    public void clear();
}
