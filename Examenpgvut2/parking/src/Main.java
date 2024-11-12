
public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking();
        for(int i = 1; i<15; i++) {
            Coche c = new Coche(parking,i);
            c.start();
        }
    }
}