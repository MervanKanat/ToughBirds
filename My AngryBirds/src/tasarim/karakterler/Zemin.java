package tasarim.karakterler;

import javax.swing.ImageIcon;

public class Zemin extends Entity {

    public Zemin(int x, int y, int genislik, int yukseklik) {
        super(x, y, genislik, yukseklik);

        gorselYolu = "kaynaklar/gorseller/Zemin.png";
        ImageIcon imageIcon = new ImageIcon(gorselYolu);
        gorsel = imageIcon.getImage();
        gorsel_genislik = imageIcon.getIconHeight();
        gorsel_yukseklik = imageIcon.getIconWidth();
    }

    @Override
    public void hareket() {

    }

}
