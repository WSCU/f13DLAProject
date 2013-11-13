/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package f13dlaproject;

import static f13dlaproject.Particle2D.*;
import static f13dlaproject.Crystal2D.*;
import static f13dlaproject.Testing.*;
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
        Testing t = testing();
        System.out.println(t.testAngle(10000));
    }
    
}
