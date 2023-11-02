package gorunum;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Ana_Menu extends Oyunun_MenuGorunumu {

    private JButton yeniOyun_butonu, devam_butonu, kontrol_butonu, cikis_butonu;

    public Ana_Menu() {

        yeniOyun_butonu = new JButton("Yeni Oyun");
        yeniOyun_butonu.setSize(200, 30);
        yeniOyun_butonu.setLocation(oyun_genisligi / 2 - 100, 150);

        devam_butonu = new JButton("Kaldığın Yerden Devam Et");
        devam_butonu.setSize(200, 30);
        devam_butonu.setLocation(oyun_genisligi / 2 - 100, 225);

        kontrol_butonu = new JButton("Kontroller");
        kontrol_butonu.setSize(200, 30);
        kontrol_butonu.setLocation(oyun_genisligi / 2 - 100, 300);

        cikis_butonu = new JButton("Çıkış Yap");
        cikis_butonu.setSize(200, 30);
        cikis_butonu.setLocation(oyun_genisligi / 2 - 100, 375);

        //Ana sayfada geri_butonu yok : 
        geri_butonu.setVisible(false);

        this.add(yeniOyun_butonu, new Integer(1));
        this.add(devam_butonu, new Integer(1));
        this.add(kontrol_butonu, new Integer(1));
        this.add(cikis_butonu, new Integer(1));
    }

    public JButton getYeniOyun_butonu() {
        return yeniOyun_butonu;
    }

    public JButton getDevam_butonu() {
        return devam_butonu;
    }

    public JButton getKontrol_butonu() {
        return kontrol_butonu;
    }

    public JButton getCikis_butonu() {
        return cikis_butonu;
    }

}
