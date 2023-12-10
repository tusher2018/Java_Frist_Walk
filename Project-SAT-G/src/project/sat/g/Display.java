/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.sat.g;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {
    
    
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int width,height;
    
     
    public Display(String title,int width,int height){
    this.title=title;
    this.height=height;
    this.width=width;
       
 
    creatDiplay();
    } 
    
    
    public void creatDiplay(){
    
        frame = new JFrame();
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setBounds(0, 0, width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
      
        canvas=new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false);
        
        frame.add(canvas);
        frame.pack();
        
    
    }
    
    public Canvas getCanvas(){
        return canvas;
    
    
    
    
    }
    
    public JFrame getFrame(){
   
        return frame;
    
    }
    
    
    
    
    
    
    
    
    
     
}
