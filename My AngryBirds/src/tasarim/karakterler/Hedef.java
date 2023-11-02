package tasarim.karakterler;

abstract class Hedef extends Entity {

    protected boolean geri;
    protected boolean asagi;
    protected int dinamik_yon;//zamanla degisen yon
    protected int gerileyen_zaman;

    public Hedef(int x, int y, int genislik, int yukseklik) {
        super(x, y, genislik, yukseklik);
    }
}
