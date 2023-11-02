package tasarim.karakterler;

import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Pigeon extends Kus {

    public Pigeon() {
        super(50, 48);
        gorselYolu = "kaynaklar/gorseller/pigeon.png";
        ImageIcon imageIcon = new ImageIcon(gorselYolu);
        gorsel = imageIcon.getImage();
        gorsel_yukseklik = imageIcon.getIconHeight();
        gorsel_genislik = imageIcon.getIconWidth();
        hiz = 100;
        alan_boyutu = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    }

 //Hareket etmeyi engeller : 
    public void asiliKal() {
       hareket = !hareket;
    }

   
}
