package gorunum;

import main.OyunAlani;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Oyunun_MenuGorunumu extends JLayeredPane {

    final int oyun_genisligi = OyunAlani.getOyunBoyutu().width;
    final int oyun_yuksekligi = OyunAlani.getOyunBoyutu().height;

    private String baslikYolu = "kaynaklar/gorseller/baslik.png";
    private String arkaplanYolu = "kaynaklar/gorseller/arkaPlan.png";
    private Image gorsel, baslik;
    private JPanel arka_panel;
    protected JButton geri_butonu;

    public Oyunun_MenuGorunumu() {

        ImageIcon imageIcon = new ImageIcon(arkaplanYolu);
        gorsel = imageIcon.getImage();

        ImageIcon imageIcon1 = new ImageIcon(baslikYolu);
        baslik = imageIcon1.getImage();

        arka_panel = new JPanel() {

            public void paint(Graphics g) {
                g.drawImage(gorsel, 0, 0, oyun_genisligi, oyun_yuksekligi, null);
                g.drawImage(baslik, oyun_genisligi / 2 - 295, 20, null);

            }
        };

        arka_panel.setSize(new Dimension(oyun_genisligi, oyun_yuksekligi));
        setFocusable(true);
        setDoubleBuffered(true);

        geri_butonu = new JButton("Geri");
        geri_butonu.setSize(100, 30);
        geri_butonu.setLocation(1000, 500);
        //Arka panelin indeksi 1 olan diğer tüm nesnelerin arkasında olmak için 0 indisine sahiptir
        this.add(arka_panel, new Integer(0));
        this.add(geri_butonu, new Integer(1));

        this.setVisible(true);
    }

    public JButton getGeri_butonu() {
        return geri_butonu;
    }

}
