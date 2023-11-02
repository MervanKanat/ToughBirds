package kontrol_yapilari;

import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import main.OyunAlani;
import tasarim.OyunTasarimi;
import tasarim.ListChangedEvent;
import tasarim.ListListener;
import tasarim.karakterler.Kus;
import tasarim.karakterler.Sparrow;

public class OyunKontrolu implements KeyListener, ListListener, MouseListener, MouseMotionListener {

    private OyunTasarimi kusTasarimi;
    private OyunAlani kusAlani;

    private Kus mevcut_kus;
    private boolean secilmis_kus = false;

    public OyunKontrolu(OyunAlani oyunAlani) {
        kusAlani = oyunAlani;
        kusTasarimi = oyunAlani.getKusTasarimi();
        JOptionPane.setDefaultLocale(Locale.ENGLISH);
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
        UIManager.put("OptionPane.okButtonText", "Tamam");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (mevcut_kus.Ucus()) {
                    mevcut_kus.saga_hareket();
                }
                break;
            case KeyEvent.VK_LEFT:
                if (mevcut_kus.Ucus()) {
                    mevcut_kus.sola_hareket();
                }
                break;
            case KeyEvent.VK_UP:
                if (!mevcut_kus.Ucus()) {
                    mevcut_kus.setAci(mevcut_kus.getAci() + 0.1);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!mevcut_kus.Ucus()) {
                    mevcut_kus.setAci(mevcut_kus.getAci() - 0.1);
                }
                break;
            case KeyEvent.VK_S:

                // Sparrow gezinmesin
                if (!(mevcut_kus instanceof Sparrow)) {
                    mevcut_kus.asiliKal();
                }
                break;

            case KeyEvent.VK_ESCAPE:
                kusAlani.setSeviyeSecimi_MenuGorunumu();
                break;
            default:
                System.out.println("Geçersiz karakter girdiniz!");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void listChanged(ListChangedEvent event) {
        this.mevcut_kus = event.getMevcut_kus();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (!mevcut_kus.Ucus()) {
            if (e.getX() >= mevcut_kus.getPosition().getX() && e.getX() <= mevcut_kus.getPosition().getX() + mevcut_kus.getGorsel_genislik()) {
                if (e.getY() >= mevcut_kus.getPosition().getY() && e.getY() <= mevcut_kus.getPosition().getY() + mevcut_kus.getGorsel_yukseklik()) {
                    secilmis_kus = true;
                }
            }
            kusAlani.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (secilmis_kus) {
            int x = e.getX(), y = e.getY();
            if (y > 545 - mevcut_kus.getGorsel_yukseklik() / 2) {
                y = 545 - mevcut_kus.getGorsel_yukseklik() / 2;
            }
            if (y < 400) {
                y = 400;
            }
            if (x < mevcut_kus.getGorsel_genislik() / 2) {
                x = mevcut_kus.getGorsel_genislik() / 2;
            }
            if (x > 200) {
                x = 200;
            }
            double deltaX = mevcut_kus.getX_baslangicKonumu() - x;
            double deltaY = mevcut_kus.getY_baslangicKonumu() - y;
            float hiz = (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
            mevcut_kus.setAci(-Math.atan(deltaY / deltaX));
            mevcut_kus.setHiz(hiz);

            if (deltaX > 0) {
                mevcut_kus.baslat();
            } else {
                mevcut_kus.setPosition(mevcut_kus.getX_baslangicKonumu(), mevcut_kus.getY_baslangicKonumu());
            }
        }
        kusAlani.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        secilmis_kus = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (secilmis_kus) {
            int x = e.getX(), y = e.getY();
            if (y > 545 - mevcut_kus.getGorsel_yukseklik() / 2) {
                y = 545 - mevcut_kus.getGorsel_yukseklik() / 2;
            }
            if (y < 400) {
                y = 400;
            }
            if (x < mevcut_kus.getGorsel_genislik() / 2) {
                x = mevcut_kus.getGorsel_genislik() / 2;
            }
            if (x > 200) {
                x = 200;
            }
            double deltaX = mevcut_kus.getX_baslangicKonumu() - x;
            double deltaY = mevcut_kus.getY_baslangicKonumu() - y;
            float speed = (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
            mevcut_kus.setAci(-Math.atan(deltaY / deltaX));
            mevcut_kus.setAci(speed);

            mevcut_kus.setPosition(x - mevcut_kus.getGorsel_genislik() / 2, y - mevcut_kus.getGorsel_yukseklik() / 2);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
