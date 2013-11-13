/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

/**
 *
 * @author stu738510
 */
public class Point3 implements Point {

    private double x,y,z;
    
    public Point3(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    
    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public Point clone(Point p) {
        return new Point3(p.getX(),p.getY(),p.getZ());
    }
    
    public static Point3 point3(double x, double y, double z){
        return new Point3(x,y,z);
    }

    @Override
    public double length() {
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
    }

    @Override
    public double getAngle() {
        return Math.cos(x);
    }

    @Override
    public double angleDiff(Point p) {
        double dot = (this.getX()*p.getX()+this.getY()*p.getY()+this.getZ()*p.getZ());
        double val = dot/(this.length()*p.length());
        return val;
    }
   
}
