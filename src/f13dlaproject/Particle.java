/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;


import static f13dlaproject.Point2.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
/**
 *
 * @author stu781945
 */
public class Particle {
    
    private static Particle uniqueInstance; //Singleton pattern create variable
    private Point2 p; //holds location of particle
    private Point2 dir; //holds a unit vector of the direction
    private double vel; //holds magnitude of the velocity
    private double a; //range of turn
    private int numLaunched; //holds the number of particles launched
    private static Random r = new Random(); //random generator
    
    private Particle(){ //private constructor
        this.p = point2(0, 0);
        this.vel = 0;
        this.dir = point2(0, 1);
        this.a = 0;
        this.numLaunched = 1;
    }
    
    //setters
    public void setVelocity(double v){
        this.vel = v;
    }
    
    public void setA(double a){
        this.a = a;
    }
    
    public void setAngle(){
        double angle = 2*(r.nextDouble()-0.5)*this.a; //finds random double between 0, a)
        double oang = Math.acos(this.dir.getX()); //finds the original angle from origin
        this.dir.setX(Math.cos(oang + angle)); //calculates new x dir
        this.dir.setY(Math.sin(oang + angle)); // calculates new y dir
    }
    
    //getters
    public int getLaunched(){
        return numLaunched;
    }
    
    public double getVelocity(){
        return vel;
    }
    
    public Point2 getPosition(){
        return p;
    }
    
    public void move(){ //move and collison detection
        this.setAngle();
        p.setX(p.getX() + dir.getX()*vel);
        p.setY(p.getY() + dir.getY()*vel);
    }
    
    public void reset(){ //reset particle to origin
        this.p.setX(0);
        this.p.setY(0);
        this.numLaunched++;
    }
    
    public void clear(){ //clear()
        this.reset();
        this.numLaunched = 0;
    }
    
    public void draw(Graphics g){ //draw
        g.setColor(Color.RED);
        g.fillOval((int)p.getX(), (int)p.getY(), 1, 1);
    }
    
    @Override
    public String toString(){
        return "Position: (" + p.getX() + ", " + p.getY() + ") Direction: " + Math.cos(dir.getX()) + " radians";  
    }
    
    /*
     * static factory,
     * checks the singleton variable to see if it has been created yet,
     * if not create instance
     * return instance
     */
    public static Particle particle(){ 
        if(uniqueInstance == null){
            uniqueInstance = new Particle();
        }
        return uniqueInstance;
    }
    
}
