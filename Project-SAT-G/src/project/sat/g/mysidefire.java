
package project.sat.g;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class mysidefire {
    
     private int x,y;     //position of bulet
 Rectangle  rect;

       StartG StartG;  
     
  
         
    mysidefire(int px,int py){
    this.x=px;
   this.y=py;
  tick();
       
          
    }


    public void render(Graphics2D g,BufferedImage bulet){
    g.drawImage(bulet,x ,y , 15,20,null);
   // g.draw(rect);
    }
    
    public int x(){
    return x;
    }
    public int y(){
      return y;
    }
    
   
       public void tick(){
      
     
         y--;
           
         
      rect=new Rectangle(x, y, 8,10); 
        
  
      }
    
}
    

