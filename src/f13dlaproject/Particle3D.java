/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import static f13dlaproject.Crystal3D.*;
import java.awt.Graphics;
import java.util.Random;
import static f13dlaproject.Point3.*;
import static f13dlaproject.Crystal3D.*;
import static f13dlaproject.Point3.*;

/**
 *
 * @author stu738510
 */
public class Particle3D implements Particle {

    /**
     * A unique instance of a particle3D
     */
    private static Particle3D uniqueInstance;
    /**
     * holds position of a particle3D.
     */
    private Point3 p;
    /**
     * holds a unit vector of the direction
     */
    private Point3 dir;
    /**
     * holds magnitude of the velocity
     */
    private double vel;
    /**
     * stores the range of the turn
     */
    private double a;
    /**
     * holds the distance value before reset
     */
    private double bounds;
    /**
     * holds the number of particles launched
     */
    private int numLaunched;
    /**
     * a new Random
     */
    private static Random r = new Random();
    
    @Override
    public void setVelocity(double v) {
        this.vel = v;
    }
    
    @Override
    public void setA(double a) {
        this.a = a;
    }
    
    @Override
    public void setAngle() {
        double angleMax = a;//Math.cos(a);
        double x = r.nextDouble(), y = r.nextDouble(), z = r.nextDouble();
        Point3 p2 = new Point3(x, y, z);//Makes vector
        Point3 newDir = new Point3(x / p2.length(), y / p2.length(), z / p2.length());//makes it a unit vector
        if (angleDiff(dir, newDir) > angleMax) {
            setAngle();
        }//checks to see if the angle is within the cone
        else {
            dir = newDir;
        }
    }
    
    @Override
    public void setPosition() {
        double theta = r.nextDouble() * 2 * Math.PI;
        double phi = r.nextDouble() * 2 * Math.PI;
        double r = crystal3D().getRadius() + 5;
        double x = r * Math.sin(theta) * Math.cos(phi);
        double y = r * Math.sin(theta) * Math.sin(phi);
        double z = r * Math.cos(theta);
        this.p.setX(x);
        this.p.setY(y);
        this.p.setZ(z);
    }
    
    @Override
    public int getLaunched() {
        return numLaunched;
    }
    
    @Override
    public double getVelocity() {
        return vel;
    }
    
    @Override
    public Point getPosition() {
        return p;
    }
    
   
    public Point getAngle() {
        return dir;
    }
    
    @Override
    public void move() {
         p.setX(p.getX() + dir.getX() * vel);
        p.setY(p.getY() + dir.getY() * vel);
        p.setZ(p.getZ() + dir.getZ() * vel);
        double dist = p.length();
        if(crystal3D().collides()||dist>bounds){
            reset();
            bounds = crystal3D().getRadius() + 5;
        }
    }
    
    @Override
    public void reset() {
       this.setPosition();
        this.numLaunched++;
    }
    
    @Override
    public void clear() {
        this.reset();
        this.numLaunched = 0;
    }
    
    @Override
    public void draw(Graphics g) {
        
        
    }
    
    private Particle3D(){
         this.p = point3(0, 0, 0);
        double rad = crystal3D().getRadius() + 5;
        double angle = r.nextDouble() * 2 * Math.PI;
        p.setX(rad * Math.cos(angle));
        p.setY(rad * Math.sin(angle));
        this.bounds = rad + 15;
        this.vel = 0;
        this.dir = point3(0, 0, 0);
        dir.setX(-Math.cos(angle));
        dir.setY(-Math.sin(angle));
        this.a = 0;
        this.numLaunched = 1;
    }
    
    /*
     * static factory,
     * checks the singleton variable to see if it has been created yet,
     * if not create instance
     * return instance
     */
    public static Particle3D particle3D() {
        if (uniqueInstance == null) {
            uniqueInstance = new Particle3D();
        }
        return uniqueInstance;
    }
}