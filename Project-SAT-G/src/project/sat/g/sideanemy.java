
package project.sat.g;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class sideanemy {
    private int x,y;     //position of bulet
 Rectangle  rect;
       StartG StartG;  
         
         
    sideanemy(int x,int y){
    this.x=x;
    this.y=y;
  tick();
       
          
    }

    public void tick() {
        
     y++;
   
     
        
rect=new Rectangle(x, y, 80,100);        
    }
    public void render(Graphics2D g,BufferedImage bulet){
    g.drawImage(bulet, x, y, 80,100,null);
   // g.draw(rect);
    }
    
    public int x(){
    return x;
    }
    public int y(){
       return y;
    }
    
}
    
    
    

