/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;
import static f13dlaproject.Particle2D.*;
/**
 *
 * @author stu452786
 */
public class Testing {
    public Particle2D p ;
    private static Testing uniqueInstance;
    
    private Testing(){
        p = particle2D();
    }

    
    public String testAngle(int stop){
        double bounds = .5;
        p.setA(bounds);
        
        double oang = p.getAngle();
        double difAng;
        double ang;
        int counter=0;
        for(;counter<stop; counter++){
            p.setAngle();
           ang = p.getAngle();
           difAng = Math.abs(ang-oang);
            System.out.println(difAng + ", counter = " + counter);
          
           if (difAng > bounds){
               if(difAng < (2*Math.PI)-bounds){
               System.out.println((2*Math.PI)-bounds);
               return "Angle out of bounds";
           }
           }
           oang=ang;
          
        }
        return "Angle is working";
    }
    
     public static Testing testing() {
         if (uniqueInstance == null) {
            uniqueInstance = new Testing();
        }
        return uniqueInstance;
    }
    
}
