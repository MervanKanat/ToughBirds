package tasarim;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Oyuncu implements Serializable {

    private String isim;

    //Skor
    private ArrayList<Integer> baslangicSkor;
    private ArrayList<Integer> ortaSkor;
    private ArrayList<Integer> zorSkor;

    //En iyi skor
    private ArrayList<Integer> maks_baslangicSkor;
    private ArrayList<Integer> maks_ortaSkor;
    private ArrayList<Integer> maks_zorSkor;

    //Seviye atlama
    private ArrayList<Integer> baslangic;
    private ArrayList<Integer> orta;
    private ArrayList<Integer> zor;

    private int seviyeNumarasi;

    public Oyuncu(String isim) {

        this.isim = isim;

        baslangicSkor = new ArrayList<Integer>();
        ortaSkor = new ArrayList<Integer>();
        zorSkor = new ArrayList<Integer>();

        maks_baslangicSkor = new ArrayList<Integer>();
        maks_ortaSkor = new ArrayList<Integer>();
        maks_zorSkor = new ArrayList<Integer>();

        baslangic = new ArrayList<Integer>();
        orta = new ArrayList<Integer>();
        zor = new ArrayList<Integer>();

        //OutOfBoundException onlemi 
        for (int i = 0; i < SeviyeNumarasi.getSeviyeNumarasi(); i++) {

            baslangicSkor.add(0);
            ortaSkor.add(0);
            zorSkor.add(0);

            maks_baslangicSkor.add(0);
            maks_ortaSkor.add(0);
            maks_zorSkor.add(0);
        }
        this.kaydet();
    }

    public static Oyuncu dosyadanAktar(String isim) {
        try {
            FileInputStream fis = new FileInputStream("Kaydet/" + isim
                    + ".kaydedildi");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Oyuncu oyuncu = (Oyuncu) ois.readObject();
            return oyuncu;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void kaydet() {
        try {
            File file = new File("Kaydet/" + isim + ".kaydedildi");
            file.delete();
            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            //Veri akis kanalindaki tüm veriyi gonderir (ve bufferi yeni veri icin bosaltir.):
            oos.flush();
            oos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

    public String toString() {
        return isim;
    }

    //Son kilidi acilmis seviyenin numarasini dondurur
    public boolean sonKilit(int seviye, String zorluk) {
        if (zorluk.equals("baslangic")) {
            return baslangic.contains(seviye);
        } else if (zorluk.equals("orta")) {
            return orta.contains(seviye);
        } else if (zorluk.equals("zor")) {
            return zor.contains(seviye);
        }
        return false;
    }

    //Alinan skor ile kilidi acilan son seviyenin numarasini gunceller(set)
    public void sonSeviye(int seviye, String zorluk, int skor) {
        if (zorluk.equals("baslangic")) {
            if (!(baslangic.contains(seviye))) {
                baslangic.add(seviye);
                baslangicSkor.set(seviye - 1, skor);
            }
            if (skor > maks_baslangicSkor.get(seviye - 1)) {
                maks_baslangicSkor.set(seviye - 1, skor);
            }

        } else if (zorluk.equals("orta")) {
            if (!(orta.contains(seviye))) {
                orta.add(seviye);
                ortaSkor.set(seviye - 1, skor);
            }

            if (skor > maks_ortaSkor.get(seviye - 1)) {
                maks_ortaSkor.set(seviye - 1, skor);
            }
        } else if (zorluk.equals("zor")) {
            if (!(zor.contains(seviye))) {
                zor.add(seviye);
                zorSkor.set(seviye - 1, skor);
            }

            if (skor > maks_zorSkor.get(seviye - 1)) {
                maks_zorSkor.set(seviye - 1, skor);
            }
        }
        kaydet();
    }

    public String getIsim() {
        return isim;
    }

    public int getMaksSkor(String zorluk, int seviye) {
        if (zorluk.equals("baslangic")) {
            return maks_baslangicSkor.get(seviye);
        } else if (zorluk.equals("orta")) {
            return maks_ortaSkor.get(seviye);
        } else if (zorluk.equals("zor")) {
            return maks_zorSkor.get(seviye);
        }
        return 0;
    }

}
