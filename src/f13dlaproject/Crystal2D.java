/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import static f13dlaproject.Point2.*;
import static f13dlaproject.Particle2D.*;

/**
 *
 * @author Graham and Ryan
 */
public class Crystal2D implements Crystal{

    private static Crystal2D instance;
    private int count;
    private double radius;
    private double zoom;
    private ColoringStrategy color;
    private ArrayList<CParticle> parts = new ArrayList();

    /*
     * Declaration of inner CParticle class
     * Be each node in crystal
     */
    private class CParticle {

        private Point p; //position of node
        private int num; //number in which it was added to crystal
        private double dist; //distance from center of cystal
        
        public CParticle(Point p, int num) { //constructor
            this.p = p.clone(p);
            this.num = num;
            this.dist = Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2));
        }

        public void draw(Graphics g) { //draw
            g.setColor(color.chooseColor(num, dist, p));
            g.fillOval((int) (p.getX() * zoom - zoom / 2) + (DLAFrame.WIDTH / 2) - DLAFrame.dx, (int) (p.getY() * zoom - zoom / 2) + (DLAFrame.HEIGHT / 2) - DLAFrame.dy, (int) zoom, (int) zoom);
        }

        public boolean collides() {
            Particle2D t = particle();
            Point pos = t.getPosition();
            double dist = Math.sqrt(Math.pow(p.getX() - pos.getX(), 2) + Math.pow(p.getY() - pos.getY(), 2));
            if (dist < 1) {
                return true;
            }
            return false;
        }
    }

    private Crystal2D() { //constructor
        this.count = 1;
        this.radius = 0;
        this.zoom = 20;
        Color[] c = {Color.BLUE, Color.GREEN, Color.RED};
        this.color = new StandardColor(c);
        parts.add(new CParticle(point2(0, 0), count));
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public double getZoom() {
        return zoom;
    }

    @Override
    public void add(Particle2D p) {//adds node to crystal
        count++;
        CParticle part = new CParticle(p.getPosition(), count);
        parts.add(part);
        double dist = Math.sqrt(Math.pow(part.p.getX(), 2) + Math.pow(part.p.getY(), 2));
        if (dist > radius) {
            radius = dist;
           if(DLAFrame.autoZ){
            zoom = 20 - Math.pow((radius) / 11, 2);
           if(zoom < 5){
               zoom = 5;
           }
           }
        }
    }

    @Override
    public int getSize() { //returns the number of nodes
        return count;
    }
    
    public void setZoom(int z){
        this.zoom = z;
    }
    
    @Override
    public void setColorStrategy(ColoringStrategy color){
        this.color = color;
    }

    public void clear() { //resets the cystal to nothing
        parts = new ArrayList();
        count = 0;
        parts.add(new CParticle(point2(0, 0), count));
        count = 0;
        zoom = 90;
        radius = 0;
    }

    @Override
    public boolean collides() {
        for (CParticle p : parts) {
            if (p.collides()) {
                this.add(particle());
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {//iterates the nodes and draws each one
        for (CParticle p : parts) {
            p.draw(g);
        }
    }

    /*
     * Singleton static factory
     */
    public static Crystal2D crystal2D() {
        if (instance == null) {
            instance = new Crystal2D();
        }
        return instance;
    }

    @Override
    public String toString() { // toString()
        return "Size: " + count;
    }
}
