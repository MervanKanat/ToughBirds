package gorunum;

import gorunum.Oyunun_MenuGorunumu;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;

import tasarim.Oyuncu;

@SuppressWarnings("serial")
public class KaldiginYerden_Menusu extends Oyunun_MenuGorunumu {

    private JComboBox oyunucu_listesi;
    private JButton onay_butonu, silme_butonu;

    public KaldiginYerden_Menusu(ArrayList<Oyuncu> oyuncular) {
        oyunucu_listesi = new JComboBox();
        oyunucu_listesi.setSize(200, 30);
        oyunucu_listesi.setLocation(oyun_genisligi / 2 - 100, 150);

        for (Oyuncu oyuncu : oyuncular) {
            oyunucu_listesi.addItem(oyuncu);
        }

        onay_butonu = new JButton("Tamam");
        onay_butonu.setSize(200, 30);
        onay_butonu.setLocation(oyun_genisligi / 2 - 100, 200);

        silme_butonu = new JButton("Sil");
        silme_butonu.setSize(200, 30);
        silme_butonu.setLocation(oyun_genisligi / 2 - 100, 300);

        this.add(oyunucu_listesi, new Integer(1));
        this.add(silme_butonu, new Integer(1));
        this.add(onay_butonu, new Integer(1));
    }

    public JComboBox getOyunucu_listesi() {
        return oyunucu_listesi;
    }

    public void setOyunucu_listesi(ArrayList<Oyuncu> oyuncular) {
        oyunucu_listesi.removeAllItems();
        for (Oyuncu oyuncu : oyuncular) {
            oyunucu_listesi.addItem(oyuncu);
        }
    }

    public JButton getSilme_butonu() {
        return silme_butonu;
    }

    public JButton getOnay_butonu() {
        return onay_butonu;
    }

}
