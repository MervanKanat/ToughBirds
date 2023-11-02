package tasarim.karakterler;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Entity {

    protected float hiz;
    protected String gorselYolu;
    protected Image gorsel;
    protected int gorsel_genislik;
    protected int gorsel_yukseklik;
    protected boolean gorunum = true;
    protected Rectangle kutu;
    protected double aci;

    //Konum  ve  Boyut : 
    public Entity(int x, int y, int genislik, int yukseklik) {
        kutu = new Rectangle(x, y, genislik, yukseklik);
        gorsel_genislik = genislik;
        gorsel_yukseklik = yukseklik;
    }

    public Point getPosition() {
        return new Point(kutu.x, kutu.y);
    }

    public Image getGorsel() {
        return this.gorsel;
    }

    abstract public void hareket();

    public boolean Gorunum() {
        return this.gorunum;
    }

    public Rectangle getKutu() {
        return kutu;
    }

    public void setHiz(float hiz) {
        this.hiz = hiz;
    }

    public double getHiz() {
        return hiz;
    }

    public void setAci(double fizikAci) {
        aci = fizikAci;
    }

    public double getAci() {
        return aci;
    }

    public int getGorsel_genislik() {
        return gorsel_genislik;
    }

    public int getGorsel_yukseklik() {
        return gorsel_yukseklik;
    }

    public void setPosition(int x, int y) {
        kutu.x = x;
        kutu.y = y;
    }
}
