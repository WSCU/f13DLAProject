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
     * holds particles position.
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
        if (a != 0) {
            double angle = 2 * (r.nextDouble() - 0.5) * this.a; //finds random double between -a, a
            double oang = this.getAngle();
            this.dir.setX(Math.cos(oang + angle)); //calculates new x dir
            this.dir.setY(Math.sin(oang + angle)); // calculates new y dir
        }
    }
    
    /**
     *
     * @return unit vector of the point direction
     */
    public Point getDirection() {
        return dir;
    }
    
    @Override
    public void setPosition() {
        double rad = crystal2D().getRadius() + 5;
        double angle = r.nextDouble() * 2 * Math.PI;
        p.setX(rad*Math.cos(angle));
        p.setY(rad*Math.sin(angle));
        dir.setX(-Math.cos(angle));
        dir.setY(-Math.sin(angle));
    }

    //getters
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

    /**
     *
     * @return The angle of the 2D particle 
     */
    public double getAngle() {
        double oang = Math.acos(this.dir.getX()); //finds the original angle from origin
        if (this.dir.getY() < 0) {
            oang *= -1;
        }
        return oang;
    }

    @Override
    public void move() { //move and collison detection
        //this.setAngle();
        p.setX(p.getX() + dir.getX() * vel);
        p.setY(p.getY() + dir.getY() * vel);
        double dist = Math.sqrt(Math.pow(p.getX(), 2)+Math.pow(p.getY(), 2));
        if(dist>bounds) {
            reset();
            bounds = crystal2D().getRadius() + 5;
        }
        else if(this.p.length() < crystal2D().getRadius()+1) {
            if(crystal2D().collides()){
                reset();
                bounds = crystal2D().getRadius() + 5;
            }
        }
    }

    @Override
    public void reset() { //reset particle2D to origin
        this.setPosition();
        this.numLaunched++;
    }

    @Override
    public void clear() { //clear()
        this.reset();
        this.numLaunched = 0;
    }

    @Override
    public void draw(Graphics g) { //draw
        g.setColor(Color.RED);
        Crystal2D c = crystal2D();
        g.fillOval((int)(p.getX()*c.getZoom() - c.getZoom()/2)+(DLAFrame.WIDTH/2)-DLAFrame.dx, (int)(p.getY()*c.getZoom()-c.getZoom()/2)+(DLAFrame.HEIGHT/2) - DLAFrame.dy, (int)(c.getZoom()), (int)(c.getZoom()));
    }

    /**
     * String representation of the 2D Particle
     *
     * @return A String representation of a 2D Particle's position, directional
     * angle in radians, and the number launched
     */
    @Override
    public String toString() {
        return "Position: (" + p.getX() + ", " + p.getY() + ") Direction: " + this.getAngle() + " radians, Number Launched: " + this.numLaunched;
    }

     /**
     * Static factory Checks the singleton variable to see if it has been
     * created yet If not create an instance
     *
     * @return unique instance of a 2D Particle
     */
    public static Particle2D particle2D() {
        if (uniqueInstance == null) {
            uniqueInstance = new Particle2D();
        }
        return uniqueInstance;
    }
    
    public void setPosition(Point p) {
        this.p = (Point2) p;
    }

}
