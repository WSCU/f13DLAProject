/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

/**
 *
 * @author stu781945
 */
public class Point2 {
    
    private double x,y;
    
    public Point2(double x, double y){ //constructor
        this.x = x;
        this.y = y;
    }
    
    //setter methods
    public void setX(double x){
        this.x = x;
    }
    
    public void setY(double y){
        this.y = y;
    }
    
    //getter methods
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public Point2 clone(Point2 p){
        return new Point2(p.getX(), p.getY());
    }
    
    public static Point2 point2(double x, double y){ //static factory
        return new Point2(x,y);
    }
    
}
