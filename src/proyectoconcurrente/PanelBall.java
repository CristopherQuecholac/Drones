package proyectoconcurrente;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.*;
import java.util.ArrayList;

public class PanelBall extends JPanel{
     private BufferedImage img;
     private ArrayList<Circulo> circulos;
     private ArrayList<Line2D> limites;
     private NumeroBall n;
     public boolean bandera=false;
     
     public PanelBall(ArrayList<Circulo> circulos, BufferedImage img, NumeroBall n, ArrayList<Line2D> limites){        
         this.circulos = circulos;
         this.img=img;
         this.n=n;
         this.limites=limites;    
    }
     public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        Line2D mitad = new Line2D.Double();
        mitad.setLine(0, 280, 560, 280);
        g2.drawImage(img, 0, 0, 540, 560, null);
        g2.setColor(Color.red);
        for(Circulo circulo : circulos){
            g2.fill(circulo);
            //g2.drawString(String.valueOf(n.getConta()), WIDTH, WIDTH);
        }
        g2.setColor(Color.black);
        if(n.getConta()==2 || bandera){
            g2.draw(mitad);
            bandera=true;
        }
        for(Line2D lim : limites){
            g2.draw(lim);
        }    
     }  
}

