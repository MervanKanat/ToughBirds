package tasarim.karakterler;

import main.OyunAlani;

import java.awt.Dimension;
import javax.swing.ImageIcon;

public class Domuz extends Hedef {

    private Dimension alan_boyutu;

    public Domuz(int x, int y, int domuzHizi) {
        super(x, y, 35, 35);
        gorselYolu = "kaynaklar/gorseller/domuz.png";
        ImageIcon imageIcon = new ImageIcon(gorselYolu);
        gorsel = imageIcon.getImage();
        gorsel_yukseklik = imageIcon.getIconHeight();
        gorsel_genislik = imageIcon.getIconWidth();
        hiz= domuzHizi;
        gerileyen_zaman = 0;
        geri = false;
        alan_boyutu = OyunAlani.getOyunBoyutu();
    }

    public void hareket() {
        if (kutu.x > alan_boyutu.getWidth() - gorsel.getWidth(null)) {
            geri = true;
        }
        if (kutu.x < 250) {
            geri = false;
        }

        if (!geri) {
           kutu.x += hiz;
        } else {
           kutu.x -= hiz;
        }

        if (asagi) {
            kutu.y += (int) (hiz + 3);
        }
        dinamik_yon++;
        gerileyen_zaman++;
    }

    public void asagiHareket() {
        if (gerileyen_zaman > 10) {
            asagi = true;
            gerileyen_zaman = 0;
        }

    }

    public void yonDegis() {
        if (dinamik_yon > 10) {
            geri = !geri;
            dinamik_yon= 0;
        }
    }

    public boolean ileriGit() {
        return !geri;
    }

    public void asagihareketEtme() {
       asagi = false;
    }

}
