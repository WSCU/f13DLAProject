/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;

/**
 *
 * @author stu781945
 */
public class StructColor implements ColoringStrategy{
    
    Color[] colors;
    
    public StructColor(Color[] c){
        this.colors = c;
    }
    
    @Override
    public void chooseColor(CParticle c) {
        if(c.getParent() != null){
            
        }
        else{
            c.setColor(Color.BLACK);
        }
    }
    
}
