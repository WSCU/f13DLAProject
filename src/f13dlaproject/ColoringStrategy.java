/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;
import f13dlaproject.Crystal2D.*;

/**
 *
 * @author outcast
 */
public interface ColoringStrategy {
    
    public Color chooseColor(double dist, int num, Point p, CParticle parent);
    
}
