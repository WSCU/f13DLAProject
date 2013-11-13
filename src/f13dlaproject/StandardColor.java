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
public class StandardColor implements ColoringStrategy {

    private Color[] c;
    private int r,g,b;
    public StandardColor(Color[] c) {
        this.c = c;
    }

    @Override
    public Color chooseColor(double dist) {
        int changeDist =10;
        int m = (int)dist/changeDist % 3;
        double difR,difG,difB;
        
        if (m == 0) {
            
            r = c[0].getRed();
            g = c[0].getGreen();
            b = c[0].getBlue();
            
            difR = c[0].getRed() - c[1].getRed();
            difG = c[0].getGreen() - c[1].getGreen();
            difB = c[0].getBlue() - c[1].getBlue();
            
              
        } else if (m == 1) {
            
            r = c[1].getRed();
            g = c[1].getGreen();
            b = c[1].getBlue();
            
            difR = c[1].getRed() - c[2].getRed();
            difG = c[1].getGreen() - c[2].getGreen();
            difB = c[1].getBlue() - c[2].getBlue();
                      
        } else {
            r = c[2].getRed();
            g = c[2].getGreen();
            b = c[2].getBlue();
            
            difR = c[2].getRed() - c[0].getRed();
            difG = c[2].getGreen() - c[0].getGreen();
            difB = c[2].getBlue() - c[0].getBlue();
                       
        }    
        
            double per = ((int)dist%changeDist)/changeDist;
            
            double dR = difR*per;
            double dG = difG*per;
            double dB = difB*per;
            
            r += dR;
            g += dG;
            b += dB; 
        return new Color(r, g, b);
    }
}
