/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Graphics;

/**
 * An interface that provides the framework for the current walking particle and its methods
 * {@link Particle2D} and {@link Particle3D} implement this interface
 * @author outcast
 */
public interface Particle {
    
    /**
     * Sets the magnitude of the velocity of the particle
     * @param v The velocity magnitude in which to set the particle 
     */
    public void setVelocity(double v);
    
    /**
     * Sets the range of the particle turn
     * @param a The range of turn in which to set the particle
     */
    public void setA(double a);
    
    /**
     * Sets the angle of the particle
     * Calculates new x and y directions based on the current angle
     */
    public void setAngle();
    
    /**
     * Sets the position and direction of the particle. 
     * 
     */
    public void setPosition();
    
    /**
     * Sets position of particle to the given point
     */
    public void setPosition(Point p);
    
    /**
     * Tells us the number of particles launched so far
     * @return The number of particles launched
     */
    public int getLaunched();
    
    /**
     * Gets the magnitude of the particle's velocity
     * @return The magnitude of the particle's velocity
     */
    public double getVelocity();
      
    /**
     * Gets the position (Point) of a particle
     * @return the Point position of a particle
     */
    public Point getPosition();
     
    /**
     * Sets the position, direction, and velocity of a particle, detects collisions
     */
    public void move();
    
    /**
     * Resets the particle to the origin
     */
    public void reset();
    
    /**
     * Clears all the existing particles
     */
    public void clear();
    
    /**
     * Draws the particle on the screen
     * @param g The current walking particle to be drawn
     */
    public void draw(Graphics g);
}
