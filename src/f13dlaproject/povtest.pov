 1 #include "colors.inc"
  2 #include "textures.inc"
  3 
  4 camera {
  5     location  <-4, 3, -9>
  6     look_at   <0, 0, 0>
  7     angle 48
  8 }
  9 
 10   plane {
 11     y, -1
 12     texture {
 13       pigment {
 14         checker
 15         color rgb<0.5, 0, 0>
 16         color rgb<0, 0.5, 0.5>
 17       }
 18       finish {
 19         diffuse 0.4
 20         ambient 0.2
 21         phong 1
 22         phong_size 100
 23         reflection 0.25
 24       }
 25     }
 26   }
 27   torus {
 28     1.5, 0.5
 29     texture { Brown_Agate }
 30     rotate <90, 160, 0>
 31     translate <-1, 1, 3>
 32   }
 33   box {
 34     <-1, -1, -1>, <1, 1, 1>
 35     texture { DMFLightOak }
 36     translate <2, 0, 2.3>
 37   }
 38   cone {
 39     <0,1,0>, 0, <0,0,0>, 1
 40     texture { PinkAlabaster }
 41     scale <1, 3, 1>
 42     translate <-2, -1, -1>
 43   }
 44   sphere {
 45     <0,0,0>,1
 46     texture { Sapphire_Agate }
 47     translate <1.5, 0, -2>
 48   }
 49 
 50  light_source {
 51     <2, 10, -3>
 52     color White
 53   }
 54 
~              