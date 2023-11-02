package tasarim.karakterler;

import java.awt.Dimension;
import java.util.ArrayList;

public abstract class Kus extends Entity {

    protected short ucus_suresi;

    protected boolean ucus;
    protected boolean hareket;
    private double zaman;
    protected Dimension alan_boyutu;
    private double x_ivmesi;
    private double y_ivmesi;
    protected int x_baslangicKonumu;
    protected int y_baslangicKonumu;
    private long gecen_zaman;
    private long ucarkenKalan_zaman;

    public Kus(int genislik, int yukseklik) {
        super(100, 440, genislik, yukseklik);
        hareket = false;
        zaman = 0.1;
        x_ivmesi = 0;
        x_baslangicKonumu = 100;
        y_baslangicKonumu = 440;
        y_ivmesi = 9.81;
        ucarkenKalan_zaman = 10000;
    }

    public int getX_baslangicKonumu() {
        return x_baslangicKonumu;
    }

    public int getY_baslangicKonumu() {
        return y_baslangicKonumu;
    }

    //Yalnizca havada asili kalabilen kuslar icin yeniden tanimlandi
    public abstract void asiliKal();

    public boolean Ucus() {
        return ucus;
    }

    public void hareket() {
        if (ucus) {
            if (hareket) {
                kutu.x = (int) Math.round(0.5 * x_ivmesi * zaman * zaman + x_baslangicKonumu + hiz * Math.cos(aci) * zaman);
                kutu.y = (int) Math.round(0.5 * y_ivmesi * zaman * zaman + y_baslangicKonumu - hiz * Math.sin(aci) * zaman);
                zaman += 0.1;
            }
            long mevcut_zaman = System.currentTimeMillis();
            ucarkenKalan_zaman -= (mevcut_zaman - gecen_zaman);
            gecen_zaman = mevcut_zaman;
        }
        if (kutu.y > (int) alan_boyutu.getHeight() || kutu.x > (int) alan_boyutu.getWidth() || ucarkenKalan_zaman <= 0) {
            gorunum = false;
        }
    }

    public void baslat() {
        hareket = true;
        ucus = true;
        gecen_zaman = System.currentTimeMillis();
    }

    public void saga_hareket() {
        x_ivmesi += 0.1;
    }

    public void sola_hareket() {
        x_ivmesi -= 0.1;
    }

    public long getUcarkenKalan_zaman() {
        return ucarkenKalan_zaman;
    }
}
