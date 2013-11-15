/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;
import f13dlaproject.Crystal2D.*;

/**
 * 
 * An interface to represent a coloring strategy for a crystal 
 * @author outcast
 */
public interface ColoringStrategy {
    
    /**
     * Chooses a color for each crystal node 
     * @param c The crystal node to be colored
     */
    public void chooseColor(CParticle c);
    
}
