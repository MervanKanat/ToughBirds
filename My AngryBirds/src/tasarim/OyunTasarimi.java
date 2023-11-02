package tasarim;

import gorunum.OyunGorunumu;
import tasarim.karakterler.Kus;
import tasarim.karakterler.Blok;
import tasarim.karakterler.Entity;
import tasarim.karakterler.EntityThread;
import tasarim.karakterler.Zemin;
import tasarim.karakterler.Domuz;

import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.event.EventListenerList;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JOptionPane;
/*import jdk.nashorn.internal.ir.ContinueNode;
import jdk.nashorn.internal.parser.TokenType;8*/

public class OyunTasarimi implements ActionListener {

    private OyunGorunumu kusGorunumu;
    private Seviye seviye;
    private ArrayList<Oyuncu> oyuncular;
    private ArrayList<Entity> entitys;
    private Kus mevcutKus;
    private Timer timer;
    private EntityThread entityThread;
    private EventListenerList listeners;
    private Oyuncu mevcutOyuncu;
    private String zorluk;
    private int mevcutSeviye;
    private int mevcutMaksSkor;
    private int skor;

    public OyunTasarimi() {

        entitys = new ArrayList<Entity>();
        oyuncular = new ArrayList<Oyuncu>();
        skor = 0;

        try {
            File file = new File("kaydet");
            for (File f : file.listFiles()) {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Oyuncu oyuncu = (Oyuncu) ois.readObject();
                oyuncular.add(oyuncu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        timer = new Timer(5, this);
        timer.start();

        listeners = new EventListenerList();

        entityThread = new EntityThread(entitys);
        addListListener(entityThread);
    }

    public OyunGorunumu getKusGorunumu() {
        return kusGorunumu;
    }

    public void setKusGorunumu(OyunGorunumu goruntule) {
        this.kusGorunumu = goruntule;
    }

    public Seviye getHarita() {
        return seviye;
    }

    public ArrayList<Oyuncu> getOyuncular() {
        return oyuncular;
    }

    public void setHarita(Seviye harita) {
        this.seviye = harita;
        entitys = harita.getEntityList();
        for (Entity e : entitys) {
            if (e instanceof Kus) {
                mevcutKus = (Kus) e;
                break;
            }
        }
        fireListChanged();
        if (!entityThread.isAlive()) {
            entityThread.start();
        }
    }

    public ArrayList<Entity> getEntityList() {
        return entitys;
    }

    public void updateEntity() {

        ArrayList<Entity> cikar = new ArrayList<Entity>();
        int kusTest = 0;
        int domuzTest = 0;

        for (Entity entity : entitys) {
            //---------------------------->Kutu x Domuz
            if (entity instanceof Domuz) {
                Domuz domuz = (Domuz) entity;
                Rectangle kutuDomuz = domuz.getKutu();

                boolean asagiHareket = true;
                for (Entity entity2 : entitys) {
                    if (entity2 instanceof Blok) {
                        Rectangle kutuBlok = entity2.getKutu();

                        if (kutuDomuz.intersectsLine(kutuBlok.getX() + kutuBlok.getWidth(),
                                kutuBlok.getY() + 2,
                                kutuBlok.getX() + kutuBlok.getWidth(),
                                kutuBlok.getY() + kutuBlok.getHeight() - 2)
                                || kutuDomuz.intersectsLine(kutuBlok.getX(),
                                        kutuBlok.getY() + 2,
                                        kutuBlok.getX(),
                                        kutuBlok.getY() + kutuBlok.getHeight() - 2)) {
                            domuz.yonDegis();
                        }
                        if (kutuDomuz.intersectsLine(kutuBlok.getX(),
                                kutuBlok.getY(),
                                kutuBlok.getX() + kutuBlok.getWidth(),
                                kutuBlok.getY())) {
                            asagiHareket = false;
                        }
                    }
                    if (entity2 instanceof Zemin) {
                        Zemin zemin = (Zemin) entity2;
                        Rectangle kutuZemin = zemin.getKutu();

                        if (kutuDomuz.intersectsLine(kutuZemin.getX(),
                                kutuZemin.getY(),
                                kutuZemin.getX() + kutuZemin.getWidth(),
                                kutuZemin.getY())) {
                            asagiHareket = false;
                        }
                    }
                    if (asagiHareket) {
                        domuz.asagiHareket();
                    } else {
                        domuz.asagihareketEtme();
                    }
                }
            }

            //---------------------------->Kutu X Kus
            if (entity == mevcutKus) {
                Kus kus = (Kus) entity;
                if (!kus.Gorunum()) {
                    cikar.add(kus);
                }
                Rectangle kutuKus = kus.getKutu();

                for (Entity entity2 : entitys) {
                    if (entity2 instanceof Blok) {
                        Blok blok = (Blok) entity2;
                        Rectangle kutuBlok = entity2.getKutu();
                        if (kutuKus.intersects(kutuBlok)) {
                            cikar.add(blok);
                            cikar.add(kus);
                        }
                    }
                }
                for (Entity entity2 : entitys) {
                    if (entity2 instanceof Domuz) {
                        Domuz domuz = (Domuz) entity2;
                        Rectangle kutuDomuz = domuz.getKutu();
                        if (kutuKus.intersects(kutuDomuz)) {
                            cikar.add(domuz);
                            cikar.add(kus);

                            boolean kazanmak = true;
                            for (Entity entity3 : entitys) {
                                if (entity3 instanceof Domuz && !cikar.contains(entity3)) {
                                    kazanmak = false;
                                    break;
                                }
                            }
                            if (kazanmak) {
                                for (Entity entity3 : entitys) {
                                    if (entity3 instanceof Kus) {
                                        ++skor;
                                    }
                                }
                                kazanmak();
                            }
                        }
                    }
                }
                for (Entity entity2 : entitys) {
                    if (entity2 instanceof Kus && !cikar.contains(entity2)) {
                        mevcutKus = (Kus) entity2;
                        break;
                    }
                }
            }
        }
        for (Entity entity : cikar) {

            entitys.remove(entity);
        }

        for (Entity entity4 : entitys) {
            if (entity4 instanceof Kus) {
                kusTest++;
            }
            if (entity4 instanceof Domuz) {
                domuzTest++;
            }
        }
        if (kusTest == 0 && domuzTest != 0) {
            kusGorunumu.repaint();
            kaybetmek();
        }
    }

    public void actionPerformed(ActionEvent event) {
        updateEntity();
        fireListChanged();
    }

    public void addListListener(ListListener listener) {
        listeners.add(ListListener.class, listener);
    }

    public void removeListListener(ListListener l) {
        listeners.remove(ListListener.class, l);
    }

    public void fireListChanged() {
        ListListener[] listenerList = (ListListener[]) listeners.getListeners(ListListener.class
        );

        for (ListListener listener : listenerList) {
            listener.listChanged(new ListChangedEvent(this, getEntityList(), mevcutKus));
        }
    }

    public void kazanmak() {
        javax.swing.JOptionPane.showMessageDialog(null,
                "Tebrikler Kazandınız!\nPuanınız : " + skor + ".");
        mevcutOyuncu.sonSeviye(mevcutSeviye, zorluk, skor);
        Seviye seviye = new Seviye("kaynaklar/haritalar/svy0" + (mevcutSeviye + 1) + ".txt", zorluk);
        if (seviye.Yuklendi()) {
            kusGorunumu.setHarita(seviye);
            this.setHarita(seviye);
            this.setMevcutSeviye(mevcutSeviye + 1);
        } else {

            javax.swing.JOptionPane.showMessageDialog(null, "Bu zorluktaki haritanin sonuna geldin...");
        }

    }

    public void kaybetmek() {
        int secenek = javax.swing.JOptionPane.showConfirmDialog(null,
                "Tekrar Denemek İster Misiniz ?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (secenek != JOptionPane.OK_OPTION) {
            System.exit(0);
        }

        Seviye seviye = new Seviye("kaynaklar/haritalar/svy0" + (mevcutSeviye) + ".txt", zorluk);
        if (seviye.Yuklendi()) {
            kusGorunumu.setHarita(seviye);
            this.setHarita(seviye);
        }
    }

    public void setZorluk(String zrl) {
        zorluk = zrl;
    }

    public void setOyuncular(ArrayList<Oyuncu> oyuncular) {
        this.oyuncular = oyuncular;
    }

    public void setMevcutOyuncu(Oyuncu oyuncu) {
        mevcutOyuncu = oyuncu;
    }

    public void setMevcutSeviye(int seviye) {
        mevcutSeviye = seviye;
    }

    public void setMevcutYuksekSkor() {
        mevcutMaksSkor = mevcutOyuncu.getMaksSkor(zorluk, mevcutSeviye);
        kusGorunumu.setMevcut_maksSkor(mevcutMaksSkor);
    }

    public Kus getMevcutKus() {
        return mevcutKus;
    }
}
