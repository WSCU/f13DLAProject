/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import static f13dlaproject.Point2.*;
import static f13dlaproject.Crystal2D.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * A class that represents a particle2D of a crystal
 *
 * @author Graham and Ryan
 */
public class Particle2D implements Particle {

    /**
     * A unique instance of a particle2D
     */
    private static Particle2D uniqueInstance;
    /**
     * holds instance of a particle2D.
     */
    private Point2 p;
    /**
     * holds a unit vector of the direction
     */
    private Point2 dir;
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

    private Particle2D() { //private constructor
        this.p = point2(0, 0);
        double rad = crystal2D().getRadius() + 5;
        double angle = r.nextDouble() * 2 * Math.PI;
        p.setX(rad * Math.cos(angle));
        p.setY(rad * Math.sin(angle));
        this.bounds = rad + 15;
        this.vel = 0;
        this.dir = point2(0, 0);
        dir.setX(-Math.cos(angle));
        dir.setY(-Math.sin(angle));
        this.a = 0;
        this.numLaunched = 1;
    }

    //setters
    public void setVelocity(double v) {
        this.vel = v;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setAngle() {
        if (a != 0) {
            double angle = 2 * (r.nextDouble() - 0.5) * this.a; //finds random double between -a, a
            double oang = this.getAngle();
            this.dir.setX(Math.cos(oang + angle)); //calculates new x dir
            this.dir.setY(Math.sin(oang + angle)); // calculates new y dir
        }
    }

    public void setPosition() {
        double rad = crystal2D().getRadius() + 5;
        double angle = r.nextDouble() * 2 * Math.PI;
        p.setX(rad*Math.cos(angle));
        p.setY(rad*Math.sin(angle));
        dir.setX(-Math.cos(angle));
        dir.setY(-Math.sin(angle));
    }

    //getters
    public int getLaunched() {
        return numLaunched;
    }

    public double getVelocity() {
        return vel;
    }

    public Point getPosition() {
        return p;
    }

    public double getAngle() {
        double oang = Math.acos(this.dir.getX()); //finds the original angle from origin
        if (this.dir.getY() < 0) {
            oang *= -1;
        }
        return oang;
    }

    public void move() { //move and collison detection
        //this.setAngle();
        p.setX(p.getX() + dir.getX() * vel);
        p.setY(p.getY() + dir.getY() * vel);
        double dist = Math.sqrt(Math.pow(p.getX(), 2)+Math.pow(p.getY(), 2));
        if(crystal2D().collides()||dist>bounds){
            reset();
            bounds = crystal2D().getRadius() + 5;
        }
    }

    public void reset() { //reset particle2D to origin
        this.setPosition();
        this.numLaunched++;
    }

    public void clear() { //clear()
        this.reset();
        this.numLaunched = 0;
    }

    public void draw(Graphics g) { //draw
        g.setColor(Color.RED);
        Crystal2D c = crystal2D();
        g.fillOval((int)(p.getX()*c.getZoom() - c.getZoom()/2)+(DLAFrame.WIDTH/2)-DLAFrame.dx, (int)(p.getY()*c.getZoom()-c.getZoom()/2)+(DLAFrame.HEIGHT/2) - DLAFrame.dy, (int)(c.getZoom()), (int)(c.getZoom()));
    }

    @Override
    public String toString() {
        return "Position: (" + p.getX() + ", " + p.getY() + ") Direction: " + this.getAngle() + " radians, Number Launched: " + this.numLaunched;
    }

    /*
     * static factory,
     * checks the singleton variable to see if it has been created yet,
     * if not create instance
     * return instance
     */
    public static Particle2D particle2D() {
        if (uniqueInstance == null) {
            uniqueInstance = new Particle2D();
        }
        return uniqueInstance;
    }

}
