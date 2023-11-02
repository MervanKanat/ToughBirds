package tasarim.karakterler;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Sparrow extends Kus {

    public Sparrow() {

        super(30, 29);
        gorselYolu = "kaynaklar/gorseller/sparrow.png";
        ImageIcon imageIcon = new ImageIcon(gorselYolu);
        gorsel = imageIcon.getImage();
        gorsel_yukseklik = imageIcon.getIconHeight();
        gorsel_genislik = imageIcon.getIconWidth();
        hiz = 100;

        alan_boyutu = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    }

    public void asiliKal() {

    }

}
