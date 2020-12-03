package proyectoconcurrente;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;


public class Ball extends Thread{
    private PanelBall panel;
    private Circulo dron;
    private Semaphore a;
    private boolean ennable=true;
    private int op;
    private int pasox =2;
    private int pasoy =1; 
    
    
    Ball(PanelBall panel, Circulo dron, int op, Semaphore a){
        this.panel = panel;
        this.dron=dron;
        this.op=op;
        this.a= a;
    }
     
    public void run(){
        
        while(true){
            try{
               switch(op){
                   case 1: a.acquire();
                           rc();
                           a.release();
                           break;
                   default: break;
                         
               }
               panel.repaint();
               Thread.sleep((int)(15+Math.random()));
               if(!ennable){
                   this.join();
               }
            }catch(Exception e){e.printStackTrace(); System.out.println("uno");}
        }
    }
    
    
    void rc(){
       if(dron.getX()>=dron.getLimXmax()-20 || dron.getX()<=dron.getLimXmin()){
                    pasox=pasox*(-1);  
                }
       if(dron.getY()>=dron.getLimYmax() || dron.getY()<=dron.getLimYmin()){
                    pasoy=pasoy*(-1);  
       }
       dron.setFrame(dron.getX()+pasox, dron.getY()+pasoy, 20, 20);
    }
    
    void detener(){
        this.ennable=false;
    }
}
