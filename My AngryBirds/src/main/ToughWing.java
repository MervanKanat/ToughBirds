package main;

public class ToughWing {
    public ToughWing() {
    }

    public static void main(String[] args) {
        // Java sürüm kontrolü
        if (!System.getProperty("java.version").startsWith("1.8")) {
            System.err.println("Bu uygulama en iyi Java 8 ile çalışır. Lütfen Java 8 kullanın.");
            return; // Uygulamayı sonlandır
        }
        
        // Uygulamayı başlat
        new OyunAlani("Tough Wing");
    }
}
