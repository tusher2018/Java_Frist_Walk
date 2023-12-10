
package project.sat.g;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class StartG implements Runnable  {
    
      private Display display;
    private  int width,height;
    Thread thread;
     private boolean running =false;
    public String title;
     private BufferedImage Bodyimage,playerimage1,playerimage2;
      private BufferStrategy bs;
       private Graphics g;
       private Graphics2D g2d;
       int BodyimageStartx=0,BodyimageStarty=0;
  int playerimageStartx;
  int playerimageStarty;
       int passtime=0;
       ArrayList<anemybulet> anemybulet1;
       ArrayList<mybulet> mybulet1;
       ArrayList<sideanemy> sideanemy1;
       ArrayList<mysidefire> mysidefire;
      
       private BufferedImage bulet,mybulet;
       Rectangle playerpositionrect;
       
       


       
       
    public void run() {
     
    display =new Display(title,width,height);  
          try {
              init();
          } catch (IOException ex) {
              Logger.getLogger(StartG.class.getName()).log(Level.SEVERE, null, ex);
          }
     
     int fps=60;
     double timepertick=1000000000 /fps;
     double delta=0;
     long now;
     long lasttime =System.nanoTime();
     long timer=0;
     int tricks=0;
       
     
    
     
     
    while(running){
        now=System.nanoTime();
        delta += (now-lasttime)/timepertick;
     timer += now-lasttime ;
        
        lasttime=now;
        
        
   if(delta >=1){     
    tick();
    render();
   tricks++ ;
   delta--;
   
   }   if(timer>=1000000000){
      // System.out.println("tricks and  :"+tricks);
       tricks=0;
       timer=0;
           
   } 
    }
    
    stop();
    
    }
    public synchronized void start(){
        if(running)
           return;
        running=true;
     thread = new Thread(  this);
    thread.start();
  }
    public synchronized void stop(){
     if(!running)
            return;
        
        running=false;
        try{
             thread.join();
        }catch(InterruptedException e){
        
        e.printStackTrace();
         
    
    }
    
    

    }
    public StartG(String title,int width,int height){
    
    this.width=width;
    this.height=height;
    this.title=title;
    }
    
    
    public void init() throws IOException {
  
    
      Bodyimage=imageLoader.LoadImage("LAND.png");
      playerimage1=imageLoader.LoadImage("player1.png");
      playerimage2=imageLoader.LoadImage("player2.png");
      bulet=imageLoader.LoadImage("bulet.png");
      mybulet=imageLoader.LoadImage("mybullet.png");
        
  display.getFrame().addKeyListener(new KeyListener(){
          @Override
          public void keyTyped(KeyEvent ke) {
         }@Override
          public void keyReleased(KeyEvent ke) {
           if((ke.getKeyCode()==KeyEvent.VK_ENTER)){

       mysidegunfire=false;
                         }  
          
          } 
          @Override
          public void keyPressed(KeyEvent ke) {
           if(rungame){ if((ke.getKeyCode()==KeyEvent.VK_RIGHT)){
      if(playerpositionrect.getX()<=(display.getFrame().getWidth()/500)*400){ changeplayerx=changeplayerx+display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10;
      }
            }  if((ke.getKeyCode()==KeyEvent.VK_LEFT)){
if(playerpositionrect.getX()>0){
         changeplayerx=changeplayerx-display.getFrame().getWidth()/4-(display.getFrame().getWidth()/500)*10;               }
            }
          }          
            if((ke.getKeyCode()==KeyEvent.VK_ENTER)){
if(havingbulet>0){
       mysidegunfire=true;
       havingbulet--;
}    }        
            if(!rungame){
    if(ke.getKeyCode()==KeyEvent.VK_W){
    
 rungame=true;
 playerhelth=100;
 havingbulet=0;
 passtime=0;
                
}}       
          } });
 
  anemybulet1=new ArrayList<anemybulet>(); 
  mybulet1=new ArrayList<mybulet>(); 
  sideanemy1=new ArrayList<sideanemy>(); 
  mysidefire=new ArrayList<mysidefire>(); 
  
  display.getCanvas().addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me) {
        int x= me.getX();
        int y= me.getY();
        if(x>=(display.getFrame().getWidth()/500)*150 && x<=((display.getFrame().getWidth()/500)*150)+((display.getFrame().getWidth()/500)*250) && y>=(display.getFrame().getHeight()/700)*120 && y<=((display.getFrame().getHeight()/700)*120)+(display.getFrame().getHeight()/700)*100){
        rungame=true;
        }
    
            if(!rungame){if(x>=(display.getFrame().getWidth()/2-50) && x<= ((display.getFrame().getWidth()/2)-50+140) && y>=((display.getFrame().getHeight()/2)+80) && y<=((display.getFrame().getHeight()/2)+80+50)){
 
 playerhelth=100;
 havingbulet=0;
 passtime=0;
 kill=0;
 playerimageStartx=(((display.getFrame().getWidth())/4));
 playerimageStarty=((display.getFrame().getHeight())/(7/2));
 
 for(int i=0;i<anemybulet1.size();i++){anemybulet1.removeAll(anemybulet1);}
 for(int i=0;i<mybulet1.size();i++){mybulet1.removeAll(mybulet1);}
 for(int i=0;i<sideanemy1.size();i++){sideanemy1.removeAll(sideanemy1);}
 for(int i=0;i<mysidefire.size();i++){mysidefire.removeAll(mysidefire);}
 g.clearRect(0, 0,width,height);
 rungame=true;
 }}     }});
  

  }

    int rendomstock;
    Random rendom=new Random();
    
    public void render() {
           bs=display.getCanvas().getBufferStrategy();
    if(bs==null){
    display.getCanvas().createBufferStrategy(3);
    return; 
    }
     g=bs.getDrawGraphics();
      g2d=(Graphics2D) bs.getDrawGraphics();
         
    g2d.clearRect(0, 0, width, height);
    g2d.setColor(Color.pink);
    g2d.fillRect(0, 0, width, height);
    g2d.setColor(Color.yellow);
    g2d.fillRect((display.getFrame().getWidth()/500)*150, (display.getFrame().getHeight()/700)*120,(display.getFrame().getWidth()/500)*250,(display.getFrame().getHeight()/700)*100);
    g2d.setColor(Color.BLACK);
    Font font2= new Font("arial",Font.BOLD,40);
    g2d.setFont(font2);
    g2d.drawString("Start Game", display.getFrame().getWidth()/3, display.getFrame().getHeight()/4);
    
    
    
    if(rungame){
         g2d.clearRect(0, 0, width, height);
    for(int i=0;i<1000;i++){
     g2d.drawImage(Bodyimage, BodyimageStartx, BodyimageStarty-display.getFrame().getHeight()*i, display.getFrame().getWidth(), display.getFrame().getHeight(), null);
  }
    
        playerimageStartx=(((display.getFrame().getWidth())/4));
        playerimageStarty=((display.getFrame().getHeight())/(7/2));
   
    

     for(int i=0;i<mybulet1.size();i++){mybulet1.get(i).render(g2d,mybulet,bulet);} 
     for(int i=0;i<sideanemy1.size();i++){sideanemy1.get(i).render(g2d,playerimage1);} 
     for(int i=0;i<mysidefire.size();i++){mysidefire.get(i).render(g2d,mybulet);} 

    
      if(passtime%50<=25){
      g.drawImage(playerimage1,playerimageStartx+changeplayerx,playerimageStarty,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }else{
      g.drawImage(playerimage2,playerimageStartx+changeplayerx,playerimageStarty,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }
      
      if(passtime>300) for(int i=0;i<anemybulet1.size();i++){anemybulet1.get(i).render(g2d,bulet);} 
        
    //anemy............................start
    
     if(passtime%50<=25){
      g.drawImage(playerimage1,playerimageStartx,display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }else{
      g.drawImage(playerimage2,playerimageStartx,display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }
      if(passtime%50<=25){
      g.drawImage(playerimage1,playerimageStartx-display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10,display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }else{
      g.drawImage(playerimage2,playerimageStartx-display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10,display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }
        if(passtime%50<=25){
      g.drawImage(playerimage1,playerimageStartx-2*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }else{
      g.drawImage(playerimage2,playerimageStartx-2*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }
         if(passtime%50<=25){
      g.drawImage(playerimage1,playerimageStartx+(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }else{
      g.drawImage(playerimage2,playerimageStartx+(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }
         if(passtime%50<=25){
      g.drawImage(playerimage1,playerimageStartx+2*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }else{
      g.drawImage(playerimage2,playerimageStartx+2*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }
            if(passtime%50<=25){
      g.drawImage(playerimage1,playerimageStartx+3*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }else{
      g.drawImage(playerimage2,playerimageStartx+3*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150,(display.getFrame().getWidth()/500)*80,(display.getFrame().getHeight()/700)*150,null);
    }
      //anemy..............end
      
   
   
   
   Font font= new Font("arial",Font.BOLD,17);
   g2d.setFont(font);
   
     g2d.drawString("Health : "+playerhelth,0,20);
     g2d.drawString("Bulet :"+havingbulet, display.getFrame().getWidth()-120, 20);
     g2d.drawString("Score :"+score, display.getFrame().getWidth()/3-30, 20);
     g2d.drawString("kill :"+kill, display.getFrame().getWidth()/2+30, 20);
     
    }
   
    if(playerhelth<=0){
        rungame=false;
        int lastscore=score+(kill*100);
                  g2d.clearRect(0, 0, display.getFrame().getWidth(), display.getFrame().getHeight());
         Font font1= new Font("arial",Font.BOLD,20);
        g2d.setFont(font1);
       g2d.drawString("GAME OVER", display.getFrame().getWidth()/2-50, display.getFrame().getHeight()/2-50);
      g2d.drawString("Score :"+lastscore, display.getFrame().getWidth()/2-50, display.getFrame().getHeight()/2-80);
  g2d.drawRect(display.getFrame().getWidth()/2-50, display.getFrame().getHeight()/2+80,140,50);
    g2d.drawString("play again", display.getFrame().getWidth()/2-50+15, display.getFrame().getHeight()/2+110);
      }
    
    bs.show();
    g.dispose();
    
    }
  int changeplayerx=0;
    public void tick() {
        if(rungame){
    
  BodyimageStarty+=5;
  passtime++;
  
 if(playerhelth>0) score=passtime/2;
 
     for(int i=1;i<2;i++){
rendomstock=rendom.nextInt(651);
}
if(rendomstock==0){fire1=true;}else{fire1=false;}
if(rendomstock==100){fire2=true;}else{fire2=false;}
if(rendomstock==200){fire3=true;}else{fire3=false;}
if(rendomstock==300){fire4=true;}else{fire4=false;}
if(rendomstock==400){fire5=true;}else{fire5=false;}
if(rendomstock==500){fire6=true;}else{fire6=false;}
    
if(rendomstock==10){myfire1=true;} else{myfire1=false;}   
if(rendomstock==20){myfire2=true;} else{myfire2=false;}   
if(rendomstock==30){myfire3=true;} else{myfire3=false;}   
if(rendomstock==40){myfire4=true;} else{myfire4=false;}   
if(rendomstock==50){myfire5=true;} else{myfire5=false;}   
if(rendomstock==60){myfire6=true;} else{myfire6=false;} 

if (rendomstock==50){allsideanemy1=true;}else{allsideanemy1=false;}
if (rendomstock==150){allsideanemy2=true;}else{allsideanemy2=false;}
if (rendomstock==350){allsideanemy3=true;}else{allsideanemy3=false;}
if (rendomstock==250){allsideanemy4=true;}else{allsideanemy4=false;}
if (rendomstock==450){allsideanemy5=true;}else{allsideanemy5=false;}
if (rendomstock==650){allsideanemy6=true;}else{allsideanemy6=false;}
    
    for(int i= 0;i<anemybulet1.size();i++){ anemybulet1.get(i).tick();  }
    for(int i= 0;i<mybulet1.size();i++){ mybulet1.get(i).tick();  }
    for(int i=0;i<sideanemy1.size();i++){sideanemy1.get(i).tick(); } 
    for(int i=0;i<mysidefire.size();i++){mysidefire.get(i).tick(); } 


if(fire1){   anemybulet1.add(new anemybulet((playerimageStartx+(display.getFrame().getWidth()/500)*25),display.getFrame().getHeight()-150));}
if(fire2){   anemybulet1.add(new anemybulet((playerimageStartx+(display.getFrame().getWidth()/500)*25)+1*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150));}
if(fire3){   anemybulet1.add(new anemybulet((playerimageStartx+(display.getFrame().getWidth()/500)*25)+2*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150));}
if(fire4){   anemybulet1.add(new anemybulet((playerimageStartx+(display.getFrame().getWidth()/500)*25)+3*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150));}
if(fire5){   anemybulet1.add(new anemybulet((playerimageStartx+(display.getFrame().getWidth()/500)*25)-1*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150));}
if(fire6){   anemybulet1.add(new anemybulet((playerimageStartx+(display.getFrame().getWidth()/500)*25)-2*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),display.getFrame().getHeight()-(display.getFrame().getHeight()/700)*150));}
   
if(myfire1){ mybulet1.add(new mybulet((playerimageStartx+(display.getFrame().getWidth()/500)*25)-0*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),(display.getFrame().getHeight()/700)*(-500))); }
if(myfire2){ mybulet1.add(new mybulet((playerimageStartx+(display.getFrame().getWidth()/500)*25)+1*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),(display.getFrame().getHeight()/700)*(-50))); }
if(myfire3){ mybulet1.add(new mybulet((playerimageStartx+(display.getFrame().getWidth()/500)*25)+2*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),(display.getFrame().getHeight()/700)*(-500))); }
if(myfire4){ mybulet1.add(new mybulet((playerimageStartx+(display.getFrame().getWidth()/500)*25)+3*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),(display.getFrame().getHeight()/700)*(-700))); }
if(myfire5){ mybulet1.add(new mybulet((playerimageStartx+(display.getFrame().getWidth()/500)*25)-1*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),(display.getFrame().getHeight()/700)*(-500))); }
if(myfire6){ mybulet1.add(new mybulet((playerimageStartx+(display.getFrame().getWidth()/500)*25)-2*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),(display.getFrame().getHeight()/700)*(-1000))); }


if(allsideanemy1){ sideanemy1.add(new sideanemy((playerimageStartx+(display.getFrame().getWidth()/500)*0)-0*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),(display.getFrame().getHeight()/700)*(-500))); }
if(allsideanemy2){ sideanemy1.add(new sideanemy((playerimageStartx+(display.getFrame().getWidth()/500)*0)+1*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),(display.getFrame().getHeight()/700)*(-50))); }
if(allsideanemy3){ sideanemy1.add(new sideanemy((playerimageStartx+(display.getFrame().getWidth()/500)*0)+2*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),(display.getFrame().getHeight()/700)*(-500))); }
if(allsideanemy4){ sideanemy1.add(new sideanemy((playerimageStartx+(display.getFrame().getWidth()/500)*0)+3*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),(display.getFrame().getHeight()/700)*(-700))); }
if(allsideanemy5){ sideanemy1.add(new sideanemy((playerimageStartx+(display.getFrame().getWidth()/500)*0)-1*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),(display.getFrame().getHeight()/700)*(-500))); }
if(allsideanemy6){ sideanemy1.add(new sideanemy((playerimageStartx+(display.getFrame().getWidth()/500)*0)-2*(display.getFrame().getWidth()/4+(display.getFrame().getWidth()/500)*10),(display.getFrame().getHeight()/700)*(-1000))); }


if(mysidegunfire){mysidefire.add(new mysidefire(playerimageStartx+changeplayerx+32,playerimageStarty+40));}
  
playerpositionrect=new Rectangle(playerimageStartx+changeplayerx,playerimageStarty,80,150);
      
      
      for(int i=0;i<anemybulet1.size();i++){
      int ex=anemybulet1.get(i).x();
      int ey=anemybulet1.get(i).y();
     int px=(int) playerpositionrect.getX();
     int py=(int) playerpositionrect.getY();
      if ( px < ex + 30 && px + 80 > ex && py < ey + 30 && py + 80 > ey ){
          anemybulet1.remove( i ); i -- ;
      
  if(playerhelth>0){playerhelth-=20;}
  else{  }
  }   }
     
      for(int i=0;i<mybulet1.size();i++){
      int mx=mybulet1.get(i).x();
      int my=mybulet1.get(i).y();
      int px=(int) playerpositionrect.getX();
      int py=(int) playerpositionrect.getY();
      if ( px < mx + 30 && px + 80 > mx && py < my + 30 && py + 80 > my ){
          mybulet1.remove( i ); i -- ;      havingbulet+=5;
      }
      
      }
      for(int i=0;i<mysidefire.size();i++){     
      int px=mysidefire.get(i).x();
      int py=mysidefire.get(i).y();
    
      for(int j=0;j<sideanemy1.size();j++){
                   int sax,say;
     sax=sideanemy1.get(j).x();
     say=sideanemy1.get(j).y();
             
     if (px<sax+80 && px>sax && py>say && py<say+50){gun=true;
          sideanemy1.remove( j );        
          j-- ;  kill++;    
        }}}    
      if(gun){for (int i=0;i<mysidefire.size();i++){
           mysidefire.remove( i );      
           i--; 
         }}
      gun=false;
    
   }}
    /*public void mysidefire(){
      for(int i=0;i<sideanemy1.size();i++){
      int sax=sideanemy1.get(i).x();
      int say=sideanemy1.get(i).y();
      int px=(int) playerpositionrect.getX();
      int py=(int) playerpositionrect.getY();
      int dalx=px-sax;
      int daly=py-say;
      int plasx;
      int plasy;
      plasx=dalx/5;
      plasy=daly/5;
      px+=plasx;
      py+=plasy;
       }   } */
    boolean gun=false;
   boolean mysidegunfire=false;
   boolean allsideanemy1=false,allsideanemy2=false,allsideanemy3=false,allsideanemy4=false,allsideanemy5=false,allsideanemy6=false;
    boolean rungame=false;
  int playerhelth=100,havingbulet=0;
  int score=0,kill=0;
    int playerx,playery,anemybuletx,anemybulety;
    boolean fire1=false, fire2=false, fire3=false, fire4=false, fire5=false, fire6=false;
    boolean myfire1,myfire2,myfire3,myfire4,myfire5,myfire6=false;


}
