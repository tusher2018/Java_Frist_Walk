/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.sat.g;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;


public class menu {
 StartG StartGame=new StartG("GAME-SAT-1",500,700);
 Display display;
    private  int width,height;
   public String title;
 //  Graphics2D g2d;
   Graphics g;
   private BufferStrategy bs;
   
 
       public menu(String title,int width,int height){
    
    this.width=width;
    this.height=height;
    this.title=title;
       display =new Display(title,width,height);  
       
         bs=display.getCanvas().getBufferStrategy();
    if(bs==null){
    display.getCanvas().createBufferStrategy(3);
    return; 
    }   g=bs.getDrawGraphics();
    
     g.clearRect(0, 0, width, height);
       g.drawRect(0,0, 100,200);
    
    
    
       
          bs.show();
    g.dispose();
     
       
       
   
       display.getFrame().addMouseListener(new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent me) {}

        @Override
        public void mousePressed(MouseEvent me) {
        
        
        }

        @Override
        public void mouseReleased(MouseEvent me) {  }

        @Override
        public void mouseEntered(MouseEvent me) { }

        @Override
        public void mouseExited(MouseEvent me) {       }
    });
       
       
       
       }
   

    
   public void start(){
      StartGame.start();
    }

    
    
    
    
    
    
}
