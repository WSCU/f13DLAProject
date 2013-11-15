/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import static f13dlaproject.Point3.*;
import static f13dlaproject.Particle3D.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A class to represent a crystal in a 3D environment. Implements from {@link Crystal}
 * @author stu738510
 */
public class Crystal3D implements Crystal{
        /**
 * A unique crystal 
 */
    private static Crystal3D instance;
         /**
 * The number of nodes in a crystal 
 */ 
    private int count;
        /**
 * The radius of the crystal 
 */
    private double radius;
         /**
 * zoom factor to make the crystal fit to the window
 */
    private double zoom;
        /**
 * A coloring strategy for the crystal
 */
    private ColoringStrategy color;
        /**
 * An ArrayList of CParticles that make up the crystal 
 */
    private List<CParticle3> parts = new CopyOnWriteArrayList();
      /**
 * Most recent CParticle 
 */  
    private CParticle recent;
    
    
    /**
 * Constructor for a 3D crystal 
 * Instantiates the count, radius, zoom, color array, coloring strategy, CParticle
 */ 
    private Crystal3D(){
        this.count=1;
        this.radius=1;
        this.zoom = 0;
        Color [] c = {Color.RED,Color.BLUE,Color.BLACK};
        this.color= new StandardColor(c);
        parts.add(new CParticle3(point3(0,0,0),1,null));
    
    }
    @Override
    public double getRadius() {
       return this.radius;
    }

    @Override
    public double getZoom() {
        return this.zoom;
    }

    @Override
    public void add(Particle p, f13dlaproject.CParticle parent) {
        count++;
        CParticle3 part = new CParticle3(p.getPosition(), count, parent);
        parts.add(part);
        double dist = part.p.length();
        if (dist > radius) {
            radius = dist;
        }
    }

    @Override
    public int getSize() {
        return this.count;
    }

    @Override
    public boolean collides() {
       for(CParticle3 partic: parts){
           CParticle recent = partic.collides();
           if(recent != null){
               this.add(particle3D(), recent);
               return true;
           }
       }
       return false;
    }

    @Override
    public void setColorStrategy(ColoringStrategy Color) {
        this.color=Color;
    }

    @Override
    public void draw(Graphics g) {
        for(CParticle partic: parts){
            partic.draw(g);
        }
    }
    
 /**
 * Singleton static factory that generates a unique 3D crystal 
 * @return unique instance of a 3D crystal 
 */
    public static Crystal3D crystal3D(){
        if(instance == null){instance = new Crystal3D(); }
        return instance;
    }

    @Override
    public void setZoom(int z) {
        this.zoom = z;
    }

    @Override
    public void clear() {
        this.count=1;
        this.radius=1;
        this.zoom = 0;
        Color [] c = {Color.RED,Color.BLUE,Color.BLACK};
        this.color= new StandardColor(c);
    }

    private static class CParticle3 implements CParticle {
        private Point p; //position of node
        private int num; //number in which it was added to crystal
        private double dist; //distance from center of cystal
        private Color c;
        CParticle parent;

        public CParticle3(Point p, int i, CParticle  parent) {
            this.p = p.clone(p);
            this.num = i;
            this.parent = parent;
        }
        
        @Override
        public CParticle collides(){
             Particle3D t = particle3D();
            Point pos = t.getPosition();
            double l = Math.sqrt(Math.pow(p.getX() - pos.getX(), 2) + Math.pow(p.getY() - pos.getY(), 2)+ Math.pow(p.getZ() - pos.getZ(), 2));
            if (l < 1) {
                return this;
            }
            return null;
        }
        @Override
        public void draw(Graphics g){
              
        }

        @Override
        public void setColor(Color c) {
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

        @Override
        public CParticle getParent() {
            return this.parent;
        }
    }
    
}
