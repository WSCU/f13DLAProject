/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

/**
 *A class that represents a Point.
 * 
 * @author stu781945
 */
public class Point2 implements Point{
    
    /**
     * The x coordinate of the Point.  
     */
    private double x; 
    /**
     * The y coordinate of the point.   
     */
    private double y; 
    /**
     * Constructs a new point
     * @param x the x coordinate of the point. 
     * @param y the y coordinate of the point
     */
    public Point2(double x, double y){ //constructor
        this.x = x;
        this.y = y;
    }
    
    /**
     * Sets the x coordinate of an existing point
     * @param x the x coordinate of the point. 
     */
    public void setX(double x){
        this.x = x;
    }
    /**
     * Sets the y coordinate of an existing point
     * @param y the y coordinate of the point. 
     */   
    public void setY(double y){
        this.y = y;
    }
    
    /**
     * Gets the x coordinate of a point. 
     * @return the x coordinate of a point. 
     */
    public double getX(){
        return x;
    }
    /**
     * Gets the y coordinate of a point. 
     * @return the y coordinate of a point. 
     */
    public double getY(){
        return y;
    }
    /**
     * Clones an existing point. 
     * @param p the point to be cloned.  
     * @return the new cloned point.  
     */
    public Point clone(Point p){
        return new Point2(p.getX(), p.getY());
    }
     /**
     * A factory that makes points. 
     * @param x the x coordinate of a point
     * @param y the y coordinate of a point
     * @return a new point  
     */   
    public static Point2 point2(double x, double y){ //static factory
        return new Point2(x,y);
    }

    @Override
    public double getZ() {
        return 0;
    }

    @Override
    public void setZ(double z) {
        System.out.println("2D point: no z componant");
    }

    @Override
    public double length() {
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }

    @Override
    public double getAngle() {
        double angle = Math.acos(x/this.length());
        if(y < 0){
            angle += 2 * (Math.PI - angle);
        }
        return angle;
    }
    
}
