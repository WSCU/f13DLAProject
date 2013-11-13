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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static double angleDiff(Point3 v1, Point3 v2){
        double h = (v1.getX()*v2.getX()+v1.getY()*v2.getY()+v1.getZ()*v2.getZ());
        return Math.acos(h/(v1.length()*v2.length()));
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
   
}
