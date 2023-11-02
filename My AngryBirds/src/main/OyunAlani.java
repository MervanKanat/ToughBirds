package main;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;

import gorunum.OyunGorunumu;
import gorunum.ZorlukSec_Menusu;
import gorunum.Ana_Menu;
import gorunum.SeviyeSecimi_MenuGorunumu;
import gorunum.KaldiginYerden_Menusu;
import gorunum.KontrolTuslari_Menusu;
import gorunum.KullaniciGiris_Menusu;
import gorunum.SeviyeSecimi_MenuGorunumu;
import kontrol_yapilari.OyunKontrolu;
import kontrol_yapilari.MenuKontrol;
import tasarim.OyunTasarimi;

import tasarim.Oyuncu;
import tasarim.karakterler.Entity;

@SuppressWarnings("serial")
public class OyunAlani extends JFrame {

    private Ana_Menu kusAna_Menu;
    private KullaniciGiris_Menusu kusGiris_Menusu;
    private KaldiginYerden_Menusu kusKaldiginYerden_Menusu;
    private KontrolTuslari_Menusu kusKontrolTuslari_Menusu;
    private ZorlukSec_Menusu zorlukSec_Menusu;
    private SeviyeSecimi_MenuGorunumu kusSeviyeSecimi_MenuGorunumu;
    private OyunGorunumu kusGorunumu;
    private OyunKontrolu kusKontrolu;
    private MenuKontrol kusMenuKontrolu;
    private OyunTasarimi kusTasarimi;
    private String kazananIsmi;
    ArrayList<Entity> kusEntitys;
    ArrayList<Oyuncu> kusOyuncular;

    public OyunAlani(String isim) {
        kazananIsmi = isim;

        //Yedekleme
        File file = new File("kaydet/");
        file.mkdir();

        kusTasarimi = new OyunTasarimi();
        kusEntitys = kusTasarimi.getEntityList();
        kusOyuncular = kusTasarimi.getOyuncular();

        //Gorunumler
        kusAna_Menu = new Ana_Menu();
        kusGiris_Menusu = new KullaniciGiris_Menusu();
        kusKaldiginYerden_Menusu = new KaldiginYerden_Menusu(kusOyuncular);
        kusKontrolTuslari_Menusu = new KontrolTuslari_Menusu();
        zorlukSec_Menusu = new ZorlukSec_Menusu();
        kusSeviyeSecimi_MenuGorunumu = new SeviyeSecimi_MenuGorunumu();

        kusGorunumu = new OyunGorunumu(kusEntitys);

        kusTasarimi.setKusGorunumu(kusGorunumu);

        //Kontrol
        kusKontrolu = new OyunKontrolu(this);
        kusMenuKontrolu = new MenuKontrol(this);

        //Listener
        kusAna_Menu.addKeyListener(kusMenuKontrolu);
        kusGiris_Menusu.addKeyListener(kusMenuKontrolu);
        kusGiris_Menusu.addMouseListener(kusMenuKontrolu);
        kusKaldiginYerden_Menusu.addKeyListener(kusMenuKontrolu);
        kusKaldiginYerden_Menusu.addMouseListener(kusMenuKontrolu);
        kusKontrolTuslari_Menusu.addKeyListener(kusMenuKontrolu);
        zorlukSec_Menusu.addKeyListener(kusMenuKontrolu);
        kusSeviyeSecimi_MenuGorunumu.addKeyListener(kusMenuKontrolu);

        kusGorunumu.addKeyListener(kusKontrolu);
        kusGorunumu.addMouseListener(kusKontrolu);
        kusGorunumu.addMouseMotionListener(kusKontrolu);
        kusTasarimi.addListListener(kusGorunumu);
        kusTasarimi.addListListener(kusKontrolu);

        this.setContentPane(kusAna_Menu);

        this.setTitle(kazananIsmi);
        this.setSize((int) OyunAlani.getOyunBoyutu().getWidth(), (int) OyunAlani.getOyunBoyutu().getHeight());
        this.setSize((int) OyunAlani.getOyunBoyutu().getWidth(), (int) OyunAlani.getOyunBoyutu().getHeight());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public OyunGorunumu getKusGorunumu() {
        return kusGorunumu;
    }

    public Ana_Menu getKusAna_Menu() {
        return kusAna_Menu;
    }

    public KullaniciGiris_Menusu getKusGiris_Menusu() {
        return kusGiris_Menusu;
    }

    public KaldiginYerden_Menusu getKusKaldiginYerden_Menusu() {
        return kusKaldiginYerden_Menusu;
    }

    public KontrolTuslari_Menusu getKusKontrolTuslari_Menusu() {
        return kusKontrolTuslari_Menusu;
    }

    public ZorlukSec_Menusu getZorlukSec_Menusu() {
        return zorlukSec_Menusu;
    }

    public SeviyeSecimi_MenuGorunumu getKusSeviyeSecimi_MenuGorunumu() {
        return kusSeviyeSecimi_MenuGorunumu;
    }

    public OyunTasarimi getKusTasarimi() {
        return kusTasarimi;
    }

    public OyunKontrolu getKusKontrolu() {
        return kusKontrolu;
    }

    public void setSeviyeSecimi_MenuGorunumu() {

        this.setContentPane(kusSeviyeSecimi_MenuGorunumu);
        kusSeviyeSecimi_MenuGorunumu.requestFocus();
        this.setVisible(true);

    }

    public void setOyun() {
        this.setContentPane(kusGorunumu);
        kusGorunumu.requestFocus();
        this.setVisible(true);

    }

    public void setOyuncular(ArrayList<Oyuncu> oyuncular) {
        kusTasarimi.setOyuncular(oyuncular);
    }

    public void setMevcutOyuncu(Oyuncu oyuncu) {
        kusTasarimi.setMevcutOyuncu(oyuncu);
    }

    public void setZorluk(String zrk) {
        kusTasarimi.setZorluk(zrk);
    }

    public void setMevcutSeviye(int seviye) {
        kusTasarimi.setMevcutSeviye(seviye);
    }

    public void setMevcutYuksekSkor() {
        kusTasarimi.setMevcutYuksekSkor();
    }

    static public Dimension getOyunBoyutu() {
        Dimension oyunBoyutu = new Dimension(1200, 600);
        return oyunBoyutu;
    }

}
