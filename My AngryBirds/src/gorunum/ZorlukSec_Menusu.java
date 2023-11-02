package gorunum;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ZorlukSec_Menusu extends Oyunun_MenuGorunumu {

    private JLabel zorluk_tanimlama;
    private JButton baslangic_butonu, orta_butonu, zor_butonu;
    private String ana_panel = "";

    public ZorlukSec_Menusu() {

        zorluk_tanimlama = new JLabel("Zorluk Seviyenizi Secin : ");
        zorluk_tanimlama.setSize(200, 30);
        zorluk_tanimlama.setLocation(oyun_genisligi / 2 - 100, 150);

        baslangic_butonu = new JButton("BASLANGIÇ");
        baslangic_butonu.setSize(200, 30);
        baslangic_butonu.setLocation(oyun_genisligi / 2 - 100, 250);

        orta_butonu = new JButton("ORTA");
        orta_butonu.setSize(200, 30);
        orta_butonu.setLocation(oyun_genisligi / 2 - 100, 325);

        zor_butonu = new JButton("ZOR");
        zor_butonu.setSize(200, 30);
        zor_butonu.setLocation(oyun_genisligi / 2 - 100, 400);

        this.add(zorluk_tanimlama, new Integer(1));
        this.add(baslangic_butonu, new Integer(1));
        this.add(orta_butonu, new Integer(1));
        this.add(zor_butonu, new Integer(1));

    }

    public JButton getBaslangic_butonu() {
        return baslangic_butonu;
    }

    public JButton getOrta_butonu() {
        return orta_butonu;
    }

    public JButton getZor_butonu() {
        return zor_butonu;
    }

    public String getAna_panel() {
        return this.ana_panel;
    }

    public void setAna_panel(String ana_panel) {
        this.ana_panel = ana_panel;
    }
}
