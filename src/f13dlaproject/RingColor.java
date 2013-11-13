/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;

/**
 *
 * @author stu473293
 */
public class RingColor implements ColoringStrategy{
    
    private Color[] colors;
    
    public RingColor(Color[] c) {
        this.colors = c;
    }

    @Override
    public void chooseColor(CParticle c) {
        double dist = c.getDist();
        int changeDist = 10;
        int m = (int)dist/changeDist % colors.length;
        c.setColor(colors[m]);
    }

    
}
