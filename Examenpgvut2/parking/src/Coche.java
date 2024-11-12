public class Coche extends Thread {
    Parking parking;
    private int nPlaza;
    private int nCoche;

    public Coche(Parking parking, int nCoche) {
        this.parking = parking;
        this.nCoche = nCoche;
    }

    @Override
    public synchronized void run() {
        parking.aparcar();
        System.out.println("El coche nnúmero " + nCoche+ " Ha aparcado") ;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        parking.salir();
        System.out.println("El coche numero " +nCoche + "Ha salido ");
    }
}
