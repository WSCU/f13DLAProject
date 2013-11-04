/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

/**
 *
 * @author outcast
 */
public interface Point {
    
    public double getX();
    
    public double getY();
    
    public double getZ();
    
    public void setX(double x);
    
    public void setY(double y);
    
    public void setZ(double z);
    
    public Point clone(Point p);
    
}
