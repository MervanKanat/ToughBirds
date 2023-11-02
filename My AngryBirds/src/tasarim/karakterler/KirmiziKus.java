package tasarim.karakterler;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class KirmiziKus extends Kus {

    public KirmiziKus() {
        super(45, 44);
        gorselYolu = "kaynaklar/gorseller/KirmiziKus.png";
        ImageIcon imageIcon = new ImageIcon(gorselYolu);
        gorsel = imageIcon.getImage();
        gorsel_yukseklik = imageIcon.getIconHeight();
        gorsel_genislik = imageIcon.getIconWidth();

        alan_boyutu = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    }

    public void asiliKal() {
        hareket = !hareket;
    }

}
