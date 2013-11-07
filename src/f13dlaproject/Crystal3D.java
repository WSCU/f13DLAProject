/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import static f13dlaproject.Point3.*;

/**
 *
 * @author stu738510
 */
public class Crystal3D implements Crystal{
    private static Crystal3D instance;
    private int count;
    private double radius;
    private double zoom;
    private ColoringStrategy color;
    private ArrayList<CParticle> parts = new ArrayList();
    
    
    
    private Crystal3D(){
        this.count=1;
        this.radius=1;
        this.zoom = 0;
        Color [] c = {Color.RED,Color.BLUE,Color.BLACK};
        this.color= new StandardColor(c);
    
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
    public void add(Particle p) {
        count++;
        CParticle part = new CParticle(p.getPosition(), count);
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
       for(CParticle partic: parts){
           if(partic.collides()){return true;}
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
    
    public static Crystal3D crystal3D(){
        if(instance == null){instance = new Crystal3D(); }
        return instance;
    }

    private static class CParticle {
        private Point p; //position of node
        private int num; //number in which it was added to crystal
        private double dist; //distance from center of cystal

        public CParticle(Point point, int i) {
        }
        
        public boolean collides(){
            return true;
        }
        public void draw(Graphics g){
            
        }
    }
    
}
