/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;

/**
 *
 * @author outcast
 */
public class StandardTimeColor implements ColoringStrategy {

    private Color[] c;
    
    public StandardTimeColor(Color[] c) {
        this.c = c;
    }

    @Override
    public Color chooseColor(double dist, int num, Point p, CParticle parent) {
        int changeDist = 500;
        int m = num/changeDist % 3;
        float difR,difG,difB;
        float[] color = new float[3];
        float[] tocolor = new float[3];
        
        if (m == c.length-1) {
            
            c[m].getColorComponents(color);
            c[0].getColorComponents(tocolor);
                       
            difR = color[0] - tocolor[0];
            difG = color[1] - tocolor[1];
            difB = color[2] - tocolor[2];
                      
        } else {
            
            c[m].getColorComponents(color);
            c[m+1].getColorComponents(tocolor);
                       
            difR = color[0] - tocolor[0];
            difG = color[1] - tocolor[1];
            difB = color[2] - tocolor[2];
                       
        }    
        
            float per = (1.0f*(num%changeDist))/changeDist;
            
            float dR = difR*per;
            float dG = difG*per;
            float dB = difB*per;
            
            float r = color[0] - dR;
            float g = color[1] - dG;
            float b = color[2] - dB; 
            
            
        return new Color(r, g, b);
    }
}

