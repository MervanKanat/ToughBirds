package tasarim.karakterler;

import javax.swing.ImageIcon;

public class Blok extends Entity {

    public Blok(int x, int y, int genislik, int yukseklik) {
        super(x, y, genislik, yukseklik);

        gorselYolu = "kaynaklar/gorseller/blok.png";
        ImageIcon imageIcon = new ImageIcon(gorselYolu);
        gorsel = imageIcon.getImage();
        gorsel_yukseklik = imageIcon.getIconHeight();
        gorsel_genislik = imageIcon.getIconWidth();
    }

    @Override
    public void hareket() {

    }

}
