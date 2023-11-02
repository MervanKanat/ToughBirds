package gorunum;

import gorunum.Oyunun_MenuGorunumu;
import java.util.ArrayList;

import javax.swing.JButton;

import tasarim.SeviyeNumarasi;

@SuppressWarnings("serial")
public class SeviyeSecimi_MenuGorunumu extends Oyunun_MenuGorunumu {

    private ArrayList<JButton> seviye_butonu;

    public SeviyeSecimi_MenuGorunumu() {
        seviye_butonu = new ArrayList<JButton>();

        for (int seviyeNumarasi = 0; seviyeNumarasi < SeviyeNumarasi.getSeviyeNumarasi(); ++seviyeNumarasi) {
            seviye_butonu.add(new JButton("" + (seviyeNumarasi + 1)));
            seviye_butonu.get(seviyeNumarasi).setSize(60, 60);
            int satir_sayisi = seviyeNumarasi / 5;
            seviye_butonu.get(seviyeNumarasi).setLocation(oyun_genisligi / 2 - 110 + (seviyeNumarasi % 5) * 80, oyun_yuksekligi / 2 - 55 + satir_sayisi * 80);
        }

        for (JButton button : seviye_butonu) {
            this.add(button, new Integer(1));
        }

    }

    public ArrayList<JButton> getSeviye_butonu() {
        return seviye_butonu;
    }
}
