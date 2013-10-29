/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import static f13dlaproject.Point2.*;
/**
 *
 * @author stu781945
 */
public class Crystal {
    
    private static Crystal instance;
    private int count;
    private ArrayList<CParticle> parts = new ArrayList();
    
    /*
     * Declaration of inner CParticle class
     * Be each node in crystal
     */
    private class CParticle{
        private Point2 p; //position of node
        private int num; //number in which it was added to crystal
        
        public CParticle(Point2 p, int num){ //constructor
            this.p = p.clone(p);
            this.num = num;
        }
        
        public void draw(Graphics g){ //draw
            g.setColor(Color.yellow);
            g.fillOval((int)p.getX(), (int)p.getY(), 1, 1);
        } 
        
    }
    
    private Crystal(){ //constructor
        this.count = 1;
        parts.add(new CParticle(point2(100,100), count));
    }
    
    public void add(Particle p){//adds node to crystal
        count ++;
        CParticle part = new CParticle(p.getPosition(), count);
        parts.add(part);
    }
    
    public int getSize(){ //returns the number of nodes
        return count;
    }
    
    public void clear(){ //resets the cystal to nothing
        parts = new ArrayList();
        count = 0;
    }
    
    public void draw(Graphics g){//iterates the nodes and draws each one
        for(CParticle p: parts){
            p.draw(g);
        }
    }
    
    /*
     * Singleton static factory
     */
    public static Crystal crystal(){
        if(instance == null){
            instance = new Crystal();
        }
        return instance;
    }
    
    @Override
    public String toString(){ // toString()
        return "Size: " + count;
    }
    
}
