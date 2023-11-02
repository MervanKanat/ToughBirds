package kontrol_yapilari;

import main.OyunAlani;
import tasarim.OyunTasarimi;
import tasarim.Seviye;
import tasarim.Oyuncu;
import gorunum.ZorlukSec_Menusu;
import gorunum.Ana_Menu;
import gorunum.SeviyeSecimi_MenuGorunumu;
import gorunum.KaldiginYerden_Menusu;
import gorunum.KullaniciGiris_Menusu;
import gorunum.KontrolTuslari_Menusu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MenuKontrol implements KeyListener, ActionListener, MouseListener {

    private JTextField oyuncuIsmi;
    private JButton yeni_geri, kaldigin_geri, kontrol_geri;
    private JButton zorluk_geri, seviye_geri;
    private JButton yeni_butonu, kaldigin_butonu, kontrol_butonu, cikis_butonu;
    private JButton yeni_onay, kaldigin_onay, sil_butonu;
    private JButton baslangicButonu, ortaButonu, zorButonu;
    private ArrayList<JButton> seviye_butonu;
    private Oyuncu mevcut_oyuncu;
    @SuppressWarnings("unused")
    private ArrayList<Oyuncu> kusOyunculari;
    private JComboBox oyuncuList;
    private Ana_Menu kusAna_Menu;
    private KullaniciGiris_Menusu kusKullaniciGiris_Menusu;
    private KaldiginYerden_Menusu kusKaldiginYerden_Menusu;
    private KontrolTuslari_Menusu kusKontrolTuslari_Menusu;
    private ZorlukSec_Menusu kusZorlukSec_Menusu;
    private SeviyeSecimi_MenuGorunumu kusSS_MG;
    private OyunAlani kusAlani;
    private String zorluk = "";
    private OyunTasarimi kusModeli;

    public MenuKontrol(OyunAlani alan) {

        kusAlani = alan;
        kusModeli = kusAlani.getKusTasarimi();
        kusOyunculari = kusModeli.getOyuncular();

        kusAna_Menu = alan.getKusAna_Menu();
        kusKullaniciGiris_Menusu = alan.getKusGiris_Menusu();
        kusKaldiginYerden_Menusu = alan.getKusKaldiginYerden_Menusu();
        kusKontrolTuslari_Menusu = alan.getKusKontrolTuslari_Menusu();
        kusZorlukSec_Menusu = alan.getZorlukSec_Menusu();
        kusSS_MG = alan.getKusSeviyeSecimi_MenuGorunumu();

        //Listener ekledik:
        yeni_geri = kusKullaniciGiris_Menusu.getGeri_butonu();
        yeni_geri.addActionListener(this);
        kaldigin_geri = kusKaldiginYerden_Menusu.getGeri_butonu();
        kaldigin_geri.addActionListener(this);
        kontrol_geri = kusKontrolTuslari_Menusu.getGeri_butonu();
        kontrol_geri.addActionListener(this);
        zorluk_geri = kusZorlukSec_Menusu.getGeri_butonu();
        zorluk_geri.addActionListener(this);
        seviye_geri = kusSS_MG.getGeri_butonu();
        seviye_geri.addActionListener(this);

        yeni_butonu = kusAna_Menu.getYeniOyun_butonu();
        yeni_butonu.addActionListener(this);
        kaldigin_butonu = kusAna_Menu.getDevam_butonu();
        kaldigin_butonu.addActionListener(this);
        kontrol_butonu = kusAna_Menu.getKontrol_butonu();
        kontrol_butonu.addActionListener(this);
        cikis_butonu = kusAna_Menu.getCikis_butonu();
        cikis_butonu.addActionListener(this);

        oyuncuIsmi = kusKullaniciGiris_Menusu.getOyuncu_ismiAlani();
        yeni_onay = kusKullaniciGiris_Menusu.getGiris_butonu();
        yeni_onay.addActionListener(this);

        oyuncuList = kusKaldiginYerden_Menusu.getOyunucu_listesi();
        sil_butonu = kusKaldiginYerden_Menusu.getSilme_butonu();
        sil_butonu.addActionListener(this);
        kaldigin_onay = kusKaldiginYerden_Menusu.getOnay_butonu();
        kaldigin_onay.addActionListener(this);

        baslangicButonu = kusZorlukSec_Menusu.getBaslangic_butonu();
        baslangicButonu.addActionListener(this);
        ortaButonu = kusZorlukSec_Menusu.getOrta_butonu();
        ortaButonu.addActionListener(this);
        zorButonu = kusZorlukSec_Menusu.getZor_butonu();
        zorButonu.addActionListener(this);

        seviye_butonu = kusSS_MG.getSeviye_butonu();
        for (JButton buton : seviye_butonu) {
            buton.addActionListener(this);
        }

        JOptionPane.setDefaultLocale(Locale.ENGLISH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Panel değiştir:

        if (e.getSource().equals(yeni_geri) || e.getSource().equals(kaldigin_geri) || e.getSource().equals(kontrol_geri)) {
            kusAlani.setContentPane(kusAna_Menu);
            kusAna_Menu.requestFocus();
            kusAlani.setVisible(true);
        }
        if (e.getSource().equals(zorluk_geri)) {
            if (kusZorlukSec_Menusu.getAna_panel() == "yeniPanel") {
                kusAlani.setContentPane(kusKullaniciGiris_Menusu);
                kusKullaniciGiris_Menusu.requestFocus();
                kusAlani.setVisible(true);
            } else {
                kusAlani.setContentPane(kusKaldiginYerden_Menusu);
                kusKaldiginYerden_Menusu.requestFocus();
                kusAlani.setVisible(true);
            }
        }

        if (e.getSource().equals(seviye_geri)) {
            kusAlani.setContentPane(kusZorlukSec_Menusu);
            kusZorlukSec_Menusu.requestFocus();
            kusAlani.setVisible(true);
        }

        if (e.getSource().equals(yeni_butonu)) {
            kusAlani.setContentPane(kusKullaniciGiris_Menusu);
            kusKullaniciGiris_Menusu.requestFocus();
            kusAlani.setVisible(true);
        }

        if (e.getSource().equals(kaldigin_butonu)) {
            if (kusAlani.getKusTasarimi().getOyuncular().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null,
                        "Kayıt dosyası bulunamadı!");
            } else {
                kusAlani.setContentPane(kusKaldiginYerden_Menusu);
                kusKaldiginYerden_Menusu.requestFocus();
                kusAlani.setVisible(true);
            }
        }

        if (e.getSource().equals(kontrol_butonu)) {
            kusAlani.setContentPane(kusKontrolTuslari_Menusu);
            kusKontrolTuslari_Menusu.requestFocus();
            kusAlani.setVisible(true);
        }

        if (e.getSource().equals(cikis_butonu)) {
            int secenek = javax.swing.JOptionPane.showConfirmDialog(null,
                    "Emin misiniz?", "Çıkışınızı Onaylayın", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (secenek == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }

        if (e.getSource().equals(yeni_onay)) {
            if (oyuncuIsmi.getText().equals("")) {
                javax.swing.JOptionPane.showMessageDialog(null, "Lütfen isminizi giriniz!");
            } else {

                // yeni bir oyuncunun oluşturulması
                mevcut_oyuncu = new Oyuncu(oyuncuIsmi.getText());
                kusAlani.setMevcutOyuncu(mevcut_oyuncu);
                kusAlani.setZorluk("");
                kusAlani.setMevcutSeviye(0);
                kusAlani.setMevcutYuksekSkor();

                kusAlani.setContentPane(kusZorlukSec_Menusu);
                kusZorlukSec_Menusu.setAna_panel("yeniPanel");
                kusZorlukSec_Menusu.requestFocus();
                kusAlani.setVisible(true);

                // oyuncu listesini güncelle
                ArrayList<Oyuncu> oyuncular = new ArrayList<Oyuncu>();
                try {
                    File file = new File("kaydet");
                    for (File f : file.listFiles()) {
                        FileInputStream fis = new FileInputStream(f);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        Oyuncu oyuncu = (Oyuncu) ois.readObject();
                        oyuncular.add(oyuncu);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                kusAlani.setOyuncular(oyuncular);
                kusKaldiginYerden_Menusu.setOyunucu_listesi(oyuncular);
            }
        }

        if (e.getSource().equals(kaldigin_onay)) {

            mevcut_oyuncu = (Oyuncu) oyuncuList.getSelectedItem();
            kusAlani.setMevcutOyuncu(mevcut_oyuncu);
            kusAlani.setZorluk("");
            kusAlani.setMevcutSeviye(0);
            kusAlani.setMevcutYuksekSkor();

            kusAlani.setContentPane(kusZorlukSec_Menusu);
            kusZorlukSec_Menusu.setAna_panel("kaldiginYerPanel");
            kusZorlukSec_Menusu.requestFocus();
            kusAlani.setVisible(true);

            ArrayList<Oyuncu> oyuncular = new ArrayList<Oyuncu>();
            try {
                File file = new File("kaydet");
                for (File f : file.listFiles()) {
                    FileInputStream fis = new FileInputStream(f);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Oyuncu oyuncu = (Oyuncu) ois.readObject();
                    oyuncular.add(oyuncu);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            kusAlani.setOyuncular(oyuncular);
            kusKaldiginYerden_Menusu.setOyunucu_listesi(oyuncular);
        }

        if (e.getSource().equals(sil_butonu)) {
            int option = javax.swing.JOptionPane.showConfirmDialog(null,
                    "Emin misiniz?", "Silmek istediğinizi onaylayın", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {

                //Seçili dosyayı sil
                Oyuncu oyuncu = (Oyuncu) oyuncuList.getSelectedItem();
                oyuncuList.removeItem(oyuncu);
                File file = new File("kaydet/" + oyuncu.getIsim() + ".kaydedildi");
                file.delete();

                //Oyuncu listesini güncelle
                ArrayList<Oyuncu> oyuncular = new ArrayList<Oyuncu>();
                try {
                    File file2 = new File("kaydet");
                    for (File f : file2.listFiles()) {
                        FileInputStream fis = new FileInputStream(f);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        Oyuncu oyuncu2 = (Oyuncu) ois.readObject();
                        oyuncular.add(oyuncu2);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                kusAlani.setOyuncular(oyuncular);
                kusKaldiginYerden_Menusu.setOyunucu_listesi(oyuncular);

                kusAlani.setContentPane(kusKaldiginYerden_Menusu);
                kusKaldiginYerden_Menusu.requestFocus();
                kusAlani.setVisible(true);
            }
        }

        if (e.getSource().equals(baslangicButonu)) {
            zorluk = "baslangic";
        }
        if (e.getSource().equals(ortaButonu)) {
            zorluk = "orta";
        }
        if (e.getSource().equals(zorButonu)) {
            zorluk = "zor";
        }

        // kaydetmeye bağlı olarak (varlık)
        if (!zorluk.equals("") && (e.getSource().equals(baslangicButonu) || e.getSource().equals(ortaButonu) || e.getSource().equals(zorButonu))) {
            for (int seviyeNumarasi = 0; seviyeNumarasi < seviye_butonu.size(); ++seviyeNumarasi) {
                seviye_butonu.get(seviyeNumarasi).setEnabled(false);

                if (mevcut_oyuncu.sonKilit(seviyeNumarasi, zorluk)) {
                    seviye_butonu.get(seviyeNumarasi).setEnabled(true);
                    if ((seviyeNumarasi + 1) < seviye_butonu.size()) {
                        seviye_butonu.get(seviyeNumarasi + 1).setEnabled(true);
                    }
                }
            }
            seviye_butonu.get(0).setEnabled(true);

            kusAlani.setZorluk(zorluk);

            kusAlani.setContentPane(kusSS_MG);
            kusSS_MG.requestFocus();
            kusAlani.setVisible(true);
        }

        //Seçilen seviyeye karşılık gelen oyunu başlatıyoruz
        for (int i = 0; i < seviye_butonu.size(); ++i) {
            if (e.getSource().equals(seviye_butonu.get(i))) {
                Seviye seviye = new Seviye("kaynaklar/haritalar/svy0" + (i + 1) + ".txt", zorluk);
                if (seviye.Yuklendi()) {
                    kusAlani.getKusGorunumu().setHarita(seviye);
                    kusAlani.getKusTasarimi().setHarita(seviye);
                    kusAlani.setMevcutYuksekSkor();
                    kusAlani.setOyun();
                    kusAlani.setMevcutSeviye(i + 1);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Bu seviye için henüz haritamiz bulunmamakta.");
                }
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_ESCAPE:
                if (kusAlani.getContentPane() == kusAna_Menu) {
                    int option = javax.swing.JOptionPane.showConfirmDialog(null, "Emin misiniz ?", "Çıkışınızı Onaylayın", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (option == JOptionPane.OK_OPTION) {
                        System.exit(0);
                    }
                }
                if (kusAlani.getContentPane() == kusKullaniciGiris_Menusu || kusAlani.getContentPane() == kusKaldiginYerden_Menusu || kusAlani.getContentPane() == kusKontrolTuslari_Menusu) {
                    kusAlani.setContentPane(kusAna_Menu);
                    kusAna_Menu.requestFocus();
                    kusAlani.setVisible(true);
                }
                if (kusAlani.getContentPane() == kusZorlukSec_Menusu) {
                    if (kusZorlukSec_Menusu.getAna_panel() == "yeniPanel") {
                        kusAlani.setContentPane(kusKullaniciGiris_Menusu);
                        kusKullaniciGiris_Menusu.requestFocus();
                        kusAlani.setVisible(true);
                    } else {
                        kusAlani.setContentPane(kusKaldiginYerden_Menusu);
                        kusKaldiginYerden_Menusu.requestFocus();
                        kusAlani.setVisible(true);
                    }
                }
                if (kusAlani.getContentPane() == kusSS_MG) {
                    kusAlani.setContentPane(kusZorlukSec_Menusu);
                    kusZorlukSec_Menusu.requestFocus();
                    kusAlani.setVisible(true);
                }
                break;
            default:
                System.out.println("Geçersiz karakter girişi yaptınız !");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {

    }

    @Override
    public void mouseExited(MouseEvent arg0) {

    }

    @Override
    public void mousePressed(MouseEvent arg0) {

        if (kusAlani.getContentPane() == kusKullaniciGiris_Menusu) {
            kusKullaniciGiris_Menusu.requestFocus();
        }

        if (kusAlani.getContentPane() == kusKaldiginYerden_Menusu) {
            kusKaldiginYerden_Menusu.requestFocus();
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {

    }
}
