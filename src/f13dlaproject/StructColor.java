/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author stu781945
 */
public class StructColor implements ColoringStrategy{
    
    private Color[] colors;
    private Random r = new Random();
    
    public StructColor(Color[] c){
        this.colors = c;
    }
    
    @Override
    public void chooseColor(CParticle c) {
        CParticle parent = c.getParent();
        if(parent != null){
            if(parent.getParent() != null){
                Point p = c.getPos();
                Point pp = parent.getPos();
                if(Math.acos(p.angleDiff(pp)) < Math.PI/4){
                    c.setColor(parent.getColor());
                }
             else{
                c.setColor(colors[r.nextInt(colors.length)]);
            }
                
            }
        }
        else{
            c.setColor(Color.BLACK);
        }
    }
    
}
