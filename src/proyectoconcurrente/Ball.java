package proyectoconcurrente;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


public class Ball extends Thread{
    private PanelBall panel;
    private Circulo dron;
    private Semaphore a;
    private boolean ennable=true;
    private int op;
    private int pasox =5;
    private int pasoy =3; 
    
    private Lock mutex;
    private Lock vCondicion;
    private boolean vc;
    private Condition condicion;
    
    Ball(PanelBall panel, Circulo dron, int op, Semaphore a,Lock mutex){
        this.panel = panel;
        this.dron=dron;
        this.op=op;
        this.a= a;
        this.mutex=mutex;
    }
     
    public void run(){
        
        while(true){
            
               switch(op){
                   case 1: 
                            try{    
                            a.acquire();
                                rc();
                                System.out.println("1");
                            a.release();            
                            }catch(Exception e){}
                            break;
                   
                   case 2: synchronized(panel){
                            rc();
                            System.out.println("2");
                            }
                            break;
                   
                   case 3: mutex.lock();
                            rc();
                           mutex.unlock();
                   
                   case 4: mutex.lock();
                           try{
                            condicion.signal();
                            rc();
                           }catch(Exception e){} 
                           finally{
                               mutex.unlock();
                           }
                           break;
                   
                   default: break;         
               }
               
               
               try{
               Thread.sleep((int)(Math.random()*100));
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
       panel.repaint();
    }
    
    void detener(){
        this.ennable=false;
    }
}
