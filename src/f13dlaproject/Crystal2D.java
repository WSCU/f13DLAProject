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
import java.util.List;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

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
    //private ArrayList<CParticle> parts = new ArrayList();
    private List<CParticle2> parts = new CopyOnWriteArrayList();

    /*
     * Declaration of inner CParticle class
     * Be each node in crystal
     */
    private class CParticle2 implements CParticle {

        private Point p; //position of node
        private int num; //number in which it was added to crystal
        private double dist; //distance from center of cystal
        private Color c;
        private CParticle parent;
        
        public CParticle2(Point p, int num, Color c, CParticle parent) { //constructor
            this.p = p.clone(p);
            this.num = num;
            this.dist = Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2));
            this.c = c;
        }

        @Override
        public void draw(Graphics g) { //draw
            g.setColor(c);
            g.fillOval((int) (p.getX() * zoom - zoom / 2) + (DLAFrame.WIDTH / 2) - DLAFrame.dx, (int) (p.getY() * zoom - zoom / 2) + (DLAFrame.HEIGHT / 2) - DLAFrame.dy, (int) zoom, (int) zoom);
        }

        @Override
        public CParticle collides() {
            Particle2D t = particle2D();
            Point pos = t.getPosition();
            double distance = Math.sqrt(Math.pow(p.getX()-pos.getX(),2)+Math.pow(p.getY()-pos.getY(),2));
            if (distance < 1) {
                return this;
            }
            return null;
        }
        
        @Override
        public void setColor(Color c){
            this.c = c;
        }

        @Override
        public Color getColor() {
            return c;
        }

        @Override
        public Point getPos() {
            return p;
        }

        @Override
        public int getNum() {
            return num;
        }

        @Override
        public double getDist() {
            return dist;
        }
    }

    private Crystal2D() { //constructor
        this.count = 1;
        this.radius = 0;
        this.zoom = 20;
        Color[] c = {Color.RED, Color.CYAN, Color.MAGENTA};
        this.color = new RingColor(c);
        parts.add(new CParticle2(point2(0, 0), count, color.chooseColor(0, 0, point2(0,0), null), null));
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
    public void add(Particle p, CParticle parent) {//adds node to crystal
        count++;
        Point po = p.getPosition();
        double dist = po.length();
        CParticle2 part = new CParticle2(po, count, color.chooseColor(dist, count, po, parent), parent);
        parts.add(part);
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
    
    @Override
    public void setZoom(int z){
        this.zoom = z;
    }
    
    @Override
    public void setColorStrategy(ColoringStrategy color){
        this.color = color;
        for(CParticle2 p: parts){
            p.setColor(color.chooseColor(p.dist,p.num,p.p,p.parent));
        }
    }

    @Override
    public void clear() { //resets the cystal to nothing
        parts = new ArrayList();
        count = 0;
        parts.add(new CParticle2(point2(0, 0), count, color.chooseColor(0,0,point2(0,0),null), null));
        count = 0;
        zoom = 90;
        radius = 0;
    }

    @Override
    public boolean collides() {
        for (CParticle2 p : parts) {
            CParticle recent = p.collides();
            if (recent!=null) {
                this.add(particle2D(), recent);
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {//iterates the nodes and draws each one
        Iterator<CParticle2> iterator = parts.iterator();
//        for (CParticle p : parts) {
//            p.draw(g);
//        }
        while (iterator.hasNext()) {
            CParticle p = iterator.next();
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
