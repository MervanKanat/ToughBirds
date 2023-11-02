package tasarim;

import tasarim.karakterler.Blok;
import tasarim.karakterler.Entity;
import tasarim.karakterler.Zemin;
import tasarim.karakterler.KirmiziKus;
import tasarim.karakterler.Domuz;
import tasarim.karakterler.Pigeon;
import tasarim.karakterler.Sparrow;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Seviye {

    private String arkaplanYolu = "kaynaklar/gorseller/1.png";
    private String arkaplanYolu2 = "kaynaklar/gorseller/2.png";
    private String arkaplanYolu3 = "kaynaklar/gorseller/3.png";
    private Image gorsel;
    private int haritaYap[][];
    private int x_haritaBoyut = 47;
    private int y_haritaBoyut = 22;
    private int blokBoyut = 26;
    private String zeminYolu = "kaynaklar/gorseller/Zemin.png";
    private Image zemin;
    private String blokYolu = "kaynaklar/gorseller/blok.png";
    private Image blok;
    private boolean yuklendi;
    private ArrayList<Entity> entity;
    private int domuzHizi;

    public Seviye(String haritaYolu, String zorluk) {

        ImageIcon imageIcon1 = new ImageIcon(zeminYolu);
        zemin = imageIcon1.getImage();
        ImageIcon imageIcon2 = new ImageIcon(blokYolu);
        blok = imageIcon2.getImage();

        if (zorluk == "baslangic") {
            ImageIcon imageIcon = new ImageIcon(arkaplanYolu);
            gorsel = imageIcon.getImage();
            domuzHizi = 1;
        }
        if (zorluk == "orta") {
            ImageIcon imageIcon = new ImageIcon(arkaplanYolu2);
            gorsel = imageIcon.getImage();

            domuzHizi = 2;
        }
        if (zorluk == "zor") {
            ImageIcon imageIcon = new ImageIcon(arkaplanYolu3);
            gorsel = imageIcon.getImage();

            domuzHizi = 3;
        }

        entity = new ArrayList<Entity>();

        //Dosyayi yukluyoruz ve seviyenin ozelliklerini olusturuyoruz
        try {
            FileInputStream fis = new FileInputStream(haritaYolu);
            yuklendi = true;
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            line = br.readLine();
            while (!line.equals("Harita")) // Haritadan farklıysa Seviyedeki kuşları yükle
            {
                if (line.equals("Pigeon")) {
                    entity.add(new Pigeon());
                }
                if (line.equals("Kirmizi Kus")) {
                    entity.add(new KirmiziKus());
                }
                if (line.equals("Sparrow")) {
                    entity.add(new Sparrow());
                }
                line = br.readLine();
            }
            //Metin dosyasının(txt)karakterlerini tek tek okuyoruz ve Haritayı güncelliyoruz
            haritaYap = new int[y_haritaBoyut][x_haritaBoyut];

            for (int i = 0; i < y_haritaBoyut; i++) {
                line = br.readLine();

                for (int j = 0; j < x_haritaBoyut; j++) {

                    char car = line.charAt(j);
                    String str = String.valueOf(car);
                    haritaYap[i][j] = Integer.parseInt(str);
                    if (haritaYap[i][j] == 3) //Domuz
                    {
                        entity.add(new Domuz(j * blokBoyut, i * blokBoyut, domuzHizi));
                    }
                    if (haritaYap[i][j] == 2) //Blok
                    {
                        entity.add(new Blok(j * blokBoyut, i * blokBoyut, blokBoyut, blokBoyut));
                    }
                    if (haritaYap[i][j] == 1) //Zemin
                    {
                        entity.add(new Zemin(j * blokBoyut, i * blokBoyut, blokBoyut, blokBoyut));
                    }
                }
            }
        } catch (Exception e) {
            yuklendi = false;
        }
    }

    public Image getGorsel() {
        return gorsel;
    }

    public Image getZemin() {
        return zemin;
    }

    public Image getBlok() {
        return blok;
    }

    public int[][] getHaritaYap() {
        return haritaYap;
    }

    public int getX_haritaBoyut() {
        return x_haritaBoyut;
    }

    public int getY_haritaBoyut() {
        return y_haritaBoyut;
    }

    public int getBlokBoyut() {
        return blokBoyut;
    }

    public ArrayList<Entity> getEntityList() {
        return entity;
    }

    public boolean Yuklendi() {
        return yuklendi;
    }

    public void setHaritaYap(int[][] haritaYap) {
        this.haritaYap = haritaYap;
    }
}
