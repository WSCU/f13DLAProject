/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package f13dlaproject;

import static f13dlaproject.Particle2D.*;
import static f13dlaproject.Crystal2D.*;
import static f13dlaproject.Testing.*;
import static f13dlaproject.OutputSVG.*;
import static f13dlaproject.Crystal2D.*;
import static f13dlaproject.DLAFrame.display;
import static f13dlaproject.DLAFrame.paused;
import static f13dlaproject.DLAFrame.time;
import static f13dlaproject.Particle2D.*;
/**
 *
 * @author jiig
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Testing t = testing();
//        System.out.println(t.testAngle(10000));
//          Particle2D p = particle2D();
//          Crystal2D c = crystal2D();
//          int count=0, stop=1000000;
//          while (count<stop) {
//                        p.setAngle();
//                        p.move();
//          }          
          outputSVG().toFile(null);
       
    }
    
}
