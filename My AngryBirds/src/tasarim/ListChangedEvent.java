package tasarim;

import java.util.ArrayList;
import java.util.EventObject;

import tasarim.karakterler.Kus;
import tasarim.karakterler.Entity;

@SuppressWarnings("serial")
public class ListChangedEvent extends EventObject {

    private ArrayList<Entity> entitys;
    private Kus mevcut_kus;

    public ListChangedEvent(Object source, ArrayList<Entity> entitys, Kus kus) {
        super(source);

        this.entitys = entitys;
        mevcut_kus = kus;
    }

    public ArrayList<Entity> getEntityList() {
        return entitys;
    }

    public Kus getMevcut_kus() {
        return mevcut_kus;
    }
}
