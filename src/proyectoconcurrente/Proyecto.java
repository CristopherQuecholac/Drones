
package proyectoconcurrente;


import java.awt.geom.Line2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Proyecto extends javax.swing.JFrame {
    private PanelBall panel;
    private NumeroBall n;
    private int num, op;
    private ArrayList<Ball> drones;
    private ArrayList<Circulo> circulos;
    private ArrayList<Line2D> limites;
    private Line2D mitad;
    private Circulo circulo;
    private BufferedImage img;
    private boolean up, par=false; 
    private Semaphore semaforo;
    private Lock mutex;
    
    public Proyecto() {
        initComponents();
        try{
            img = ImageIO.read(new File ("mapacu.JPG"));
        }catch(IOException e){e.printStackTrace();}
        
        n = new NumeroBall();
        
        drones = new ArrayList<Ball>();        
        circulos = new ArrayList<Circulo>();
        limites = new ArrayList<Line2D>();
        semaforo=new Semaphore(1);
        mutex = new ReentrantLock(true);
        panel = new PanelBall(circulos, img, n, limites); 
        panel.setBounds(0, 0, 560, 560);
        add(panel);
        num=0; op=1;
    }
    void nuevoDrone(){
        if(n.getConta()==0){
            agrega();
        }
        if(n.getConta()==1){
            agrega();
            up=true;
            for(Circulo c: circulos){
                if(up){
                    c.setLim(540, 0, 260, 0);
                    c.setFrame(1, 1, 20, 20);
                    up=false;
                }
                else{
                    c.setLim(540, 0, 540, 270);
                    c.setFrame(1, 271, 20, 20);
                    up=true;
                }   
            }   
        }
        if (n.getConta()>1 && n.getConta()<10){
            int div=540/(num);
            int limup=0,limdown=0, xminup=0, xmaxup=0, xmindown=0, xmaxdown=0, xline=div;
            agrega();
            agrega();
            up=true;
            for(Circulo c: circulos){
                if(up){
                    xminup=limup;
                    limup=limup+div;
                    xmaxup=limup;
                    c.setLim(xmaxup, xminup, 260, 0);
                    c.setFrame(xminup+1, 1, 20, 20);
                    up=false;
                }
                else{
                    xmindown=limdown;
                    limdown=limdown+div;
                    xmaxdown=limdown;
                    c.setLim(xmaxdown, xmindown, 540, 270);
                    c.setFrame(xmindown+1, 271, 20, 20);
                    up=true;
                }  
            }
            if(n.getConta()>=2){
                    //System.out.println("entra");
                    limites.add(new Line2D.Double());
                    for(Line2D lim: limites){
                        lim.setLine(xline, 0, xline, 560);
                        xline=xline+ div;
                    }
                }
            par=true;
        }
        if(par)
        n.setConta(n.getConta()+2);
        else n.setConta(n.getConta()+1);
        num++;
        //System.out.println(n.getConta());
        for(Ball d: drones){
                if(!d.isAlive())
                try{
                d.start();
            }catch(IllegalThreadStateException ex){} 
        }
    }
    void agrega(){
        circulo = new Circulo(540, 0, 540, 0);
        circulo.setFrame(1, 1, 20,20);
        circulos.add(circulo);
        drones.add(new Ball(panel, circulo, op, semaforo, mutex));
    }
    void limpia(){
        n.setConta(0);
        num=0;
        for(Ball b: drones)
            b.detener();
        drones.clear();
        circulos.clear();
        limites.clear();
        par=false;
        panel.bandera= false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu5.setText("jMenu5");

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(545, 610));
        setResizable(false);

        jMenu3.setText("Tipo de algoritmo");

        jMenuItem1.setText("Semaforos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem3.setText("Monitor");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem5.setText("Mutex");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Dron");

        jMenuItem2.setText("Insertar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        nuevoDrone();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        limpia();
        this.op=1;
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        limpia();
        this.op=2;
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        limpia();
        this.op=3;
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proyecto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    // End of variables declaration//GEN-END:variables
  
}
