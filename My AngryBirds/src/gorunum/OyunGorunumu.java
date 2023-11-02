package gorunum;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import tasarim.Seviye;
import tasarim.ListChangedEvent;
import tasarim.ListListener;
import tasarim.karakterler.Kus;

import tasarim.karakterler.Entity;

@SuppressWarnings("serial")
public class OyunGorunumu extends JPanel implements ListListener {

    private Seviye harita;
    private ArrayList<Entity> entitys;
    private int oyun_yuksekligi = 600;
    private int oyun_genisligi = 1200;
    private Kus mevcut_kus;
    private int mevcut_maksSkor;
    private String sapan1 = "kaynaklar/gorseller/sapan1.png";
    private Image sapanGorsel1;
    private String sapan2 = "kaynaklar/gorseller/sapan2.png";
    private Image sapanGorsel2;

    public OyunGorunumu(ArrayList<Entity> entities) {

        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon imageIcon_bir = new ImageIcon(sapan1);
        sapanGorsel1 = imageIcon_bir.getImage();
        ImageIcon imageIcon_iki = new ImageIcon(sapan2);
        sapanGorsel2 = imageIcon_iki.getImage();

        this.entitys = entities;
    }

    public void paint(Graphics g) {
        super.paint(g);

        // Seviyeyi gosteriyoruz
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(harita.getGorsel(), 0, 0, oyun_genisligi, oyun_yuksekligi, this);
        g2d.drawImage(sapanGorsel2, 100, 440, this);

        int k = 0;
        for (int i = entitys.size() - 1; i >= 0; i--) {
            Entity e = entitys.get(i);
            if (e instanceof Kus && e != mevcut_kus) {
                g2d.drawImage(e.getGorsel(), oyun_genisligi - 100 - k * 15, 100, e.getGorsel_genislik(), e.getGorsel_yukseklik(), this);
                k++;
            }

        }

        for (Entity e : entitys) {
            if (!(e instanceof Kus)) { // tüm kuşları göstermiyoruz, sadece mevcut olanlari
                g2d.drawImage(e.getGorsel(), (int) e.getPosition().getX(), (int) e.getPosition().getY(), this);
            }
            if (e == mevcut_kus) {
                g2d.setStroke(new BasicStroke(7.0f));
                g2d.setColor(new Color(54, 28, 13));
                if (!mevcut_kus.Ucus()) {
                    g2d.drawLine(135, 470, (int) e.getPosition().getX() + e.getGorsel_genislik() / 2, (int) e.getPosition().getY() + e.getGorsel_yukseklik() / 2);
                }
                g2d.drawImage(e.getGorsel(), (int) e.getPosition().getX(), (int) e.getPosition().getY(), e.getGorsel_genislik(), e.getGorsel_yukseklik(), this);
                if (!mevcut_kus.Ucus()) {
                    g2d.drawLine(120, 470, (int) e.getPosition().getX() + e.getGorsel_genislik() / 2, (int) e.getPosition().getY() + e.getGorsel_yukseklik() / 2);
                }

            }
            g2d.setStroke(new BasicStroke(7.0f));
            g2d.setColor(new Color(54, 28, 13));
            if (mevcut_kus.Ucus()) {
                g2d.drawLine(135, 470, 120, 470);
            }
            g2d.setStroke(new BasicStroke(1.0f));
            g2d.setColor(new Color(0, 0, 0));
        }

        g2d.drawImage(sapanGorsel1, 100, 440, this);
        g.drawString("En yuksek skor: " + mevcut_maksSkor, 10, 15);
        g.drawString("Kalan Süren: " + mevcut_kus.getUcarkenKalan_zaman(), 10, 30);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void setEntityList(ArrayList<Entity> entities) {
        this.entitys = entities;
    }

    public Seviye getHarita() {
        return harita;
    }

    public void setHarita(Seviye harita) {
        this.harita = harita;
    }

    public void setMevcut_maksSkor(int maksSkor) {
        this.mevcut_maksSkor = maksSkor;
    }

    @Override
    public void listChanged(ListChangedEvent event) {
        entitys = event.getEntityList();
        mevcut_kus = event.getMevcut_kus();
        repaint();

    }
}
