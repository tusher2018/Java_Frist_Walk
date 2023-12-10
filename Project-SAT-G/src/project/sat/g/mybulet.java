/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.sat.g;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author use
 */
public class mybulet {
 


  private  int x,y;     //position of bulet
 Rectangle  rect;
    
    mybulet(int x,int y){
    this.x=x;
    this.y=y;

    }

    public void tick() {
        
        y+=4;
        rect=new Rectangle( x, y, 40,70);
    }
    public void render(Graphics2D g,BufferedImage mybulet,BufferedImage mybulet2){
   
          g.drawImage(mybulet, x, y, 40,70,null);
        g.drawImage(mybulet2, x+10, y+10, 20,50,null);
    //  g.draw(rect);
    
    }
    
  public int x(){
    return x;
    }
    public int y(){
       return y;
    }




    
}