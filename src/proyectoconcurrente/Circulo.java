/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconcurrente;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
/**
 *
 * @author LAP2019
 */
public class Circulo extends Ellipse2D{
    private double x, y;
    private int limXmax;
    private int limXmin;
    private int limYmax;
    private int limYmin;

    public Circulo(int limXmax, int limXmin, int limYmax, int limYmin) {
        this.limXmax = limXmax;
        this.limXmin = limXmin;
        this.limYmax = limYmax;
        this.limYmin = limYmin;
    }
    public void setLim(int limXmax, int limXmin, int limYmax, int limYmin) {
        this.limXmax = limXmax;
        this.limXmin = limXmin;
        this.limYmax = limYmax;
        this.limYmin = limYmin;
    }
    public int getLimXmax() {
        return limXmax;
    }
    public int getLimXmin() {
        return limXmin;
    }
    public int getLimYmax() {
        return limYmax;
    }
    public int getLimYmin() {
        return limYmin;
    }
    @Override
    public double getX() {
        return x;
    }
    @Override
    public double getY() {
        return y;
    }
    @Override
    public double getWidth() {
        return 20;
    }
    @Override
    public double getHeight() {
        return 20;
    }
    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void setFrame(double x, double y, double w, double h) {
        this.x=x;
        this.y=y;
    }
    @Override
    public Rectangle2D getBounds2D() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}