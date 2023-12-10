
package project.sat.g;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class anemybulet {
    
   private int x,y;     //position of bulet
 Rectangle  rect;
       StartG StartG;  
         
         
    anemybulet(int x,int y){
    this.x=x;
    this.y=y;
  tick();
       
          
    }

    public void tick() {
        
     y-=6;
   
     
        
rect=new Rectangle(x, y, 30,40);        
    }
    public void render(Graphics2D g,BufferedImage bulet){
    g.drawImage(bulet, x, y, 30,40,null);
  //  g.draw(rect);
    }
    
    public int x(){
    return x;
    }
    public int y(){
       return y;
    }
    
}
