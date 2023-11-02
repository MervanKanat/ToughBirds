package gorunum;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class KullaniciGiris_Menusu extends Oyunun_MenuGorunumu {

    JLabel oyuncu_ismi;
    JTextField oyuncu_ismiAlani;
    JButton giris_butonu;

    public KullaniciGiris_Menusu() {
        oyuncu_ismi = new JLabel("Kullanıcı Adı : ");
        oyuncu_ismi.setSize(200, 30);
        oyuncu_ismi.setLocation(oyun_genisligi / 2 - 100, 150);

        oyuncu_ismiAlani = new JTextField();
        oyuncu_ismiAlani.setSize(200, 30);
        oyuncu_ismiAlani.setLocation(oyun_genisligi / 2 - 100, 250);

        giris_butonu = new JButton("Giriş Yap");
        giris_butonu.setSize(200, 30);
        giris_butonu.setLocation(oyun_genisligi / 2 - 100, 350);

        this.add(oyuncu_ismi, new Integer(1));
        this.add(oyuncu_ismiAlani, new Integer(1));
        this.add(giris_butonu, new Integer(1));

    }

    public JTextField getOyuncu_ismiAlani() {
        return oyuncu_ismiAlani;
    }

    public JButton getGiris_butonu() {
        return giris_butonu;
    }

}
