package tasarim.karakterler;

import java.util.ArrayList;
import tasarim.ListChangedEvent;
import tasarim.ListListener;

public class EntityThread extends Thread implements ListListener {
    /*Veritabanindan veri alis-verisi yapilacagi zaman kontrol Entity Frameworktedir.
     Arka planda veritabanı islemleri icin kendisi sorgular olusturmaktadir.
     Basit bir veri islemi icin karmasik bir sorgu gonderebilmektedir
     */

    private ArrayList<Entity> entity;

    public EntityThread(ArrayList<Entity> entity) {
        this.entity = entity;
    }

    public void run() {
        while (true) {
            for (Entity entity : entity) {
                entity.hareket();
            }

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void listChanged(ListChangedEvent event) {
        this.entity = event.getEntityList();
    }
}
