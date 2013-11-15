/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

/**
 * An interface to represent a Point (x, y) of the crystal node
 * {@link Point2D} and {@link Point3D} implement this interface
 * @author outcast
 */
public interface Point {
    
    /**
     * Gets the x coordinate of a point 
     * @return The double x coordinate of a point 
     */
    public double getX();
    
    /**
     * Gets the y coordinate of a point 
     * @return The double y coordinate of a point 
     */
    public double getY();
    
    /**
     * Gets the z coordinate of a point. Utilized in {@link Point3D} 
     * @return The double z coordinate of a point 
     */
    public double getZ();
    
    /**
     * Gets the angle of a point from the origin 
     * @return The (double) angle of a point from the origin. 
     */
    public double getAngle();
    
    /**
     * Sets the x location of a Point 
     * @param x The x coordinate in which to set the point 
     */
    public void setX(double x);
    
    /**
     * Sets the y location of a Point 
     * @param y The y coordinate in which to set the point 
     */
    public void setY(double y);
    
    /**
     * Sets the z location of a Point, utilized in {@link Point3D} 
     * @param z The z coordinate in which to set the point 
     */
    public void setZ(double z);
    
    /**
     * Clones an existing point 
     * @param p The point to be cloned 
     * @return The new cloned point
     */
    public Point clone(Point p);
    
    /**
     * Gets the length of the point from the origin 
     * @return The length of the point from the origin 
     */
    public double length();
    
    /**
     * Returns the angle different between two points
     * @param p the Point to compare 
     * @return The angle different between the current point and the passed in point
     */
    public double angleDiff(Point p);
    
}
