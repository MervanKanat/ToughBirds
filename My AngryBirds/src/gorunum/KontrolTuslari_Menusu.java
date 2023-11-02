package gorunum;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import main.OyunAlani;

@SuppressWarnings("serial")
public class KontrolTuslari_Menusu extends Oyunun_MenuGorunumu {

    final int oyun_genisligi = OyunAlani.getOyunBoyutu().width;
    final int oyun_yuksekligi = OyunAlani.getOyunBoyutu().height;

    private JLabel kontrolTanimlama;
    private JLabel kontrolTanimlama_gorunum;
    private String kontrolYolu = "kaynaklar/gorseller/Kontrol.png";
    private Image image;

    public KontrolTuslari_Menusu() {
        ImageIcon imageIcon = new ImageIcon(kontrolYolu);
        image = imageIcon.getImage();

        kontrolTanimlama = new JLabel("Kontroller : ");
        kontrolTanimlama.setSize(200, 30);
        kontrolTanimlama.setLocation(oyun_genisligi / 2 - 200, 150);

        // JLabel istenen görüntüyle yeniden boyar.
        kontrolTanimlama_gorunum = new JLabel() {

            public void paint(Graphics g) {
                g.drawImage(image, oyun_genisligi / 2 - 280 / 2, oyun_yuksekligi / 2 - 180 / 2, 280, 180, null);

            }
        };

        kontrolTanimlama_gorunum.setSize(new Dimension(oyun_genisligi, oyun_yuksekligi));
        setFocusable(true);
        setDoubleBuffered(true);

        this.add(kontrolTanimlama, new Integer(1));
        this.add(kontrolTanimlama_gorunum, new Integer(1));
    }

}
