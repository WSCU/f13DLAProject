/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package f13dlaproject;

import static f13dlaproject.Particle2D.*;
import static f13dlaproject.Crystal2D.*;
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
        System.out.println("Hello world");
        System.out.println(Math.acos(-1));
        Particle2D p = particle();
        Crystal2D c = crystal2D();
        System.out.println(p);
        p.setVelocity(10);
        p.setA(Math.PI/4);
        //p.setAngle();
        p.move();
        System.out.println(p);
        c.add(p);
        p.reset();
        System.out.println(p);
        System.out.println(c);
        c.clear();
        p.clear();
        System.out.println(p);
        System.out.println(c);
        for(int i = 0; i < 10; i++){
            p.setAngle();
            p.move();
            System.out.println(p);
        }
    }
    
}
